package com.web.restservice;

import java.util.HashMap;
import org.springframework.stereotype.Component;

@Component
public class Cache {

    private HashMap<String, String> cache;

    public Cache() {
        cache = new HashMap<String, String>();
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
