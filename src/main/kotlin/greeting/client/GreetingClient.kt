package greeting.client

import claiborne.greeting.GreetingRequest
import claiborne.greeting.GreetingResponse
import claiborne.greeting.GreetingServiceGrpc
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder

class GreetingClient {

  fun start(args: List<String>) {

    if (args.isEmpty()) {
      println("GreetingClient.start() needs one argument to work.")
    }

    val channel: ManagedChannel = ManagedChannelBuilder
      .forAddress("localhost", 50051)
      .usePlaintext() // use for now to keep this simple
      .build()

    when (args[0]) {
      "greet" -> performGreet(channel)
      else -> println("Invalid argument keyword $args[0]")
    }

    println("Shutting down client.")
    channel.shutdown()
    println("Client shutdown.")
  }

  private fun performGreet(channel: ManagedChannel) {
    println("Performing greet...")
    val stub: GreetingServiceGrpc.GreetingServiceBlockingStub = GreetingServiceGrpc.newBlockingStub(channel)
    val response: GreetingResponse = stub.greet(GreetingRequest.newBuilder().setFirstName("Will").build())

    println(response.result)
  }
}

fun main() {
  GreetingClient().start(listOf<String>("greet"))
}
