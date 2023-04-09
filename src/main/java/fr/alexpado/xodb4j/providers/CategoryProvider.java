package fr.alexpado.xodb4j.providers;

import fr.alexpado.xodb4j.interfaces.ICategory;

public interface CategoryProvider {

    /**
     * Provide the {@link ICategory} matching the given id.
     *
     * @param id
     *         The identifier.
     *
     * @return An {@link ICategory} implementation instance.
     */
    ICategory provideCategory(Integer id);

}
