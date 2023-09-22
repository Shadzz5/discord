package fr.discord.discord.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.discord.discord.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByUsername(String username);
}
