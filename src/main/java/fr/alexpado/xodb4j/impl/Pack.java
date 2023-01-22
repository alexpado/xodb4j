package fr.alexpado.xodb4j.impl;

import fr.alexpado.xodb4j.interfaces.IPack;
import fr.alexpado.xodb4j.interfaces.common.Identifiable;
import fr.alexpado.xodb4j.interfaces.common.Merchantable;
import fr.alexpado.xodb4j.interfaces.common.Nameable;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

public class Pack implements IPack {

    private final Integer id;
    private final String  key;
    private final String  name;
    private final Double  sellPrice;
    private final Double  buyPrice;
    private final Double  rawCoins;
    private       Double  priceUsd;
    private       Double  priceEur;
    private       Double  priceGbp;
    private       Double  priceRub;

    public Pack(JSONObject source) {

        this.id        = source.getInt("id");
        this.key       = source.getString("key");
        this.name      = source.getString("name");
        this.rawCoins  = source.getDouble("rawcoins");
        this.sellPrice = source.getDouble("sellsum") / 100;
        this.buyPrice  = source.getDouble("buysum") / 100;

        JSONObject appPrices = source.getJSONObject("appprices");
        JSONArray  array     = appPrices.getJSONArray("prices");

        for (int i = 0 ; i < array.length() ; i++) {
            JSONObject priceSource = array.getJSONObject(i);
            double     value       = priceSource.getDouble("final");

            switch (priceSource.getString("currencyabbriviation").toLowerCase()) {
                case "usd" -> this.priceUsd = value;
                case "eur" -> this.priceEur = value;
                case "gbp" -> this.priceGbp = value;
                case "rub" -> this.priceRub = value;
            }
        }
    }

    /**
     * Retrieve this {@link IPack}'s key identifier.
     *
     * @return A string
     */
    @Override
    public String getKey() {

        return this.key;
    }

    /**
     * Retrieve this {@link IPack}'s price in USD.
     *
     * @return The price
     */
    @Override
    public Double getPriceUSD() {

        return this.priceUsd;
    }

    /**
     * Retrieve this {@link IPack}'s price in EUR.
     *
     * @return The price
     */
    @Override
    public Double getPriceEUR() {

        return this.priceEur;
    }

    /**
     * Retrieve this {@link IPack}'s price in GBP.
     *
     * @return The price
     */
    @Override
    public Double getPriceGBP() {

        return this.priceGbp;
    }

    /**
     * Retrieve this {@link IPack}'s price in RUB.
     *
     * @return The price
     */
    @Override
    public Double getPriceRUB() {

        return this.priceRub;
    }

    /**
     * Retrieve this {@link IPack}'s value in coins.
     *
     * @return The value
     */
    @Override
    public Double getRawCoins() {

        return this.rawCoins;
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

    /**
     * Retrieve the amount of sell offers available for this {@link Merchantable}.
     *
     * @return The sell offers amount.
     */
    @Override
    public int getSellOffers() {

        throw new UnsupportedOperationException("Packs do not have sell offers.");
    }

    /**
     * Retrieve the amount of buy orders available for this {@link Merchantable}.
     *
     * @return The buy orders amount.
     */
    @Override
    public int getBuyOrders() {

        throw new UnsupportedOperationException("Packs do not have buy orders.");
    }

    /**
     * Retrieve this {@link Nameable}'s name.
     *
     * @return The name.
     */
    @Override
    public String getName() {

        return this.name;
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
    public int compareTo(@NotNull IPack o) {

        return this.getName().compareTo(o.getName());
    }

}
