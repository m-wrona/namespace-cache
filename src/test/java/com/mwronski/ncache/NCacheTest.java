package com.mwronski.ncache;

import static com.mwronski.ncache.NCache.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases related with checking managing values in cache per namespace
 *
 * @author Michal Wronski
 * @date 24.10.14.
 */
public class NCacheTest {

    private NCache cache;

    @Before
    public void setUp() {
        cache = nCache(new InMemoryCache("InMemoryTestCache"));
    }

    @Test
    public void shouldPutValueToNamespace() {
        //given namespaces
        Cache ns1 = cache.namespace("namespace1");
        Cache ns2 = cache.namespace("namespace2");
        //and sample value
        String testKey = "key-123";
        String testValue = "value-123";
        //when putting value into chosen namespace
        ns1.put(testKey, testValue);
        //then value is present in single namespace
        assertThat((String) ns1.get(testKey), is(testValue));
        assertThat(ns2.get(testKey), is(nullValue()));
        //and value cannot be found also in generic cache
        assertThat(cache.get(testKey), is(nullValue()));
    }

    @Test
    public void shouldSeparateCacheRegions() {
        //given namespaces
        Cache ns1 = cache.namespace("namespace1");
        Cache ns2 = cache.namespace("namespace2");
        //when adding values to chosen regions of cache
        ns1.put("ns1-key1", "ns1-value1");
        ns2.put("ns2-key1", "ns2-value1");
        cache.put("key1", "value1");
        //then values are visible only in proper regions
        assertThat((String) ns1.get("ns1-key1"), is("ns1-value1"));
        assertThat(ns1.get("ns2-key1"), is(nullValue()));
        assertThat(ns1.get("key1"), is(nullValue()));

        assertThat((String) ns2.get("ns2-key1"), is("ns2-value1"));
        assertThat(ns2.get("ns1-key1"), is(nullValue()));
        assertThat(ns2.get("key1"), is(nullValue()));

        assertThat((String) cache.get("key1"), is("value1"));
        assertThat(cache.get("ns1-key1"), is(nullValue()));
        assertThat(cache.get("ns1-key1"), is(nullValue()));
    }

    @Test
    public void shouldEvictKeyFromNamespace() {
        //given namespaces
        Cache ns1 = cache.namespace("namespace1");
        Cache ns2 = cache.namespace("namespace2");
        //and sample values of namespaces
        ns1.put("ns1-key1", "ns1-value1");
        ns1.put("ns1-key2", "ns1-value2");
        ns2.put("ns2-key1", "ns2-value1");
        cache.put("key1", "value1");
        //when removing key from chosen namespace
        ns1.evict("ns1-key1");
        //then key is evicted
        assertThat(ns1.get("ns1-key1"), is(nullValue()));
        assertThat((String) ns1.get("ns1-key2"), is("ns1-value2"));
        //and other regions of cache remained untouched
        assertThat((String) ns2.get("ns2-key1"), is("ns2-value1"));
        assertThat((String) cache.get("key1"), is("value1"));
    }

    @Test
    public void shouldClearNamespace() {
        //given namespaces
        Cache ns1 = cache.namespace("namespace1");
        Cache ns2 = cache.namespace("namespace2");
        //and sample values of namespaces
        ns1.put("ns1-key1", "ns1-value1");
        ns1.put("ns1-key2", "ns1-value2");
        ns2.put("ns2-key1", "ns2-value1");
        cache.put("key1", "value1");
        //when namespace is cleared
        ns1.clear();
        //then all keys from chosen namespace are removed
        assertThat(ns1.get("ns1-key1"), is(nullValue()));
        assertThat(ns1.get("ns1-key2"), is(nullValue()));
        //and other regions of cache remained untouched
        assertThat((String) ns2.get("ns2-key1"), is("ns2-value1"));
        assertThat((String) cache.get("key1"), is("value1"));
    }

}
