package fr.discord.discord.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.discord.discord.model.Message;
import fr.discord.discord.repository.SalonRepository;

@Controller
public class SalonController {
    @Autowired
    SalonRepository repoSalon;

    private List<Message> messages = new ArrayList<Message>();

    @GetMapping("/salons")
    public String salons(Model model) {
        model.addAttribute("messages", messages);
        return "salons";
    }

    @PostMapping("/salons")
    public String postAddMessage(@RequestParam("newMessage") String newMessage, Model model) {
        Message message = new Message();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        message.setCreatedAt(LocalDateTime.now());
        message.setText(newMessage);
        // message.setUser() - Vous devez également définir l'utilisateur associé au
        // message.

        messages.add(message);
        model.addAttribute("messages", messages);
        model.addAttribute("formatter", formatter);

        return "salons";
    }
}
