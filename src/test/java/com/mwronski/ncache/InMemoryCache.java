package com.mwronski.ncache;

import java.util.HashMap;
import java.util.Map;

/**
 * Dummy implementation of in-memory cache for testing purposes
 *
 * @author Michal Wronski
 * @date 24.10.14.
 */
final class InMemoryCache implements Cache {

    private final String cacheName;
    private final Map<Object, Object> values = new HashMap<>();

    @Override
    public String getName() {
        return cacheName;
    }

    @Override
    public <K, V> V get(K key) {
        return (V) values.get(key);
    }

    @Override
    public <K, V> void put(K key, V value) {
        values.put(key, value);
    }

    @Override
    public <K> void evict(K key) {
        values.remove(key);
    }

    @Override
    public void clear() {
        values.clear();
    }

    InMemoryCache(String cacheName) {
        this.cacheName = cacheName;
    }
}
