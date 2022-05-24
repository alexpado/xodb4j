package fr.alexpado.xodb4j;

import fr.alexpado.xodb4j.interfaces.*;
import fr.alexpado.xodb4j.repositories.*;

import java.util.HashMap;
import java.util.Map;

public class XoDB {

    public static final String API_URL = "https://crossoutdb.com/api/v2";

    private final Map<Integer, IRarity>   rarityCache   = new HashMap<>();
    private final Map<Integer, IFaction>  factionCache  = new HashMap<>();
    private final Map<Integer, IType>     typeCache     = new HashMap<>();
    private final Map<Integer, ICategory> categoryCache = new HashMap<>();
    private final Map<Integer, IItem>     itemCache     = new HashMap<>();
    private final Map<Integer, IPack>     packCache     = new HashMap<>();

    /**
     * Retrieve the root url common to all endpoints.
     *
     * @return A string
     */
    public String getRootUrl() {

        return API_URL;
    }

    /**
     * Fill the cache with all entities from the API.
     *
     * @param full
     *         Define if <b>every</b> entity should be cached. If false, {@link IItem} and {@link IPack} will be
     *         excluded.
     */
    public void buildCaches(boolean full) throws Exception {

        this.rarityCache.clear();
        this.factionCache.clear();
        this.typeCache.clear();
        this.categoryCache.clear();
        this.packCache.clear();
        this.itemCache.clear();

        this.rarities().findAll().complete().forEach(rarity -> this.rarityCache.put(rarity.getId(), rarity));
        this.factions().findAll().complete().forEach(faction -> this.factionCache.put(faction.getId(), faction));
        this.types().findAll().complete().forEach(type -> this.typeCache.put(type.getId(), type));
        this.categories().findAll().complete().forEach(category -> this.categoryCache.put(category.getId(), category));

        if (full) {
            this.items().findAll().complete().forEach(item -> this.itemCache.put(item.getId(), item));
            this.packs().findAll().complete().forEach(pack -> this.packCache.put(pack.getId(), pack));
        }
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

        return new ItemRepository(this);
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
     * Retrieve the {@link IRarity} cache.
     *
     * @return A {@link Map}
     */
    public Map<Integer, IRarity> getRarityCache() {

        return this.rarityCache;
    }

    /**
     * Retrieve the {@link IFaction} cache.
     *
     * @return A {@link Map}
     */
    public Map<Integer, IFaction> getFactionCache() {

        return this.factionCache;
    }

    /**
     * Retrieve the {@link IType} cache.
     *
     * @return A {@link Map}
     */
    public Map<Integer, IType> getTypeCache() {

        return this.typeCache;
    }

    /**
     * Retrieve the {@link ICategory} cache.
     *
     * @return A {@link Map}
     */
    public Map<Integer, ICategory> getCategoryCache() {

        return this.categoryCache;
    }

    /**
     * Retrieve the {@link IItem} cache.
     *
     * @return A {@link Map}
     */
    public Map<Integer, IItem> getItemCache() {

        return this.itemCache;
    }

    /**
     * Retrieve the {@link IPack} cache.
     *
     * @return A {@link Map}
     */
    public Map<Integer, IPack> getPackCache() {

        return this.packCache;
    }

}
