package com.web.restservice;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class Cache {

    private ConcurrentHashMap<String, String> cache;

    public Cache() {
        cache = new ConcurrentHashMap<String, String>();
    }
    public void insert(String key, String value) {
        cache.put(key, value);
    }
    public String get(String key) {
        return (String) cache.get(key);
    }
    boolean isStored(String key) {
        return cache.containsKey(key);
    }
}
