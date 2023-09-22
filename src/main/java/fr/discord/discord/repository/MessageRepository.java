package fr.discord.discord.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.discord.discord.model.Message;

public interface MessageRepository  extends JpaRepository<Message,Integer> {
    
}
