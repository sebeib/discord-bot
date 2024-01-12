package de.seb.discord.domain.beer;

import java.time.LocalDate;
import java.util.List;

public record RestResponse(LocalDate lastFetched, List<Discount> offers) {}