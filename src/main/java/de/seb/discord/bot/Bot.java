package de.seb.discord.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

@Component
public class Bot {

    @Value("${bot.token}")
    private String token;

    @Value("${bot.channel}")
    private String channel;

    @Value("${bot.activity}")
    private String activity;

    private final BeerCommand beerCommand;

    public Bot(BeerCommand beerCommand) {
        this.beerCommand = beerCommand;
    }

    public void connect() throws InterruptedException {
        JDA jda = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setActivity(Activity.playing(activity))
                .addEventListeners(beerCommand)
                .build();
        jda.awaitReady();

        Guild server = jda.getGuildById(channel);
        server.updateCommands().addCommands(
                Commands.slash(SlashCommands.BEER.value(), "Lokale Bierangebote")
                        .addOption(OptionType.STRING, "plz", "Postleitzahl")
        ).queue();
    }

}
