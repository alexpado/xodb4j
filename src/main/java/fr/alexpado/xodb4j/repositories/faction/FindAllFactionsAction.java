package fr.alexpado.xodb4j.repositories.faction;

import fr.alexpado.lib.rest.RestAction;
import fr.alexpado.lib.rest.enums.RequestMethod;
import fr.alexpado.lib.rest.interfaces.IRestResponse;
import fr.alexpado.xodb4j.XoDB;
import fr.alexpado.xodb4j.impl.Faction;
import fr.alexpado.xodb4j.interfaces.IFaction;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class FindAllFactionsAction extends RestAction<List<IFaction>> {

    private final XoDB xoDB;

    public FindAllFactionsAction(XoDB xoDB) {

        this.xoDB = xoDB;
    }

    @Override
    public @NotNull RequestMethod getRequestMethod() {

        return RequestMethod.GET;
    }

    @Override
    public @NotNull String getRequestURL() {

        return String.format("%s/factions", this.xoDB.getRootUrl());
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
    public List<IFaction> convert(IRestResponse response) {

        JSONArray      array    = new JSONArray(new String(response.getBody()));
        List<IFaction> factions = new ArrayList<>();

        for (int i = 0 ; i < array.length() ; i++) {
            factions.add(new Faction(array.getJSONObject(i)));
        }

        return factions;
    }
}
