package SystemDesign.Creational.Singleton;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

enum CacheManager{
    INSTANCE;

    private record cacheEntry(String value, Instant expiry){
        boolean isExpiry(){
            return expiry != null && Instant.now().isAfter(expiry);
        }
    }

    ConcurrentHashMap<String, cacheEntry> cache = new ConcurrentHashMap<>();

    public void put(String key, String value, long ttlExpiry){
        Instant newExpiry = ttlExpiry > 0 ? Instant.now().plusSeconds(ttlExpiry) : null;
        cache.put(key, new cacheEntry(value, newExpiry));
    }

    public void put(String key, String value){
        put(key, value, 0 );
    }

    public String get(String key){
        cacheEntry ce = cache.get(key);
        if(ce == null) return null;
        if(ce.isExpiry()) {
            cache.remove(key);
            return null;
        }
        return ce.value;
    }

    public void remove(String key){
        cache.remove(key);
    }

    public int size(){
        cache.entrySet().removeIf(e -> e.getValue().isExpiry());
        return cache.size();
    }
}
