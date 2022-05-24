package fr.alexpado.xodb4j.repositories;

import fr.alexpado.lib.rest.interfaces.IRestAction;
import fr.alexpado.xodb4j.XoDB;
import fr.alexpado.xodb4j.interfaces.IRarity;
import fr.alexpado.xodb4j.repositories.rarity.FindAllRaritiesAction;

import java.util.List;

public class RarityRepository {

    private final XoDB xoDB;

    public RarityRepository(XoDB xoDB) {

        this.xoDB = xoDB;
    }

    /**
     * Retrieve every entity from the REST API of the current type.
     *
     * @return A {@link IRestAction}
     */
    public IRestAction<List<IRarity>> findAll() {

        return new FindAllRaritiesAction(this.xoDB);
    }

}
