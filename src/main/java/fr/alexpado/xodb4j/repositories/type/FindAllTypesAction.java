package fr.alexpado.xodb4j.repositories.type;

import fr.alexpado.lib.rest.RestAction;
import fr.alexpado.lib.rest.enums.RequestMethod;
import fr.alexpado.lib.rest.interfaces.IRestResponse;
import fr.alexpado.xodb4j.XoDB;
import fr.alexpado.xodb4j.impl.Type;
import fr.alexpado.xodb4j.interfaces.IType;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class FindAllTypesAction extends RestAction<List<IType>> {

    private final XoDB xoDB;

    public FindAllTypesAction(XoDB xoDB) {

        this.xoDB = xoDB;
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
     * Retrieve the request URL to which the request should be sent.
     *
     * @return The request URL.
     */
    @Override
    public @NotNull String getRequestURL() {

        return String.format("%s/types", this.xoDB.getRootUrl());
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
    public List<IType> convert(IRestResponse response) {

        JSONArray   array = new JSONArray(new String(response.getBody()));
        List<IType> types = new ArrayList<>();

        for (int i = 0 ; i < array.length() ; i++) {
            types.add(new Type(array.getJSONObject(i)));
        }

        return types;
    }
}
