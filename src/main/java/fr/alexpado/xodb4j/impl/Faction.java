package fr.alexpado.xodb4j.impl;

import fr.alexpado.xodb4j.interfaces.IFaction;
import fr.alexpado.xodb4j.interfaces.IItem;
import fr.alexpado.xodb4j.interfaces.common.Identifiable;
import fr.alexpado.xodb4j.interfaces.common.Nameable;
import org.json.JSONObject;

/**
 * Class implementing the {@link IFaction} interface representing an {@link IItem} faction.
 * <p>
 * All fields are read-only because these data aren't meant to be edited, as they come from the CrossoutDB's API.
 *
 * @author alexpado
 */
public class Faction implements IFaction {

    private final Integer id;
    private final String  name;

    /**
     * Create a new {@link IFaction} instance.
     *
     * @param id
     *         The {@link IFaction}'s ID.
     * @param name
     *         The {@link IFaction}'s name.
     */
    public Faction(Integer id, String name) {

        this.id   = id;
        this.name = name;
    }

    /**
     * Create a new {@link IFaction} instance.
     *
     * @param source
     *         JSON containing all values needed to create this {@link IFaction}.
     */
    public Faction(JSONObject source) {

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
