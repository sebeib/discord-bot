package de.seb.discord.bot;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ResultStore {

    private final Map<String, Integer> results = new HashMap<>();

    public void put(SlashCommands command, List<String> result) {
        results.put(command.getQuery(), result.hashCode());
    }

    public int get(SlashCommands command) {
        return results.getOrDefault(command.getQuery(), -1);
    }

}
