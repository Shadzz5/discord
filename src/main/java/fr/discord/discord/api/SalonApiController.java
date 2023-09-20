package fr.discord.discord.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.discord.discord.exeption.SalonNotFoundExeption;
import fr.discord.discord.model.Salon;
import fr.discord.discord.repository.SalonRepository;

@RestController
@RequestMapping("/api/salons")
public class SalonApiController {
    @Autowired
    SalonRepository salonRepository;

    @GetMapping
    public List<Salon> findAllSalons() {
        return this.salonRepository.findAll();
    }

    @GetMapping("/{id}")
    public Salon findById(@PathVariable int id) {
        return this.salonRepository.findById(id).orElseThrow(SalonNotFoundExeption::new);
    }

    @PostMapping
    public Salon add(@RequestBody Salon salon) {
        this.salonRepository.save(salon);
        return salon;
    }
}
