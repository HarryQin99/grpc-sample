package org.example.pingpong.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Logger;

public class PingPongServer {
   private static final Logger logger = Logger.getLogger(PingPongServer.class.getName());

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(5003)
                .addService(new PingPongServiceImpl())
                .build();

        server.start();
        logger.info("Server started, listening on " + 5003);
        server.awaitTermination();
    }
}
