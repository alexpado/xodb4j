package fr.alexpado.xodb4j.repositories;

import fr.alexpado.lib.rest.interfaces.IRestAction;
import fr.alexpado.xodb4j.XoDB;
import fr.alexpado.xodb4j.interfaces.IItem;
import fr.alexpado.xodb4j.providers.composite.ItemProvider;
import fr.alexpado.xodb4j.repositories.item.FindAllItemsAction;
import fr.alexpado.xodb4j.repositories.item.FindItemByIdAction;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemRepository {

    private final XoDB         xoDB;
    private final ItemProvider provider;

    public ItemRepository(XoDB xoDB, ItemProvider provider) {

        this.xoDB     = xoDB;
        this.provider = provider;
    }

    /**
     * Retrieve one entity of the current type identifiable by its ID.
     *
     * @param id
     *         The entity's identifier.
     *
     * @return A {@link IRestAction}
     */
    public @NotNull IRestAction<IItem> findById(@NotNull Integer id) {

        return this.findById(id, new HashMap<>());
    }

    /**
     * Retrieve one entity of the current type identifiable by its ID.
     *
     * @param id
     *         The entity's identifier.
     * @param urlParams
     *         Map representing the URL parameters
     *
     * @return A {@link IRestAction}
     */
    public @NotNull IRestAction<IItem> findById(@NotNull Integer id, Map<String, String> urlParams) {

        return new FindItemByIdAction(this.xoDB, provider, id, urlParams);
    }

    /**
     * Retrieve every entity from the REST API of the current type.
     *
     * @return A {@link IRestAction}
     */
    public IRestAction<List<IItem>> findAll() {

        return new FindAllItemsAction(xoDB, this.provider);
    }

    /**
     * Retrieve every entity of the current type matching the query represented by the provided map.
     *
     * @param meta
     *         A map of key-value that will be used to query the API.
     *
     * @return A {@link IRestAction}.
     */
    public IRestAction<List<IItem>> findAll(Map<String, Object> meta) {

        return new FindAllItemsAction(this.xoDB, this.provider, meta);
    }
}
