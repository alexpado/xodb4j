package fr.alexpado.xodb4j.providers;

import fr.alexpado.xodb4j.interfaces.IType;

public interface TypeProvider {

    /**
     * Provide the {@link IType} matching the given id.
     *
     * @param id
     *         The identifier.
     *
     * @return An {@link IType} implementation instance.
     */
    IType provideType(Integer id);

}
