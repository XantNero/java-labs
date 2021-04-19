package com.web.restservice;

import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

import org.springframework.web.client.HttpClientErrorException;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalendarServiceTest{

    @Test
    public void testClienException(){
        CalendarService calendar = new CalendarService();
        assertThrows(HttpClientErrorException.class, () ->
        { calendar.getCalendar(-100, 10); });
    }
  
}