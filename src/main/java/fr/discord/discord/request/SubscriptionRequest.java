package fr.discord.discord.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SubscriptionRequest {
    private String username;
    private String password;
    private String email;
}