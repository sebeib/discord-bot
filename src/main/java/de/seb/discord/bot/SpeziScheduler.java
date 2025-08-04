package de.seb.discord.bot;

import de.seb.discord.domain.beer.Discount;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static de.seb.discord.bot.CringeMessages.DEAL_INTROS;

@Component
public class SpeziScheduler {

    private final static Logger LOGGER = LoggerFactory.getLogger(SpeziScheduler.class);

    private final BeerCommand beerCommand;
    private final JDA jda;
    private final String channelId;
    private final String roleId;

    public SpeziScheduler(BeerCommand beerCommand, JDA jda, @Value("${bot.discount.channel}") String channelId, @Value("${bot.discount.role}") String roleId) {
        this.beerCommand = beerCommand;
        this.jda = jda;
        this.channelId = channelId;
        this.roleId = roleId;
    }

    @Scheduled(cron = "${bot.discount.cron}")
    public void run() {
        TextChannel channel = jda.getTextChannelById(channelId);
        Role role = jda.getRoleById(roleId);

        if(channel == null || role == null) {
            LOGGER.warn("No channel or role found for channel {} and role {}", channelId, roleId);
            throw new RuntimeException("Could not find channel or role");
        }

        List<Discount> discountList = beerCommand.fetchOffers("08060", SlashCommands.SPEZI.getQuery());
        List<String> discountMessageList = beerCommand.buildMessage(discountList);

        if(!discountMessageList.isEmpty()) {
            channel.sendMessage(role.getAsMention() + CringeMessages.getRandom(DEAL_INTROS)).queue();
            discountMessageList.forEach(message -> channel.sendMessage(message).queue());
        }
    }

}
