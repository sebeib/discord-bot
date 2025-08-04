package de.seb.discord;

import de.seb.discord.bot.BeerCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdaProvider {

    @Value("${bot.activity}")
    private String activity;

    @Value("${bot.token}")
    private String token;

    @Bean
    public JDA jda(BeerCommand beerCommand) throws InterruptedException {
        return JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setActivity(Activity.playing(activity))
                .addEventListeners(beerCommand)
                .build()
                .awaitReady();
    }

}
