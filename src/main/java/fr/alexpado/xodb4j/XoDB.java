package fr.alexpado.xodb4j;

import fr.alexpado.xodb4j.interfaces.*;
import fr.alexpado.xodb4j.repositories.*;

import java.util.HashMap;
import java.util.Map;

public class XoDB {

    private final Map<Integer, IRarity>   rarityCache   = new HashMap<>();
    private final Map<Integer, IFaction>  factionCache  = new HashMap<>();
    private final Map<Integer, IType>     typeCache     = new HashMap<>();
    private final Map<Integer, ICategory> categoryCache = new HashMap<>();
    private final Map<Integer, IItem>     itemCache     = new HashMap<>();
    private final Map<Integer, IPack>     packCache     = new HashMap<>();

    /**
     * Create a new {@link XoDB} instance.
     *
     * @throws Exception
     *         Thrown if the cache couldn't be built.
     */
    public XoDB() throws Exception {

        this.buildCaches(false);
    }

    /**
     * Retrieve the root url common to all endpoints.
     *
     * @return A string
     */
    public String getRootUrl() {

        return "https://crossoutdb.com/api/v2";
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
     * Retrieve the {@link RestRepository} allowing to query CrossoutDB for retrieving {@link IRarity}.
     *
     * @return A {@link RestRepository} instance.
     */
    public RestRepository<IRarity, Integer> rarities() {

        return new RarityRepository(this);
    }

    /**
     * Retrieve the {@link RestRepository} allowing to query CrossoutDB for retrieving {@link IItem}.
     *
     * @return A {@link RestRepository} instance.
     */
    public RestRepository<IItem, Integer> items() {

        return new ItemRepository(this);
    }

    /**
     * Retrieve the {@link RestRepository} allowing to query CrossoutDB for retrieving {@link IFaction}.
     *
     * @return A {@link RestRepository} instance.
     */
    public RestRepository<IFaction, Integer> factions() {

        return new FactionRepository(this);
    }

    /**
     * Retrieve the {@link RestRepository} allowing to query CrossoutDB for retrieving {@link IType}.
     *
     * @return A {@link RestRepository} instance.
     */
    public RestRepository<IType, Integer> types() {

        return new TypeRepository(this);
    }

    /**
     * Retrieve the {@link RestRepository} allowing to query CrossoutDB for retrieving {@link ICategory}.
     *
     * @return A {@link RestRepository} instance.
     */
    public RestRepository<ICategory, Integer> categories() {

        return new CategoryRepository(this);
    }

    /**
     * Retrieve the {@link RestRepository} allowing to query CrossoutDB for retrieving {@link IPack}.
     *
     * @return A {@link RestRepository} instance.
     */
    public RestRepository<IPack, Integer> packs() {

        return new PackRepository(this);
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
