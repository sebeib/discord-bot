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
public class Scheduler {

    private final static Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);

    private final BeerCommand beerCommand;
    private final JDA jda;
    private final String roleId;
    private final ResultStore resultStore;

    public Scheduler(BeerCommand beerCommand, JDA jda, @Value("${bot.discount.role}") String roleId, ResultStore resultStore) {
        this.beerCommand = beerCommand;
        this.jda = jda;
        this.roleId = roleId;
        this.resultStore = resultStore;
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

        final int lastResult = resultStore.get(command);
        LOGGER.info("{} last result: {}", command.getQuery(), lastResult);
        LOGGER.info("{} this result: {}", command.getQuery(), discountMessageList.hashCode());

        if(discountMessageList.hashCode() != lastResult) {
            LOGGER.info("Sending {} discount messages", discountMessageList.size());
            resultStore.put(command, discountMessageList);
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