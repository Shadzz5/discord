package fr.discord.discord.api;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.discord.discord.model.Message;
import fr.discord.discord.model.Salon;
import fr.discord.discord.model.User;
import fr.discord.discord.repository.MessageRepository;
import fr.discord.discord.repository.SalonRepository;
import fr.discord.discord.repository.UserRepository;
import fr.discord.discord.request.MessageRequest;
import fr.discord.discord.response.MessageResponse;
import fr.discord.discord.response.SalonResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/messages")
public class MessageApiController {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    SalonRepository salonRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public List<MessageResponse> getAllMessages(@RequestParam Integer salonId) {
        return this.messageRepository.findAllBySalonId(salonId)
                .stream()
                .map(f -> MessageResponse.builder()
                        .id(f.getId())
                        .text(f.getText())
                        .createdAt(f.getCreatedAt())
                        .build())
                .toList();
    }

    @PostMapping("/add")
    public MessageResponse postMessage(@RequestBody MessageRequest request) {
        Message message = new Message();
        User user = userRepository.findById(request.getUserId()).orElseThrow(null);
        Salon salon = salonRepository.findById(request.getSalonId()).orElseThrow(null);
        message.setText(request.getText());
        message.setCreatedAt(LocalDateTime.now());
        message.setSalon(salon);
        message.setUser(user);
        this.messageRepository.save(message);
        return MessageResponse.builder().text(message.getText()).createdAt(message.getCreatedAt()).build();
    }
}
