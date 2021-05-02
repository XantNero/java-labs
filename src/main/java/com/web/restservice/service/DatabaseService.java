package com.web.restservice;

import java.util.concurrent.Semaphore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.server.ResponseStatusException;

@Service
class DatabaseService{
    Semaphore semaphore = new Semaphore(1);
    @Autowired
	private CalendarRepository calendarRepository;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public Calendar getCalendar(int year, int day) throws ResponseStatusException {
        CalendarEntity calendarEntity; 
        try {
            semaphore.acquire();
            calendarEntity = calendarRepository.findOneByYearAndDay(year, day);
           
        } catch (InterruptedException e) {
			logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } finally {
            semaphore.release();
        }
        if (calendarEntity == null) {
            String errorMessage = "Trying get non existing calendar from database";
            logger.error(errorMessage);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
        }

        logger.info("Value restored from database: year " + String.valueOf(year)
                    + " day " + String.valueOf(day));
        return new Calendar(year, day, calendarEntity.getDayOfWeek());
    }

    public boolean isStored(int year, int day) {
        CalendarEntity calendarEntity;
        try {
            semaphore.acquire();
            calendarEntity = calendarRepository.findOneByYearAndDay(year, day);
        } catch (InterruptedException e) {
			logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } finally {
            semaphore.release();
        }

        return calendarEntity == null ? false : true;
    }

    public void insertCalendar(Calendar calendar) {
        try {
            semaphore.acquire();
            calendarRepository.save(new CalendarEntity(calendar));
            logger.info("Value inserted to database: year " + String.valueOf(calendar.getYear())
                        + " day " + String.valueOf(calendar.getDay()));
        } catch (InterruptedException e) {
			logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } finally {
            semaphore.release();
        }
    }
}