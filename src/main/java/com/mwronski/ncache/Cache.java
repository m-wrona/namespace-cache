package com.mwronski.ncache;

/**
 * Interface for wrapping any cache provider.
 *
 * @author Michal Wronski
 * @date 24.10.14.
 */
public interface Cache {

    /**
     * Get cache name
     *
     * @return non-nullable string
     */
    String getName();

    /**
     * Get cache value
     *
     * @param key key where value is stored
     * @param <K> type of key
     * @param <V> type of value
     * @return
     */
    <K, V> V get(K key);

    /**
     * Put value into cache
     *
     * @param key   key where value should be stored
     * @param value value to be saved
     * @param <K>   type of key
     * @param <V>   type of value
     */
    <K, V> void put(K key, V value);

    /**
     * Evict values under chosen key
     *
     * @param key key where values will be invalidated
     * @param <K> type of key
     */
    <K> void evict(K key);

    /**
     * Clear whole cache
     */
    void clear();

}
