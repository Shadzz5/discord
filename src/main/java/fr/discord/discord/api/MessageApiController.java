package fr.discord.discord.api;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.discord.discord.model.Message;
import fr.discord.discord.model.Salon;
import fr.discord.discord.model.User;
import fr.discord.discord.repository.MessageRepository;
import fr.discord.discord.repository.SalonRepository;
import fr.discord.discord.repository.UserRepository;
import fr.discord.discord.request.MessageRequest;
import fr.discord.discord.response.MessageResponse;

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

    @PostMapping("/add")
    public MessageResponse postMethodName(@RequestBody MessageRequest request) {
        Message message = new Message();
        User user = userRepository.findById(request.getUser_id()).orElseThrow(null);
        Salon salon = salonRepository.findById(request.getSalon_id()).orElseThrow(null);
        message.setText(request.getText());
        message.setCreatedAt(LocalDateTime.now());
        message.setSalon(salon);
        message.setUser(user);
        return MessageResponse.builder().text(message.getText()).createdAt(message.getCreatedAt()).build();
    }

}
