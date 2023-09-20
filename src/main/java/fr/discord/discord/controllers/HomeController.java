package fr.discord.discord.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.discord.discord.model.Server;

public class HomeController {
    private List<Server> servers = new ArrayList<Server>();

    // @PostConstruct
    // public void init() {
    //     for (int i = 0; i < 10; i++) {
    //         Server server = new Server();
    //         server.setId(i);
    //         server.setName("Server" + i);
    //         servers.add(server);
    //     }
    // }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("servers", servers);
        return "index";
    }

    @PostMapping("/")
    public String postServers(@RequestParam String name, Model model) {
        Server server = new Server();
        Random r = new Random(15);
        server.setId(r.nextInt());
        server.setName(name);
        servers.add(server);
        model.addAttribute("servers", servers);
        return "redirect:/index";
    }

}
