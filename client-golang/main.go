package main

import (
	pingPong "clieng-golang/pb"
	"context"
	"log"

	"google.golang.org/grpc"
)

func main() {
	var conn *grpc.ClientConn
	conn, err := grpc.Dial(":5003", grpc.WithInsecure())
	if err != nil {
		log.Fatalf("did not connect: %s", err)
	}
	defer conn.Close()

	client := pingPong.NewPingPongServiceClient(conn)

	response, err := client.PingPong(context.Background(), &pingPong.PingPongRequest{
		Message: "Pong",
	})
	if err != nil {
		log.Printf("Error when calling PingPong: %s", err)
	}

	response, err = client.PingPong(context.Background(), &pingPong.PingPongRequest{
		Message: "Ping",
	})
	if err != nil {
		log.Fatalf("Error when calling SayHello: %s", err)
	}
	log.Printf("Response from server: %s", response.Message)
}
