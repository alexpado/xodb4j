package fr.alexpado.xodb4j.repositories.category;

import fr.alexpado.lib.rest.RestAction;
import fr.alexpado.lib.rest.enums.RequestMethod;
import fr.alexpado.lib.rest.interfaces.IRestResponse;
import fr.alexpado.xodb4j.XoDB;
import fr.alexpado.xodb4j.impl.Category;
import fr.alexpado.xodb4j.interfaces.ICategory;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class FindAllCategoriesAction extends RestAction<List<ICategory>> {

    private final XoDB xoDB;

    public FindAllCategoriesAction(XoDB xoDB) {

        this.xoDB = xoDB;
    }

    @Override
    public @NotNull RequestMethod getRequestMethod() {

        return RequestMethod.GET;
    }

    @Override
    public @NotNull String getRequestURL() {

        return String.format("%s/categories", this.xoDB.getRootUrl());
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
    public List<ICategory> convert(IRestResponse response) {

        JSONArray       array      = new JSONArray(new String(response.getBody()));
        List<ICategory> categories = new ArrayList<>();

        for (int i = 0 ; i < array.length() ; i++) {
            categories.add(new Category(array.getJSONObject(i)));
        }

        return categories;
    }

}
