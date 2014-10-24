Namespace cache (NCache)
====

Abstraction layer for providing cache namespaces. <br/>
Namespaces allow creating separate regions in cache (for chosen groups, users etc.) and <br/>
cleansing of only needed part. Each namespace is separate. <br/>
Any cache provider can be wrapped with virtual namespaces (mem-cached, ehcache etc.).  <br/>
Namespace-cache can be used with Spring or as a stand-alone solution.  <br/>

# Build

Project is managed using maven so just hit:

```
    $mvn clean install
```

# Sample usage

```java
import static com.mwronski.ncache.NCache.*;

Cache cache = ...; //provide your cache here
//wrap your provider with cache with name-spacing capability
NCache nCache = nCache(cache);
//create namespace for keeping data of chosen users
int userId = 123;
Cache userNs = nCache.namespace("namespace-per-user", userId);

//do regular operations on your chosen namespace
userNs.put("key1", "value1");
userNs.get("key1");
userNs.evict("key1");
userNs.clear();

```
