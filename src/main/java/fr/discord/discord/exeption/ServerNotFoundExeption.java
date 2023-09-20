package fr.discord.discord.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Le serveur n'existe pas.")

public class ServerNotFoundExeption extends RuntimeException{
    
}
