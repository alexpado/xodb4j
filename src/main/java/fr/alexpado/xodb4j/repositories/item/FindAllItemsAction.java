package fr.alexpado.xodb4j.repositories.item;

import fr.alexpado.lib.rest.RestAction;
import fr.alexpado.lib.rest.enums.RequestMethod;
import fr.alexpado.lib.rest.interfaces.IRestResponse;
import fr.alexpado.xodb4j.XoDB;
import fr.alexpado.xodb4j.impl.Item;
import fr.alexpado.xodb4j.interfaces.IItem;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllItemsAction extends RestAction<List<IItem>> {

    private final XoDB                xoDB;
    private final Map<String, String> searchParams;

    public FindAllItemsAction(XoDB xoDB) {

        this.xoDB         = xoDB;
        this.searchParams = new HashMap<>();
    }

    public FindAllItemsAction(XoDB xoDB, Map<String, Object> searchParams) {

        this.xoDB         = xoDB;
        this.searchParams = new HashMap<>();

        searchParams.forEach((k, v) -> this.searchParams.put(k, URLEncoder.encode(v.toString(), StandardCharsets.UTF_8)));
    }

    @Override
    public @NotNull RequestMethod getRequestMethod() {

        return RequestMethod.GET;
    }

    @Override
    public @NotNull String getRequestURL() {

        return String.format("%s/items", this.xoDB.getRootUrl());
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
    public List<IItem> convert(IRestResponse response) {

        JSONArray   array = new JSONArray(new String(response.getBody()));
        List<IItem> items = new ArrayList<>();

        for (int i = 0 ; i < array.length() ; i++) {
            items.add(new Item(this.xoDB, array.getJSONObject(i)));
        }
        return items;
    }

    @Override
    public @NotNull Map<String, String> getRequestParameters() {

        return this.searchParams;
    }

}
