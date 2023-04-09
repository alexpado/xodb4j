package fr.alexpado.xodb4j.cache;

import fr.alexpado.xodb4j.XoDB;
import fr.alexpado.xodb4j.interfaces.ICategory;
import fr.alexpado.xodb4j.interfaces.IFaction;
import fr.alexpado.xodb4j.interfaces.IRarity;
import fr.alexpado.xodb4j.interfaces.IType;
import fr.alexpado.xodb4j.providers.composite.ItemProvider;

import java.util.Objects;
import java.util.function.Predicate;

public class XoDBCache implements ItemProvider {

    private final XoDB                   xoDB;
    private final EntityCache<ICategory> categoryCache;
    private final EntityCache<IFaction>  factionCache;
    private final EntityCache<IRarity>   rarityCache;
    private final EntityCache<IType>     typeCache;

    public XoDBCache(XoDB xoDB) {

        this.xoDB          = xoDB;
        this.categoryCache = new EntityCache<>();
        this.factionCache  = new EntityCache<>();
        this.rarityCache   = new EntityCache<>();
        this.typeCache     = new EntityCache<>();
    }

    /**
     * Provide the {@link ICategory} matching the given id.
     *
     * @param id
     *         The identifier.
     *
     * @return An {@link ICategory} implementation instance.
     */
    @Override
    public ICategory provideCategory(Integer id) {

        Predicate<ICategory> finder = el -> Objects.equals(el.getId(), id);

        return this.categoryCache.query(finder, () -> {
            try {
                return this.xoDB.categories().findAll().complete().stream()
                                .filter(finder)
                                .findFirst()
                                .orElse(ICategory.DEFAULT);
            } catch (Exception e) {
                return ICategory.DEFAULT;
            }
        });
    }

    /**
     * Provide the {@link IFaction} matching the given id.
     *
     * @param id
     *         The identifier.
     *
     * @return An {@link IFaction} implementation instance.
     */
    @Override
    public IFaction provideFaction(Integer id) {

        Predicate<IFaction> finder = el -> Objects.equals(el.getId(), id);

        return this.factionCache.query(finder, () -> {
            try {
                return this.xoDB.factions().findAll().complete().stream()
                                .filter(finder)
                                .findFirst()
                                .orElse(IFaction.DEFAULT);
            } catch (Exception e) {
                return IFaction.DEFAULT;
            }
        });
    }

    /**
     * Provide the {@link IRarity} matching the given id.
     *
     * @param id
     *         The identifier.
     *
     * @return An {@link IRarity} implementation instance.
     */
    @Override
    public IRarity provideRarity(Integer id) {

        Predicate<IRarity> finder = el -> Objects.equals(el.getId(), id);

        return this.rarityCache.query(finder, () -> {
            try {
                return this.xoDB.rarities().findAll().complete().stream()
                                .filter(finder)
                                .findFirst()
                                .orElse(IRarity.DEFAULT);
            } catch (Exception e) {
                return IRarity.DEFAULT;
            }
        });
    }

    /**
     * Provide the {@link IType} matching the given id.
     *
     * @param id
     *         The identifier.
     *
     * @return An {@link IType} implementation instance.
     */
    @Override
    public IType provideType(Integer id) {

        Predicate<IType> finder = el -> Objects.equals(el.getId(), id);

        return this.typeCache.query(finder, () -> {
            try {
                return this.xoDB.types().findAll().complete().stream()
                                .filter(finder)
                                .findFirst()
                                .orElse(IType.DEFAULT);
            } catch (Exception e) {
                return IType.DEFAULT;
            }
        });
    }
}
