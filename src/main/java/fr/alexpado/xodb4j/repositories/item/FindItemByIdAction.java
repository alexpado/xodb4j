package fr.alexpado.xodb4j.repositories.item;

import fr.alexpado.lib.rest.RestAction;
import fr.alexpado.lib.rest.enums.RequestMethod;
import fr.alexpado.lib.rest.interfaces.IRestResponse;
import fr.alexpado.xodb4j.XoDB;
import fr.alexpado.xodb4j.impl.Item;
import fr.alexpado.xodb4j.interfaces.IItem;
import fr.alexpado.xodb4j.providers.composite.ItemProvider;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.util.Map;

public class FindItemByIdAction extends RestAction<IItem> {

    private final XoDB                xoDB;
    private final ItemProvider        provider;
    private final Integer             id;
    private final Map<String, String> urlParams;

    public FindItemByIdAction(XoDB xoDB, ItemProvider provider, Integer id, Map<String, String> urlParams) {

        this.xoDB      = xoDB;
        this.provider  = provider;
        this.id        = id;
        this.urlParams = urlParams;
    }

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

        return String.format("%s/item/%s", this.xoDB.getRootUrl(), this.id);
    }

    /**
     * Retrieve a map of parameters that should be included in the request URL.
     *
     * @return A map of parameters.
     */
    @Override
    public @NotNull Map<String, String> getRequestParameters() {

        return this.urlParams;
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
    public IItem convert(IRestResponse response) {

        JSONArray array = new JSONArray(new String(response.getBody()));
        return new Item(this.provider, array.getJSONObject(0));
    }
}
