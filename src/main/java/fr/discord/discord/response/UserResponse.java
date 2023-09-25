package fr.discord.discord.response;

import fr.discord.discord.model.Salon;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserResponse {
    private String username;
    private Integer id;
    private String email;
    private Salon salon;
    private String displayName;
    private String status ;

}
