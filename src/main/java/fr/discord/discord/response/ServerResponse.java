package fr.discord.discord.response;

import java.util.List;

import fr.discord.discord.model.Salon;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ServerResponse {
    private int id;
    private String name;
    private int salons;
}
