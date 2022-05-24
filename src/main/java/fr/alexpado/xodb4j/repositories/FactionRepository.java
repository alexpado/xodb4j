package fr.alexpado.xodb4j.repositories;

import fr.alexpado.lib.rest.interfaces.IRestAction;
import fr.alexpado.xodb4j.XoDB;
import fr.alexpado.xodb4j.interfaces.IFaction;
import fr.alexpado.xodb4j.repositories.faction.FindAllFactionsAction;

import java.util.List;

public class FactionRepository {

    private final XoDB xoDB;

    public FactionRepository(XoDB xoDB) {

        this.xoDB = xoDB;
    }

    /**
     * Retrieve every entity from the REST API of the current type.
     *
     * @return A {@link IRestAction}
     */
    public IRestAction<List<IFaction>> findAll() {

        return new FindAllFactionsAction(this.xoDB);
    }

}
