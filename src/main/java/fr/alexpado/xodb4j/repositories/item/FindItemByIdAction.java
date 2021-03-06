package fr.alexpado.xodb4j.repositories.item;

import fr.alexpado.lib.rest.RestAction;
import fr.alexpado.lib.rest.enums.RequestMethod;
import fr.alexpado.xodb4j.XoDB;
import fr.alexpado.xodb4j.impl.Item;
import fr.alexpado.xodb4j.interfaces.IItem;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

public class FindItemByIdAction extends RestAction<IItem> {

    private final XoDB    xoDB;
    private final Integer id;

    public FindItemByIdAction(XoDB xoDB, Integer id) {

        this.xoDB = xoDB;
        this.id   = id;
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
     * Convert the response body to the desired type for this request.
     *
     * @param requestBody
     *         The response body received.
     *
     * @return The response body as byte array.
     */
    @Override
    public IItem convert(byte[] requestBody) {

        JSONArray array = new JSONArray(new String(requestBody));
        return new Item(this.xoDB, array.getJSONObject(0));
    }

}
