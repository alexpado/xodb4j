package fr.alexpado.xodb4j.repositories.market;

import fr.alexpado.lib.rest.RestAction;
import fr.alexpado.lib.rest.enums.RequestMethod;
import fr.alexpado.lib.rest.interfaces.IRestResponse;
import fr.alexpado.xodb4j.XoDB;
import fr.alexpado.xodb4j.impl.Market;
import fr.alexpado.xodb4j.impl.Pack;
import fr.alexpado.xodb4j.interfaces.IItem;
import fr.alexpado.xodb4j.interfaces.IMarket;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindPriceAction extends RestAction<List<IMarket>> {

    private final XoDB          xoDB;
    private final IItem         item;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public FindPriceAction(XoDB xoDB, IItem item, LocalDateTime startTime, LocalDateTime endTime) {

        this.xoDB      = xoDB;
        this.item      = item;
        this.startTime = startTime;
        this.endTime   = endTime;
    }

    @Override
    public @NotNull Map<String, String> getRequestParameters() {

        return new HashMap<>() {{
            if (startTime != null) {
                this.put("startTimestamp", String.valueOf(startTime.toEpochSecond(ZoneOffset.UTC)));
            }
            if (endTime != null) {
                this.put("endTimestamp", String.valueOf(endTime.toEpochSecond(ZoneOffset.UTC)));
            }
        }};
    }

    /**
     * Retrieve the {@link RequestMethod} to use when sending the request.
     *
     * @return The {@link RequestMethod} to use.
     */
    @Override
    public @NotNull RequestMethod getRequestMethod() {

        return RequestMethod.GET;
    }

    /**
     * Retrieve the string version that will be used to send the request.
     *
     * @return An URL as String
     */
    @Override
    public @NotNull String getRequestURL() {

        return String.format("%s/market-all/%s", this.xoDB.getRootUrl(), this.item.getId());
    }

    /**
     * Convert the response body to the desired type for this request.
     *
     * @param response
     *         The response body received.
     *
     * @return The converted response body
     */
    @Override
    public List<IMarket> convert(IRestResponse response) {

        JSONArray     array   = new JSONArray(new String(response.getBody()));
        List<IMarket> markets = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            markets.add(new Market(this.item, array.getJSONArray(i)));
        }

        return markets;
    }
}
