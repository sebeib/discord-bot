package de.seb.discord.domain.beer;

import java.util.List;

public record Discount(String store, List<Beer> offers) {

}