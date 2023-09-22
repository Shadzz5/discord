package fr.discord.discord.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.discord.discord.exeption.SalonNotFoundExeption;
import fr.discord.discord.model.Salon;
import fr.discord.discord.model.Server;
import fr.discord.discord.repository.SalonRepository;
import fr.discord.discord.repository.ServerRepository;
import fr.discord.discord.request.SalonRequest;
import fr.discord.discord.response.SalonResponse;

@RestController
@RequestMapping("/api/salons")
public class SalonApiController {
    @Autowired
    SalonRepository salonRepository;
    @Autowired
    ServerRepository serverRepository;

    @GetMapping("/allSalons")
    public List<SalonResponse> findAllSalons(@RequestParam Integer serverId) {
        return this.salonRepository.findAllByServerId(serverId)
                .stream()
                .map(f -> SalonResponse.builder()
                        .id(f.getId())
                        .name(f.getName())
                        .isText(f.getIsText())
                        .build())
                .toList();
    }

    @GetMapping("/{id}")
    public Salon findById(@PathVariable int id) {
        return this.salonRepository.findById(id).orElseThrow(SalonNotFoundExeption::new);
    }

    @PostMapping("/add")
    public SalonResponse add(@RequestBody SalonRequest request) {
        Salon salon = new Salon();
        Server server = this.serverRepository.findById(request.getServerId()).orElseThrow(SalonNotFoundExeption::new);
        salon.setName(request.getIsText() == true ? "#" + request.getName() : "€" + request.getName());
        salon.setIsText(request.getIsText());
        salon.setServer(server);
        this.salonRepository.save(salon);
        return SalonResponse.builder()
                .id(salon.getId())
                .name(salon.getName())
                .isText(salon.getIsText())
                .build();
    }
}
