package com.mwronski.ncache;

import com.google.common.base.Joiner;

import java.util.Random;

/**
 * Cache managing single region/namespace.
 * Cache is a wrapper for generic cache provider.
 * Clearing of namespace cache will cause cleansing of single namespace (not the whole cache region).
 *
 * @author Michal Wronski
 * @date 24.10.14.
 */
final class NamespaceCache implements Cache {

    private static final Random RANDOM = new Random();
    private final Cache cache;
    private final Namespace namespace;

    @Override
    public String getName() {
        return cache.getName();
    }

    @Override
    public <K, V> V get(K key) {
        return cache.get(namespaceKey(key));
    }

    @Override
    public <K, V> void put(K key, V value) {
        cache.put(namespaceKey(key), value);
    }

    @Override
    public <K> void evict(K key) {
        cache.evict(namespaceKey(key));
    }

    @Override
    public void clear() {
        cache.evict(nCacheKey());
    }

    /**
     * Create key in current namespace
     *
     * @param key key that should be created in current namespace
     * @param <K> type of key
     * @return non-nullable key
     */
    private <K> String namespaceKey(K key) {
        Integer namespaceValue = cache.get(nCacheKey());
        if (namespaceValue == null) {
            namespaceValue = RANDOM.nextInt(5000);
            cache.put(nCacheKey(), namespaceValue);
        }
        return Joiner.on('.').join(nCacheKey(), namespaceValue, key.hashCode());
    }

    /**
     * Get cache key of the whole namespace
     *
     * @return cache key
     */
    private int nCacheKey() {
        return namespace.hashCode();
    }

    /**
     * Create instance
     *
     * @param cache     cache to be wrapped
     * @param namespace chosen region of the cache
     */
    NamespaceCache(Cache cache, Namespace namespace) {
        this.cache = cache;
        this.namespace = namespace;
    }

}
