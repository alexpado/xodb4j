package fr.alexpado.xodb4j.impl;

import fr.alexpado.xodb4j.interfaces.ICategory;
import fr.alexpado.xodb4j.interfaces.IItem;
import fr.alexpado.xodb4j.interfaces.common.Identifiable;
import fr.alexpado.xodb4j.interfaces.common.Nameable;
import org.json.JSONObject;

import java.util.Objects;

/**
 * Class implementing the {@link ICategory} interface representing an {@link IItem} category.
 * <p>
 * All fields are read-only because these data aren't meant to be edited, as they come from the CrossoutDB's API.
 *
 * @author alexpado
 */
public class Category implements ICategory {

    private final Integer id;
    private final String  name;

    /**
     * Create a new {@link ICategory} instance.
     *
     * @param id
     *         The {@link ICategory}'s ID.
     * @param name
     *         The {@link ICategory}'s name.
     */
    public Category(Integer id, String name) {

        this.id   = id;
        this.name = name;
    }

    /**
     * Create a new {@link ICategory} instance.
     *
     * @param source
     *         JSON containing all values needed to create this {@link ICategory}.
     */
    public Category(JSONObject source) {

        this.id   = source.getInt("id");
        this.name = source.getString("name");
    }

    /**
     * Retrieve this {@link Identifiable}'s ID.
     *
     * @return The ID.
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

        Category category = (Category) o;
        return Objects.equals(this.getId(), category.getId());
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
