package fr.alexpado.xodb4j.impl;

import fr.alexpado.xodb4j.XoDB;
import fr.alexpado.xodb4j.XoDBUtils;
import fr.alexpado.xodb4j.interfaces.*;
import fr.alexpado.xodb4j.interfaces.common.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class implementing the {@link IItem} interface.
 * <p>
 * All fields are read-only because these data aren't meant to be edited, as they come from the CrossoutDB's API.
 *
 * @author alexpado
 */
public class Item implements IItem {

    private final int           id;
    private final String        availableName;
    private final String        description;
    private final boolean       removed;
    private final boolean       meta;
    private final boolean       craftable;
    private final int           marketBuy;
    private final int           marketSell;
    private final int           craftingSellSum;
    private final int           craftingBuySum;
    private final LocalDateTime lastUpdate;
    private final IRarity       rarity;
    private final IType         type;
    private final ICategory     category;
    private final IFaction      faction;

    /**
     * Create a new {@link Item} instance.
     *
     * @param xoDB
     *         The {@link XoDB} instance to use when retrieving specific data about this {@link Item} sub-properties.
     * @param source
     *         JSON containing all values needed to create this {@link Item}.
     */
    public Item(XoDB xoDB, JSONObject source) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.id              = source.getInt("id");
        this.availableName   = source.getString("availableName");
        this.description     = source.get("description") == JSONObject.NULL ? "" : XoDBUtils.removeHTML(source.getString("description"));
        this.marketBuy       = source.getInt("buyPrice");
        this.marketSell      = source.getInt("sellPrice");
        this.craftingSellSum = source.getInt("craftingSellSum");
        this.craftingBuySum  = source.getInt("craftingBuySum");
        this.removed         = source.getInt("removed") == 1;
        this.meta            = source.getInt("meta") == 1;
        this.craftable       = source.getInt("craftable") == 1;
        this.lastUpdate      = LocalDateTime.parse(source.getString("lastUpdateTime"), formatter);

        this.rarity   = xoDB.getRarityCache().get(source.getInt("rarityId"));
        this.type     = xoDB.getTypeCache().get(source.getInt("typeId"));
        this.category = xoDB.getCategoryCache().get(source.getInt("categoryId"));
        this.faction  = xoDB.getFactionCache().get(source.getInt("factionNumber"));
    }

    /**
     * Checks this {@link Craftable}'s craftable state.
     *
     * @return True if craftable, false instead.
     */
    @Override
    public boolean isCraftable() {

        return this.craftable;
    }

    /**
     * Retrieve the amount of money needed to buy every {@link IItem} of this {@link Craftable}'s crafting recipe. The
     * value returned by this method should be ignored if {@link #isCraftable()} returns false.
     *
     * @return Amount of money needed to craft.
     */
    @Override
    public double getBuyCraftPrice() {

        return this.craftingBuySum;
    }

    /**
     * Retrieve the amount of money obtainable by selling every {@link IItem} of this {@link Craftable}'s crafting
     * recipe. The value returned by this method should be ignored if {@link #isCraftable()} returns false.
     *
     * @return Amount of money obtainable.
     */
    @Override
    public double getSellCraftPrice() {

        return this.craftingSellSum;
    }

    /**
     * Retrieve this {@link Describable}'s description.
     *
     * @return The description.
     */
    @Override
    public String getDescription() {

        return this.description;
    }

    /**
     * Retrieve this {@link Identifiable}'s ID.
     *
     * @return An ID.
     */
    @Override
    public Integer getId() {

        return this.id;
    }

    /**
     * Retrieve the amount of money needed to buy this {@link Marchantable}.
     *
     * @return The buy price
     */
    @Override
    public double getMarketSell() {

        return this.marketSell;
    }

    /**
     * Retrieve the amount of money obtainable by selling this {@link Marchantable}.
     *
     * @return The sell price
     */
    @Override
    public double getMarketBuy() {

        return this.marketBuy;
    }

    /**
     * Retrieve this {@link Nameable}'s name.
     *
     * @return The name.
     */
    @Override
    public String getName() {

        return this.availableName;
    }

    /**
     * Retrieve this {@link Updatable}'s last update time.
     *
     * @return The time
     */
    @Override
    public LocalDateTime getLastUpdate() {

        return this.lastUpdate;
    }

    /**
     * Check if this {@link IItem} has been removed from the game.
     *
     * @return True if removed, false instead.
     */
    @Override
    public boolean isRemoved() {

        return this.removed;
    }

    /**
     * Check if this {@link IItem} is a meta item.
     *
     * @return True if it's a meta item.
     */
    @Override
    public boolean isMeta() {

        return this.meta;
    }

    /**
     * Get the {@link IType} of this {@link IItem}.
     *
     * @return An {@link IType} instance.
     */
    @Override
    public IType getType() {

        return this.type;
    }

    /**
     * Get the {@link ICategory} of this {@link IItem}.
     *
     * @return An {@link ICategory} instance.
     */
    @Override
    public ICategory getCategory() {

        return this.category;
    }

    /**
     * Get the {@link IRarity} of this {@link IItem}.
     *
     * @return An {@link IRarity} instance.
     */
    @Override
    public IRarity getRarity() {

        return this.rarity;
    }

    /**
     * Get the {@link IFaction} of this {@link IItem}.
     *
     * @return An {@link IFaction} instance.
     */
    @Override
    public IFaction getFaction() {

        return this.faction;
    }

    /**
     * Compares this object with the specified object for order.  Returns a negative integer, zero, or a positive
     * integer as this object is less than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for all {@code x} and {@code y}.  (This implies that
     * {@code x.compareTo(y)} must throw an exception if and only if {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z)) == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o
     *         the object to be compared.
     *
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than
     *         the specified object.
     *
     * @throws NullPointerException
     *         if the specified object is null
     * @throws ClassCastException
     *         if the specified object's type prevents it from being compared to this object.
     */
    @Override
    public int compareTo(@NotNull IItem o) {

        return this.getName().compareTo(o.getName());
    }

}
