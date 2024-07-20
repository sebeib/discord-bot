package de.seb.discord.bot;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.seb.discord.domain.beer.Beer;
import de.seb.discord.domain.beer.Discount;
import de.seb.discord.domain.beer.RestResponse;
import net.dv8tion.jda.api.entities.templates.Template;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BeerCommand extends ListenerAdapter {

    private final HttpClient client;
    private final Gson gson;
    private final String url;

    private static final String TEMPLATE_STORE = """
            ## 🛒   %s
            %s
            
            """;

    private static final String TEMPLATE_OFFER = """
            ### 🍺   %s
                     _%s_
            💵   %s €
            🗓️   %s - %s
            """;

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public BeerCommand(@Value("${http.beer-service}") String url) throws URISyntaxException {
        client = HttpClient.newBuilder().build();
        gson = Converters.registerAll(new GsonBuilder()).create();
        this.url = url;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals(SlashCommands.BEER.value())) {
            String zip = event.getOption("plz").getAsString();

            event.deferReply().queue();
            List<Discount> offers = fetchOffers(zip);
            String message = buildMessage(offers);
            event.getHook().sendMessage(message).queue();
        }
    }

    private String buildMessage(List<Discount> discounts) {
        return discounts.stream()
                .map(discount -> TEMPLATE_STORE.formatted(
                        discount.store(),
                        discount.offers().stream().map(offer -> TEMPLATE_OFFER.formatted(offer.brand(), offer.description(), offer.price(), dateFormat.format(offer.from()), dateFormat.format(offer.to())))
                                .collect(Collectors.joining(""))))
                .collect(Collectors.joining());
    }

    private List<Discount> fetchOffers(String zip) {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(url.replace("%ZIP%", String.valueOf(zip)))).GET().build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Object body = response.body();
            RestResponse restResponse = gson.fromJson((String) body, RestResponse.class);
            return restResponse.offers();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
