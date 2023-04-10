package fr.alexpado.xodb4j.impl;

import fr.alexpado.xodb4j.interfaces.IItem;
import fr.alexpado.xodb4j.interfaces.IMarket;
import fr.alexpado.xodb4j.interfaces.common.Creatable;
import fr.alexpado.xodb4j.interfaces.common.Identifiable;
import fr.alexpado.xodb4j.interfaces.common.Merchantable;
import org.json.JSONArray;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Market implements IMarket {

    private final int           id;
    private final IItem         item;
    private final double        sellPrice;
    private final double        buyPrice;
    private final int           sellOffers;
    private final int           buyOrders;
    private final LocalDateTime createdAt;

    public Market(IItem item, JSONArray array) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.item       = item;
        this.id         = array.getInt(0);
        this.sellPrice  = array.getInt(1) / 100.0d;
        this.buyPrice   = array.getInt(2) / 100.0d;
        this.sellOffers = array.getInt(3);
        this.buyOrders  = array.getInt(4);
        this.createdAt  = LocalDateTime.parse(array.getString(5), formatter);
    }

    /**
     * Retrieve the {@link IItem} to which this {@link IMarket} is associated.
     *
     * @return The {@link IItem}.
     */
    @Override
    public IItem getItem() {

        return this.item;
    }

    /**
     * Retrieve the amount of sell offers of this {@link IMarket} entry.
     *
     * @return The amount of sell offers.
     */
    @Override
    public int getSellOffers() {

        return this.sellOffers;
    }

    /**
     * Retrieve the amount of buy orders of this {@link IMarket} entry.
     *
     * @return The amount of sell offers.
     */
    @Override
    public int getBuyOrders() {

        return this.buyOrders;
    }

    /**
     * Retrieve this {@link Creatable}'s creation time.
     *
     * @return The time
     */
    @Override
    public LocalDateTime getCreatedAt() {

        return this.createdAt;
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
     * Retrieve the amount of money needed to buy this {@link Merchantable}.
     *
     * @return The buy price
     */
    @Override
    public double getMarketSell() {

        return this.sellPrice;
    }

    /**
     * Retrieve the amount of money obtainable by selling this {@link Merchantable}.
     *
     * @return The sell price
     */
    @Override
    public double getMarketBuy() {

        return this.buyPrice;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Market market = (Market) o;
        return Objects.equals(this.getId(), market.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(this.getId());
    }
}
