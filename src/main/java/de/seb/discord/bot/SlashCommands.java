package de.seb.discord.bot;

import java.util.Arrays;
import java.util.Optional;

public enum SlashCommands {

    BEER("bier", "beer"),
    SPEZI("spezi", "spezi");

    private final String name;
    private final String query;

    SlashCommands(String value, String query) {
        this.name = value;
        this.query = query;
    }

    public String getName() {
        return name;
    }
    public String getQuery() {
        return query;
    }

    public static Optional<SlashCommands> find(String command) {
        return Arrays.stream(values())
                .filter(sc -> sc.getName().equals(command))
                .findFirst();
    }
}
