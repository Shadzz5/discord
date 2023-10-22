package fr.discord.discord.service;


import fr.discord.discord.proto.HelloRequest;
import fr.discord.discord.proto.HelloResponse;
import fr.discord.discord.proto.HelloServiceGrpc.HelloServiceImplBase;
import io.grpc.stub.StreamObserver;
public class HelloServiceImpl extends HelloServiceImplBase {
    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        StringBuilder message = new StringBuilder()
                    .append("Hello, ")
                    .append(request.getUsername())
                    .append("! ")
                    .append("Ton message a bien été reçu [")
                    .append(request.getMessage())
                    .append("]");

        HelloResponse response = HelloResponse.newBuilder()
          .setMessage(message.toString())
          .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted(); // Pour terminer le flux (ferme la connexion avec le client)
    }
}

//INSTALLER MAVEN APRES CREER UNE VARIABLE DENVIRONNEMENT ET ENSUITE FAIRE UN GENERATE TOOLS https://phoenixnap.com/kb/install-maven-windows