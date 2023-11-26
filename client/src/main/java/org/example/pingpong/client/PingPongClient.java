package org.example.pingpong.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.example.pingpong.PingPongRequest;
import org.example.pingpong.PingPongResponse;
import org.example.pingpong.PingPongServiceGrpc;

public class PingPongClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5003)
                .usePlaintext()
                .build();

        PingPongServiceGrpc.PingPongServiceBlockingStub stub = PingPongServiceGrpc.newBlockingStub(channel);

        try {
            PingPongResponse pingPongResponse = stub.pingPong(PingPongRequest.newBuilder()
                    .setMessage("Ping")
                    .build());

            System.out.println(pingPongResponse.getMessage());
        } catch (StatusRuntimeException e) {
            System.out.println(e.getStatus());
        }
    }
}
