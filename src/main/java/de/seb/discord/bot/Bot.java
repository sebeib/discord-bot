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

    private final JDA jda;

    @Value("${bot.server}")
    private String guildId;

    public Bot(JDA jda) {
        this.jda = jda;
    }

    public void connect() {
        Guild server = jda.getGuildById(guildId);
        server.updateCommands().addCommands(
                Commands.slash(SlashCommands.BEER.getName(), "Lokale Bierangebote")
                        .addOption(OptionType.STRING, "plz", "Postleitzahl"),
                Commands.slash(SlashCommands.SPEZI.getName(), "Lokale Speziangebote")
                        .addOption(OptionType.STRING, "plz", "Postleitzahl")
        ).queue();
    }

}
