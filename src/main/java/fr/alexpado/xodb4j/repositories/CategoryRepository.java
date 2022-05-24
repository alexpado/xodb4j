package fr.alexpado.xodb4j.repositories;

import fr.alexpado.lib.rest.interfaces.IRestAction;
import fr.alexpado.xodb4j.XoDB;
import fr.alexpado.xodb4j.interfaces.ICategory;
import fr.alexpado.xodb4j.repositories.category.FindAllCategoriesAction;

import java.util.List;

public class CategoryRepository {

    private final XoDB xoDB;

    public CategoryRepository(XoDB xoDB) {

        this.xoDB = xoDB;
    }

    /**
     * Retrieve every entity from the REST API of the current type.
     *
     * @return A {@link IRestAction}
     */
    public IRestAction<List<ICategory>> findAll() {

        return new FindAllCategoriesAction(this.xoDB);
    }

}
