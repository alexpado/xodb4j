package fr.alexpado.xodb4j.repositories;

import fr.alexpado.lib.rest.interfaces.IRestAction;
import fr.alexpado.xodb4j.XoDB;
import fr.alexpado.xodb4j.interfaces.IItem;
import fr.alexpado.xodb4j.interfaces.IMarket;
import fr.alexpado.xodb4j.repositories.market.FindPriceAction;

import java.time.LocalDateTime;
import java.util.List;

public class MarketRepository {

    private final XoDB  xoDB;
    private final IItem item;

    public MarketRepository(XoDB xoDB, IItem item) {

        this.xoDB = xoDB;
        this.item = item;
    }

    /**
     * Retrieve every {@link IMarket} from the REST API for the provided {@link IItem}.
     * <p>
     * <b>Note:</b> This call will take time &amp; will be heavy on memory, please be mindful.
     *
     * @return A {@link IRestAction}
     */
    public IRestAction<List<IMarket>> findAll() {

        return new FindPriceAction(this.xoDB, this.item, null, null);
    }

    /**
     * Retrieve every {@link IMarket} from the REST API for the provided {@link IItem} that are before the provided
     * {@link LocalDateTime}.
     *
     * @param endTime
     *         The ending {@link LocalDateTime}.
     *
     * @return A {@link IRestAction}
     */
    public IRestAction<List<IMarket>> findAllBefore(LocalDateTime endTime) {

        return new FindPriceAction(this.xoDB, this.item, null, endTime);
    }

    /**
     * Retrieve every {@link IMarket} from the REST API for the provided {@link IItem} that are after the provided
     * {@link LocalDateTime}.
     *
     * @param startTime
     *         The starting {@link LocalDateTime}.
     *
     * @return A {@link IRestAction}
     */
    public IRestAction<List<IMarket>> findAllAfter(LocalDateTime startTime) {

        return new FindPriceAction(this.xoDB, this.item, startTime, null);
    }

    /**
     * Retrieve every {@link IMarket} from the REST API for the provided {@link IItem} that are between the two provided
     * {@link LocalDateTime}.
     *
     * @param startTime
     *         The starting {@link LocalDateTime}.
     * @param endTime
     *         The ending {@link LocalDateTime}.
     *
     * @return A {@link IRestAction}
     */
    public IRestAction<List<IMarket>> findAllBetween(LocalDateTime startTime, LocalDateTime endTime) {

        return new FindPriceAction(this.xoDB, this.item, startTime, endTime);
    }

}
