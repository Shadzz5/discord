package fr.discord.discord.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import fr.discord.discord.model.Salon;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ServerController {
    private List<Salon> salons = new ArrayList<Salon>();

    @GetMapping("/server/{id}")
    public String server(@PathVariable Integer id,Model model) {
        model.addAttribute("salons", salons);
        model.addAttribute("id", id);
        return "server";
    }
    @PostMapping("/server")
    public String postSalons(@RequestParam String name,@RequestParam Boolean isText ,Model model) {
        Salon salon = new Salon();
        Random r = new Random(15);
        salon.setId(r.nextInt());
        salon.setName((isText == false ? "€" : "#") + name);
        salon.setIsText(isText);
        salons.add(salon);
        model.addAttribute("salons", salons);
        return "redirect:/server";
    }   
}
