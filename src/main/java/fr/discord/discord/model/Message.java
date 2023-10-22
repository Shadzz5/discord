package fr.discord.discord.model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "[message]")
public class Message {
    public Message(User user2, String text2, LocalDateTime createdAt2) {
    }
    public Message() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime createdAt;
    private String text;

    @ManyToOne()
    @JoinColumn(name = "user_id")

    private User user;
    @ManyToOne()
    @JoinColumn(name = "salon_id")
    private Salon salon;
}
