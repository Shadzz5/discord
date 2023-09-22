package fr.discord.discord.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SalonResponse {
    private int id;
    private String name;
    private Boolean isText;

}
