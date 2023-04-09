package fr.alexpado.xodb4j;

import fr.alexpado.lib.rest.RestAction;
import fr.alexpado.lib.rest.enums.RequestMethod;
import fr.alexpado.lib.rest.interfaces.IRestAction;
import fr.alexpado.xodb4j.cache.XoDBCache;
import fr.alexpado.xodb4j.interfaces.*;
import fr.alexpado.xodb4j.providers.composite.ItemProvider;
import fr.alexpado.xodb4j.repositories.*;
import org.jetbrains.annotations.NotNull;

public class XoDB {

    public static final String API_URL = "https://crossoutdb.com/api/v2";

    private ItemProvider provider;

    public XoDB() {

        this.provider = new XoDBCache(this);
    }

    /**
     * Define the new {@link ItemProvider} that will be used as cache.
     *
     * @param provider
     *         An {@link ItemProvider} implementation.
     */
    public void setProvider(ItemProvider provider) {

        this.provider = provider;
    }

    /**
     * Retrieve the current {@link ItemProvider} in use.
     *
     * @return An {@link ItemProvider} implementation.
     */
    public ItemProvider getProvider() {

        return provider;
    }

    /**
     * Retrieve the root url common to all endpoints.
     *
     * @return A string
     */
    public String getRootUrl() {

        return API_URL;
    }

    /**
     * Retrieve the {@link RarityRepository} allowing to query CrossoutDB for retrieving {@link IRarity}.
     *
     * @return A {@link RarityRepository} instance.
     */
    public RarityRepository rarities() {

        return new RarityRepository(this);
    }

    /**
     * Retrieve the {@link ItemRepository} allowing to query CrossoutDB for retrieving {@link IItem}.
     *
     * @return A {@link ItemRepository} instance.
     */
    public ItemRepository items() {

        return new ItemRepository(this, this.provider);
    }

    /**
     * Retrieve the {@link FactionRepository} allowing to query CrossoutDB for retrieving {@link IFaction}.
     *
     * @return A {@link FactionRepository} instance.
     */
    public FactionRepository factions() {

        return new FactionRepository(this);
    }

    /**
     * Retrieve the {@link TypeRepository} allowing to query CrossoutDB for retrieving {@link IType}.
     *
     * @return A {@link TypeRepository} instance.
     */
    public TypeRepository types() {

        return new TypeRepository(this);
    }

    /**
     * Retrieve the {@link CategoryRepository} allowing to query CrossoutDB for retrieving {@link ICategory}.
     *
     * @return A {@link CategoryRepository} instance.
     */
    public CategoryRepository categories() {

        return new CategoryRepository(this);
    }

    /**
     * Retrieve the {@link PackRepository} allowing to query CrossoutDB for retrieving {@link IPack}.
     *
     * @return A {@link PackRepository} instance.
     */
    public PackRepository packs() {

        return new PackRepository(this);
    }

    /**
     * Retrieve the {@link MarketRepository} allowing to query CrossoutDB for retrieving {@link IPack}.
     *
     * @return A {@link MarketRepository} instance.
     */
    public MarketRepository market(IItem item) {

        return new MarketRepository(this, item);
    }

    /**
     * Retrieve an {@link IRestAction} allowing a call to the health endpoint of CrossoutDB.
     *
     * @return An {@link IRestAction}.
     */
    public IRestAction<Void> health() {

        return new RestAction<>() {

            @Override
            public @NotNull RequestMethod getRequestMethod() {

                return RequestMethod.GET;
            }

            @Override
            public @NotNull String getRequestURL() {

                return "%s/health".formatted(API_URL);
            }
        };
    }

}
