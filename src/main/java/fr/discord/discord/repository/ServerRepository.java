package fr.discord.discord.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.discord.discord.model.Server;

public interface ServerRepository extends JpaRepository<Server, Integer> {
    @Query("select s from Server s where s.id = ?1")
    public Optional<Server> findByIdProtectedAs(int id); 
}
