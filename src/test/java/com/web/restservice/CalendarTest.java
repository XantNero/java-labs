package com.web.restservice;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

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
    @Test
    void CalendarTestDaysNames() {
        int day = 274;
        Calendar obj = new Calendar(2020, day++);
        Assert.assertEquals("Wednesday", obj.getDayOfWeek());
        obj = new Calendar(2020, day++);
        Assert.assertEquals("Thursday", obj.getDayOfWeek());
        obj = new Calendar(2020, day++);
        Assert.assertEquals("Friday", obj.getDayOfWeek());
        obj = new Calendar(2020, day++);
        Assert.assertEquals("Saturday", obj.getDayOfWeek());
        obj = new Calendar(2020, day++);
        Assert.assertEquals("Sunday", obj.getDayOfWeek());
        obj = new Calendar(2020, day++);
        Assert.assertEquals("Monday", obj.getDayOfWeek());
        obj = new Calendar(2020, day++);
        Assert.assertEquals("Tuesday", obj.getDayOfWeek());   
    }

}
