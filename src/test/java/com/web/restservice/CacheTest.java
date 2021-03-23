package com.web.restservice;

import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CacheTest {

    @Autowired
	protected Cache cache;
    
    @Test
    void CacheTestIsStoredTrue() {
        cache.insert("2021 365", "Sunday");
        assertTrue(cache.isStored("2021 365"));
    }
    @Test
    void CacheTestIsStoredFalse() {
        assertFalse(cache.isStored("2020 365"));
    }
    @Test
    void CacheTestGet() {
        cache.insert("2020 1", "Monday");
        assertEquals(cache.get("2020 1"), "Monday");
    }
}