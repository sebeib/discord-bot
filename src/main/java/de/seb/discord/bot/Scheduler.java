package de.seb.discord.bot;

import de.seb.discord.domain.beer.Discount;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static de.seb.discord.bot.CringeMessages.DEAL_INTROS;

@Component
@Scope("prototype")
public class Scheduler {

    private final static Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);

    private final BeerCommand beerCommand;
    private final JDA jda;
    private final String roleId;
    private int lastResult = -1;

    public Scheduler(BeerCommand beerCommand, JDA jda, @Value("${bot.discount.role}") String roleId) {
        this.beerCommand = beerCommand;
        this.jda = jda;
        this.roleId = roleId;
    }

    public void run(String channelId, SlashCommands command) {
        TextChannel channel = jda.getTextChannelById(channelId);
        Role role = jda.getRoleById(roleId);

        if(channel == null || role == null) {
            LOGGER.warn("No channel or role found for channel {} and role {}", channelId, roleId);
            throw new RuntimeException("Could not find channel or role");
        }

        List<Discount> discountList = beerCommand.fetchOffers("08060", command.getQuery());
        List<String> discountMessageList = beerCommand.buildMessage(discountList);

        if(discountMessageList.hashCode() != lastResult) {
            LOGGER.info("Sending {} discount messages", discountMessageList.size());
            lastResult = discountMessageList.hashCode();
            if(!discountMessageList.isEmpty()) {
                channel.sendMessage(role.getAsMention() + CringeMessages.getRandom(DEAL_INTROS)).queue();
                discountMessageList.forEach(message -> channel.sendMessage(message).queue());
            } else {
                LOGGER.info("No discounts found.");
            }
        } else {
            LOGGER.info("Same result as yesterday. Skipping.");
        }
    }

}