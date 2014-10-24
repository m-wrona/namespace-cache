package com.mwronski.ncache;

/**
 * Generic cache wrapper with name-spacing capability.
 * Each create namespace in cache is a separate region.
 *
 * @author Michal Wronski
 * @date 24.10.14.
 */
public final class NCache implements Cache {

    private final Cache cache;

    @Override
    public String getName() {
        return cache.getName();
    }

    @Override
    public <K, V> V get(K key) {
        return cache.get(key);
    }

    @Override
    public <K, V> void put(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public <K> void evict(K key) {
        cache.evict(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    /**
     * Create namespace in current cache
     *
     * @param name       name of the namespace
     * @param attributes namespace optional attributes
     * @return new instance of cache region
     */
    public Cache namespace(String name, Object... attributes) {
        return new NamespaceCache(cache, new Namespace(name, attributes));
    }

    /**
     * Wrap cache in order to provide name-spacing
     *
     * @param cache cache to be wrapped
     * @return new instance
     */
    public static NCache nCache(Cache cache) {
        return new NCache(cache);
    }

    /**
     * Create new instance
     *
     * @param cache cache to be wrapped
     */
    private NCache(Cache cache) {
        this.cache = cache;
    }

}
