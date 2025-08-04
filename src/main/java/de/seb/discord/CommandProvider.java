package de.seb.discord;

import de.seb.discord.bot.BeerCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URISyntaxException;

@Configuration
public class CommandProvider {

    @Bean
    public BeerCommand beerCommand(@Value("${http.beer-service}") String url) throws URISyntaxException {
        return new BeerCommand(url);
    }

}
