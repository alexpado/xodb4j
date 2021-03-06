package fr.alexpado.xodb4j.interfaces.common;

/**
 * Interface representing an object holding an image url.
 *
 * @author alexpado
 */
public interface Imageable {

    /**
     * Retrieve this {@link Imageable}'s image url.
     *
     * @return An image url
     */
    String getImageUrl();

    /**
     * Define this {@link Imageable}'s image url.
     *
     * @param url
     *         An image url.
     */
    default void setImageUrl(String url) {

        throw new UnsupportedOperationException("Unable to set value: This value is read-only.");
    }

}
