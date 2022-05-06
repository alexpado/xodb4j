package fr.alexpado.xodb4j;

import fr.alexpado.xodb4j.interfaces.IItem;
import fr.alexpado.xodb4j.interfaces.IPack;
import net.htmlparser.jericho.Renderer;
import net.htmlparser.jericho.Source;

import java.time.ZoneOffset;

public class XoDBUtils {

    /**
     * Retrieve the link redirecting the CrossoutDB website for the provided entity.
     *
     * @param item
     *         The {@link IItem} from which the link will be generated.
     *
     * @return The link.
     */
    public static String getWebLink(IItem item) {

        return String.format("https://crossoutdb.com/item/%s?ref=crossoutmarketbot", item.getId());
    }

    /**
     * Retrieve the link redirecting the CrossoutDB website for the provided entity.
     *
     * @param pack
     *         The {@link IPack} from which the link will be generated.
     *
     * @return The link.
     */
    public static String getWebLink(IPack pack) {

        return String.format("https://crossoutdb.com/packs?ref=crossoutmarketbot#%s", pack.getKey());
    }

    /**
     * Retrieve the link redirecting the CrossoutDB image for the provided entity.
     *
     * @param item
     *         The {@link IItem} from which the link will be generated.
     *
     * @return The link.
     */
    public static String getImage(IItem item) {

        return String.format("https://crossoutdb.com/img/items/%s.png?ref=crossoutmarketbot&time=%s", item.getId(), item.getLastUpdate()
                                                                                                                        .toEpochSecond(ZoneOffset.UTC));
    }

    /**
     * Retrieve the link redirecting the CrossoutDB image for the provided entity.
     *
     * @param pack
     *         The {@link IPack} from which the link will be generated.
     *
     * @return The link.
     */
    public static String getImage(IPack pack) {

        return String.format("https://crossoutdb.com/img/premiumpackages/%s.jpg", pack.getKey());
    }

    /**
     * Remove any HTML tag and clean up the text from the provided string.
     *
     * @param text
     *         The string to clean up.
     *
     * @return Sanitized string
     */
    public static String removeHTML(String text) {

        Source   htmlSource = new Source(text);
        Renderer htmlRend   = new Renderer(htmlSource);
        return htmlRend.toString().replace("\n", "").replace("\r", "").replace(".", ". ");
    }

}
