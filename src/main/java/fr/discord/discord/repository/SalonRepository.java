package fr.discord.discord.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.discord.discord.model.Salon;
public interface SalonRepository extends JpaRepository<Salon, Integer> {
    public List<Salon> findAllByServerId(Integer serverId);
}
