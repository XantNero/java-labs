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
public class DatabaseServiceTest{

    @Autowired
    DatabaseService databaseService;
    @Autowired
    private CalendarRepository calendarRepository;

    @Test
    public void testInsertCalendar() {
        
        Calendar calendar = new Calendar(1800, 200);
        CalendarEntity calendarEntity = new CalendarEntity(calendar);
        Mockito.when(calendarRepository.findOneByYearAndDay(1800, 200)).thenReturn(calendarEntity);

        databaseService.insertCalendar(calendar);
        Assert.assertEquals(databaseService.getCalendar(1800, 200), calendar);
    }
    
    @Test
    public void testIsNotStored() {
    
        Mockito.when(calendarRepository.findOneByYearAndDay(1800, 200)).thenReturn(null);

        Assert.assertEquals(databaseService.isStored(1800, 200), false);
    } 
     
    @Test
    public void testIsStored() {
    
        Calendar calendar = new Calendar(1800, 200);
        CalendarEntity calendarEntity = new CalendarEntity(calendar);
        Mockito.when(calendarRepository.findOneByYearAndDay(1800, 200)).thenReturn(calendarEntity);

        databaseService.insertCalendar(calendar);

        Assert.assertEquals(databaseService.isStored(1800, 200), true);
    } 

    @Test
    public void testGetCalendarException() {
    
        Mockito.when(calendarRepository.findOneByYearAndDay(1800, 200)).thenReturn(null);

        Assert.assertThrows(ResponseStatusException.class, () ->
        { databaseService.getCalendar(1800, 200); });
    } 
}