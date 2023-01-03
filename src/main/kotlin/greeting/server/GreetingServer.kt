package greeting.server

import io.grpc.Server
import io.grpc.ServerBuilder

class GreetingServer {

  fun start() {
    val port = 50051;

    val server: Server = ServerBuilder
      .forPort(port)
      .addService(GreetingServerImpl())
      .build()

    server.start()
    println("Server started on port $port.")

    Runtime.getRuntime().addShutdownHook(Thread {
      println("Received server shutdown request.")
      server.shutdown()
      println("Server stopped.")
    })

    server.awaitTermination()
  }
}

fun main() {
  GreetingServer().start()
}