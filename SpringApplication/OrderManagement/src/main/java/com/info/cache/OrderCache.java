package com.info.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class OrderCache {
	
	private final Map<String, Boolean> cache = new ConcurrentHashMap<>();

    public boolean exists(String key) {
        return cache.containsKey(key);
    }

    public void put(String key) {
        cache.put(key, true);
    }

}
