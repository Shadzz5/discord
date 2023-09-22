package fr.discord.discord.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequest {
    private String text;
    private Integer user_id;
    private Integer salon_id;
}
