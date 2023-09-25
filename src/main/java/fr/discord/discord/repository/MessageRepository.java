package fr.discord.discord.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.discord.discord.model.Message;
import fr.discord.discord.model.Salon;

public interface MessageRepository  extends JpaRepository<Message,Integer> {
        public List<Message> findAllBySalonId(Integer salonId);

}
