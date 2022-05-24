package fr.alexpado.xodb4j.interfaces;

import fr.alexpado.xodb4j.interfaces.common.Creatable;
import fr.alexpado.xodb4j.interfaces.common.Identifiable;
import fr.alexpado.xodb4j.interfaces.common.Marchantable;

import java.time.LocalDateTime;

public interface IMarket extends Identifiable<Integer>, Marchantable, Creatable<LocalDateTime> {

    /**
     * Retrieve the {@link IItem} to which this {@link IMarket} is associated.
     *
     * @return The {@link IItem}.
     */
    IItem getItem();

    /**
     * Define the {@link IItem} to which this {@link IMarket} is associated.
     *
     * @param item
     *         The {@link IItem}.
     */
    default void setItem(IItem item) {

        throw new UnsupportedOperationException("Unable to set value: This value is read-only.");
    }

    /**
     * Retrieve the amount of sell offers of this {@link IMarket} entry.
     *
     * @return The amount of sell offers.
     */
    int getSellOffers();

    /**
     * Define the amount of sell offers of this {@link IMarket} entry.
     *
     * @param sellOffers
     *         The amount of sell offers.
     */
    default void setSellOffers(int sellOffers) {

        throw new UnsupportedOperationException("Unable to set value: This value is read-only.");
    }

    /**
     * Retrieve the amount of buy orders of this {@link IMarket} entry.
     *
     * @return The amount of sell offers.
     */
    int getBuyOrders();

    /**
     * Define the amount of buy orders of this {@link IMarket} entry.
     *
     * @param buyOrders
     *         The amount of buy orders.
     */
    default void setBuyOrders(int buyOrders) {

        throw new UnsupportedOperationException("Unable to set value: This value is read-only.");
    }

}
