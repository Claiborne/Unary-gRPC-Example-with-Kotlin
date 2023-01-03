package greeting.server

import claiborne.greeting.GreetingRequest
import claiborne.greeting.GreetingResponse
import claiborne.greeting.GreetingServiceGrpc
import io.grpc.stub.StreamObserver

class GreetingServerImpl: GreetingServiceGrpc.GreetingServiceImplBase() {

  @Override
  override fun greet(request: GreetingRequest?, responseObserver: StreamObserver<GreetingResponse>?) {
    responseObserver?.onNext(GreetingResponse.newBuilder().setResult("Hello, ${request?.firstName}").build())
    responseObserver?.onCompleted()
  }
}