package com.web.restservice;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CalendarTest {

	@Test
	void CalendarTest1January2021() {
        Calendar obj = new Calendar(2021, 1);
        Assert.assertEquals("Friday", obj.getDayOfWeek());
	}

    @Test
	void CalendarTest5June1600() {
        Calendar obj = new Calendar(1600, 157);
        Assert.assertEquals("Monday", obj.getDayOfWeek());
	}

}
