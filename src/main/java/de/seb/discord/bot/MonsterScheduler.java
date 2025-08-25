package de.seb.discord.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MonsterScheduler {

    private final static Logger LOGGER = LoggerFactory.getLogger(MonsterScheduler.class);

    private final String channelId;
    private final Scheduler scheduler;

    public MonsterScheduler(@Value("${bot.discount.channel.monster}") String channelId, Scheduler scheduler) {
        this.scheduler = scheduler;
        this.channelId = channelId;
    }

    @Scheduled(cron = "${bot.discount.cron}")
    public void run() {
        LOGGER.info("MonsterScheduler started");
        scheduler.run(channelId, SlashCommands.MONSTER);
    }

}