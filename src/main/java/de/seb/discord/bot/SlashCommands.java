package de.seb.discord.bot;

public enum SlashCommands {

    BEER("bier");

    private final String value;

    SlashCommands(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
