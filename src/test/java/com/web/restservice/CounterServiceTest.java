package com.web.restservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CounterServiceTest {
    @Test
    void testIncrement() {
        CounterService counter = new CounterService();
        counter.incrementCounter();
        assertEquals(counter.getCounter(), 1);
    }
}