package fr.alexpado.xodb4j.repositories;

import fr.alexpado.lib.rest.interfaces.IRestAction;
import fr.alexpado.xodb4j.XoDB;
import fr.alexpado.xodb4j.interfaces.IPack;
import fr.alexpado.xodb4j.repositories.pack.FindAllPacksAction;

import java.util.List;

public class PackRepository {

    private final XoDB xoDB;

    public PackRepository(XoDB xoDB) {

        this.xoDB = xoDB;
    }

    /**
     * Retrieve every entity from the REST API of the current type.
     *
     * @return A {@link IRestAction}
     */
    public IRestAction<List<IPack>> findAll() {

        return new FindAllPacksAction(this.xoDB);
    }

}
