package org.example.pingpong.server;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.example.pingpong.PingPongRequest;
import org.example.pingpong.PingPongResponse;
import org.example.pingpong.PingPongServiceGrpc;

public class PingPongServiceImpl extends PingPongServiceGrpc.PingPongServiceImplBase {
    @Override
    public void pingPong(PingPongRequest request, StreamObserver<PingPongResponse> responseObserver) {
        String message = request.getMessage();

        // check if message is Ping
        if (message.equals("Ping")) {
            PingPongResponse pingPongResponse = PingPongResponse.
                    newBuilder()
                    .setMessage("Pong")
                    .build();

            responseObserver.onNext(pingPongResponse);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(Status.NOT_FOUND.withDescription("Ping Error").asRuntimeException());
        }
    }
}
