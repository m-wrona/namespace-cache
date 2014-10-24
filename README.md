Namespace cache (NCache)
====

Abstraction layer for providing cache namespaces.
Namespaces allow cleansing of chosen region of a cache.
Any cache provider can be wrapped with virtual namespaces (mem-cached, ehcache etc.).
Namespace-cache can be used with Spring or as a standalone solution.

# Build

Project is managed using maven so just hit:

```
    mvn clean install
```

# Sample usage

```java
import static com.mwronski.ncache.NCache.*;

Cache cache = ...; //provide your cache here
//wrap your cache with cache with name-spacing capability
NCache nCache = nCache(cache);
//create namespace
Cache ns1 = cache.namespace("namespace1");

//do regular operations on your chosen namespace
ns1.put("key1", "value1");
ns1.get("key1");
ns1.evict("key1");
ns1.clear();

```