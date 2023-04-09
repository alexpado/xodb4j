package fr.alexpado.xodb4j.providers;

import fr.alexpado.xodb4j.interfaces.IRarity;

public interface RarityProvider {

    /**
     * Provide the {@link IRarity} matching the given id.
     *
     * @param id
     *         The identifier.
     *
     * @return An {@link IRarity} implementation instance.
     */
    IRarity provideRarity(Integer id);

}
