package fr.alexpado.xodb4j.providers;

import fr.alexpado.xodb4j.interfaces.IFaction;

public interface FactionProvider {

    /**
     * Provide the {@link IFaction} matching the given id.
     *
     * @param id
     *         The identifier.
     *
     * @return An {@link IFaction} implementation instance.
     */
    IFaction provideFaction(Integer id);

}
