package fr.alexpado.xodb4j.interfaces;

import fr.alexpado.xodb4j.interfaces.common.Creatable;
import fr.alexpado.xodb4j.interfaces.common.Identifiable;
import fr.alexpado.xodb4j.interfaces.common.Merchantable;

import java.time.LocalDateTime;

public interface IMarket extends Identifiable<Integer>, Merchantable, Creatable<LocalDateTime> {

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

}
