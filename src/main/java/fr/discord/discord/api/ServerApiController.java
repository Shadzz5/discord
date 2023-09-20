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

import fr.discord.discord.exeption.SalonNotFoundExeption;
import fr.discord.discord.model.Salon;
import fr.discord.discord.model.Server;
import fr.discord.discord.repository.SalonRepository;
import fr.discord.discord.repository.ServerRepository;

@RestController
@RequestMapping("/api/servers")
public class ServerApiController {
    @Autowired
    ServerRepository serverRepository;

    @GetMapping
    public List<Server> findAllServer(Model model) {
        List<Server> servers = serverRepository.findAll();
        model.addAttribute("servers", servers);
        return servers;
    } 

    @GetMapping("/{id}")
    public Server findById(@PathVariable int id) {
        return this.serverRepository.findByIdProtectedAs(id).orElseThrow(SalonNotFoundExeption::new);
    }

    @PostMapping
    public Server add(@RequestBody Server server) {
        this.serverRepository.save(server);
        return server;
    }
}
