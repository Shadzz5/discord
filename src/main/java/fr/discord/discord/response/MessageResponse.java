package fr.discord.discord.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MessageResponse {
    private Integer id;
    private LocalDateTime createdAt;
    private String text;
}
