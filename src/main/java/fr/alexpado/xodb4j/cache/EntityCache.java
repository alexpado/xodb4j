package fr.alexpado.xodb4j.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Class allowing to create a very simple and easy to use cache. The element class used should implement its own
 * {@link #hashCode()} and {@link #equals(Object)} methods (see {@link Map})
 *
 * @param <T>
 *         Type of the elements that will be stored in this cache.
 */
public class EntityCache<T> {

    private final Map<T, Long> cache;
    private final long         cacheTimeout;

    /**
     * Create a new instance of EntityCache.
     */
    public EntityCache() {

        this.cache        = new HashMap<>();
        this.cacheTimeout = 20_000; // 20s
    }

    /**
     * Create a new instance of EntityCache with a specific cache timeout.
     *
     * @param cacheTimeout
     *         The duration, in ms, after which each element will be considered as outdated.
     */
    public EntityCache(long cacheTimeout) {

        this.cache        = new HashMap<>();
        this.cacheTimeout = cacheTimeout;
    }

    /**
     * Remove all element that can be considered as outdated from the cache.
     */
    private void prune() {

        long current = System.currentTimeMillis();
        this.cache.keySet().stream()
                  .filter(entity -> this.cache.get(entity) < current - this.cacheTimeout) // Filter outdated keys
                  .forEach(this.cache::remove); // Remove them from the cache.
    }

    /**
     * Query the cache to retrieve an element. If the required element isn't found in the cache, the supplier will be
     * called and its return value added into the cache.
     * <p>
     * It is expected, <b>but not enforced</b>, that the supplier return value match the predicate used.
     *
     * @param cacheSelector
     *         The predicate to use to filter out element from the cache.
     * @param cacheSupplier
     *         The supplier to use if the filter above did not match any element from the cache.
     *
     * @return An element.
     */
    public T query(Predicate<T> cacheSelector, Supplier<T> cacheSupplier) {

        this.prune();
        Optional<T> result = this.cache.keySet().stream().filter(cacheSelector).findFirst();

        if (result.isPresent()) {
            return result.get();
        }

        T item = cacheSupplier.get();
        this.cache.put(item, System.currentTimeMillis());

        return item;
    }

    /**
     * Query the cache to retrieve all elements matching the provided predicated.
     *
     * @param cacheSelector
     *         The predicate to use to filter out element from the cache.
     *
     * @return A possibly empty element list.
     */
    public List<T> queryAll(Predicate<T> cacheSelector) {

        this.prune();
        return this.cache.keySet().stream().filter(cacheSelector).collect(Collectors.toList());
    }
}
