package fr.alexpado.xodb4j.impl;

import fr.alexpado.xodb4j.interfaces.IItem;
import fr.alexpado.xodb4j.interfaces.IRarity;
import fr.alexpado.xodb4j.interfaces.common.Colorable;
import fr.alexpado.xodb4j.interfaces.common.Identifiable;
import fr.alexpado.xodb4j.interfaces.common.Nameable;
import org.json.JSONObject;

import java.awt.*;
import java.util.Objects;

/**
 * Class implementing the {@link IRarity} interface representing an {@link IItem} rarity.
 * <p>
 * All fields are read-only because these data aren't meant to be edited, as they come from the CrossoutDB's API.
 *
 * @author alexpado
 */
public class Rarity implements IRarity {

    private final Integer id;
    private final String  name;
    private final Color   color;

    /**
     * Create a new {@link IRarity} instance.
     *
     * @param id
     *         The {@link IRarity}'s ID.
     * @param name
     *         The {@link IRarity}'s name.
     * @param color
     *         The {@link IRarity}'s color.
     */
    public Rarity(Integer id, String name, Color color) {

        this.id    = id;
        this.name  = name;
        this.color = color;
    }

    /**
     * Create a new {@link IRarity} instance.
     *
     * @param source
     *         JSON containing all values needed to create this {@link IRarity}.
     */
    public Rarity(JSONObject source) {

        this.id   = source.getInt("id");
        this.name = source.getString("name");

        String colorHex = source.getString("primarycolor");
        int    r        = Integer.parseInt(colorHex.substring(0, 2), 16);
        int    g        = Integer.parseInt(colorHex.substring(2, 4), 16);
        int    b        = Integer.parseInt(colorHex.substring(4, 6), 16);
        this.color = new Color(r, g, b);
    }


    /**
     * Retrieve this {@link Colorable}'s {@link Color} instance.
     *
     * @return A {@link Color}.
     */
    @Override
    public Color getColor() {

        return this.color;
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
     * Retrieve this {@link Nameable}'s name.
     *
     * @return The name.
     */
    @Override
    public String getName() {

        return this.name;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Rarity rarity = (Rarity) o;
        return Objects.equals(this.getId(), rarity.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(this.getId());
    }

    @Override
    public String toString() {

        return this.getName();
    }
}
