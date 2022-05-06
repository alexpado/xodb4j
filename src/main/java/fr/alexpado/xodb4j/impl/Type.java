package fr.alexpado.xodb4j.impl;

import fr.alexpado.xodb4j.interfaces.IItem;
import fr.alexpado.xodb4j.interfaces.IType;
import fr.alexpado.xodb4j.interfaces.common.Identifiable;
import fr.alexpado.xodb4j.interfaces.common.Nameable;
import org.json.JSONObject;

/**
 * Class implementing the {@link IType} interface representing an {@link IItem} faction.
 * <p>
 * All fields are read-only because these data aren't meant to be edited, as they come from the CrossoutDB's API.
 *
 * @author alexpado
 */
public class Type implements IType {

    private final Integer id;
    private final String  name;

    /**
     * Create a new {@link IType} instance.
     *
     * @param id
     *         The {@link IType}'s ID.
     * @param name
     *         The {@link IType}'s name.
     */
    public Type(Integer id, String name) {

        this.id   = id;
        this.name = name;
    }

    /**
     * Create a new {@link IType} instance.
     *
     * @param source
     *         JSON containing all values needed to create this {@link IType}
     */
    public Type(JSONObject source) {

        this.id   = source.getInt("id");
        this.name = source.getString("name");
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
    public String toString() {

        return this.getName();
    }
}
