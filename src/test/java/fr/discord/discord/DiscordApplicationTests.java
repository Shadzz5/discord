package fr.discord.discord;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import fr.discord.discord.api.SalonApiController;
import fr.discord.discord.api.ServerApiController;
import fr.discord.discord.repository.SalonRepository;

@SpringBootTest
class DiscordApplicationTests {

	@InjectMocks 
	ServerApiController serverApiController;
	@Mock
	SalonRepository salonRepository;

	@Test
	void findAllIsValidForSalon(){
		this.serverApiController.findAllServer();
		Mockito.verify(this.salonRepository).findAll();
	}

}
