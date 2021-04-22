package com.web.restservice;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.ArrayList;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ActiveProfiles;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;


@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CalendarServiceTest{

    @Autowired
    CalendarService calendarService;
    @Autowired
    private Cache cache;

    @Test
    public void testClienException() {
        Assert.assertThrows(ResponseStatusException.class, () ->
        { calendarService.getCalendar(-100, 10); });
    }
    @Test
    public void testCalendars() {
        
        Mockito.when(cache.isStored("2020 1")).thenReturn(false);
        Mockito.when(cache.isStored("2009 1")).thenReturn(false);

        List<DayOfYear> inputParameters = new ArrayList<DayOfYear>();
        inputParameters.add(new DayOfYear(-10, 1));
        inputParameters.add(new DayOfYear(2020, 1));
        inputParameters.add(new DayOfYear(2020, 1));
        inputParameters.add(new DayOfYear(2009, 1));
        List<Calendar> calendars = calendarService.getCalendars(inputParameters);
        List<Calendar> waitedResult = new ArrayList<Calendar>();
        waitedResult.add(new Calendar(2020, 1, "Wednesday"));
        waitedResult.add(new Calendar(2020, 1, "Wednesday"));
        waitedResult.add(new Calendar(2009, 1, "Thursday"));
        Assert.assertArrayEquals(calendars.toArray(), waitedResult.toArray());
    }
  
}