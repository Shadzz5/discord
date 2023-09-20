package fr.discord.discord.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.discord.discord.model.Salon;

public interface SalonRepository extends JpaRepository<Salon, Integer> {
}
