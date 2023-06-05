package it.unipd.dei.eis.core.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Cache is the abstract class for caches.
 *
 * @param <K>
 * @param <V>
 */
public abstract class Cache<K, V> {

    /**
     * The cache.
     */
    private final Map<K, V> cache = new HashMap<>();

    /**
     * Returns true if the cache contains the key.
     *
     * @param key The key
     * @return True if the cache contains the key
     */
    public boolean isCached(K key) {
        return cache.containsKey(key);
    }

    /**
     * Returns the value associated with the key.
     *
     * @param key The key
     * @return The value
     */
    public V getCache(K key) {
        return cache.get(key);
    }

    /**
     * Puts the key-value pair in the cache.
     *
     * @param key   The key
     * @param value The value
     */
    public void putCache(K key, V value) {
        cache.put(key, value);
    }
}
