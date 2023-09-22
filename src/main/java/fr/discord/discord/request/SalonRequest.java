package fr.discord.discord.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalonRequest {
    private String name;
    private Boolean isText;
    private Integer serverId;
}
