package fr.alexpado.xodb4j.interfaces.common;

/**
 * Interface representing an object holding market data.
 *
 * @author alexpado
 */
public interface Merchantable {

    /**
     * Retrieve the amount of money needed to buy this {@link Merchantable}.
     *
     * @return The buy price
     */
    double getMarketSell();

    /**
     * Define the amount of money needed to buy this {@link Merchantable}.
     *
     * @param price
     *         The buy price.
     */
    default void setMarketSell(double price) {

        throw new UnsupportedOperationException("Unable to set value: This value is read-only.");
    }

    /**
     * Retrieve the amount of money obtainable by selling this {@link Merchantable}.
     *
     * @return The sell price
     */
    double getMarketBuy();

    /**
     * Define the amount of money obtainable by selling this {@link Merchantable}.
     *
     * @param price
     *         The sell price
     */
    default void setMarketBuy(double price) {

        throw new UnsupportedOperationException("Unable to set value: This value is read-only.");
    }

    /**
     * Retrieve the amount of sell offers available for this {@link Merchantable}.
     *
     * @return The sell offers amount.
     */
    int getSellOffers();

    /**
     * Define the amount of sell offers available for this {@link Merchantable}.
     *
     * @param sellOffers
     *         The sell offers amount.
     */
    default void setSellOffers(int sellOffers) {

        throw new UnsupportedOperationException("Unable to set value: This value is read-only.");
    }

    /**
     * Retrieve the amount of buy orders available for this {@link Merchantable}.
     *
     * @return The buy orders amount.
     */
    int getBuyOrders();

    /**
     * Define the amount of buy orders available for this {@link Merchantable}.
     *
     * @param buyOrders
     *         The buy orders amount.
     */
    default void setBuyOrders(int buyOrders) {

        throw new UnsupportedOperationException("Unable to set value: This value is read-only.");
    }

}
