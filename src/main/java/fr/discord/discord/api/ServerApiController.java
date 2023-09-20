package fr.discord.discord.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.discord.discord.exeption.ServerNotFoundExeption;
import fr.discord.discord.model.Server;
import fr.discord.discord.repository.ServerRepository;
import fr.discord.discord.response.ServerResponse;

@RestController
@RequestMapping("/api/servers")
public class ServerApiController {
    @Autowired
    ServerRepository serverRepository;

    @GetMapping
    public List<ServerResponse> findAllServer(Model model) {
        return serverRepository.findAll()
        .stream().map(s-> ServerResponse.builder()
            .id(s.getId())
            .name(s.getName())
            .salons(s.getSalons().size())
            .build()
        ).toList();
    } 

    @GetMapping("/{id}")
    public Server findById(@PathVariable int id) {
        return this.serverRepository.findByIdProtectedAs(id).orElseThrow(ServerNotFoundExeption::new);
    }

    @PostMapping
    public Server add(@RequestBody Server server) {
        this.serverRepository.save(server);
        return server;
    }
}
