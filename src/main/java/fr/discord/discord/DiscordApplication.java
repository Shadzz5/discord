package fr.discord.discord;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.discord.discord.service.HelloServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

@SpringBootApplication
public class DiscordApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(DiscordApplication.class, args);

        // Server server = ServerBuilder
        //     .forPort(8082)
        //     .addService(new HelloServiceImpl()).build();

        // server.start();
        // server.awaitTermination();
    }
}