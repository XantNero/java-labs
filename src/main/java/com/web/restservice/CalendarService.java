package com.web.restservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
class CalendarService {

	@Autowired
	private Cache cache;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public Calendar getCalendar(int year, int day)
        throws HttpClientErrorException, HttpServerErrorException
    {
		if (year < 1520) {
			String errorMessage = "Invalid year " + String.valueOf(year);
			logger.error(errorMessage);
			throw new HttpClientErrorException(
				HttpStatus.BAD_REQUEST,errorMessage);
		}
			
		if (day <= 0 || day > 366 ||
			 	(day == 366 && !Calendar.isLeapYear(year))) {
			String errorMessage = "Invalid day " + String.valueOf(day);
			logger.error(errorMessage);
			throw new HttpClientErrorException(
				HttpStatus.BAD_REQUEST, errorMessage);
		}
		
		String s = String.valueOf(year) + " " + String.valueOf(day);
		if (cache.isStored(s)) {
			logger.info("Value restored from cache: year "
                        + String.valueOf(year) + " day "
                        + String.valueOf(day));
			return new Calendar(year, day, cache.get(s));
		}

		Calendar calendarObj;
		try {
			calendarObj = new Calendar(year, day);
		} catch(RuntimeException ex) {
			String errorMessage =
				 "Computation error: year=" + String.valueOf(year) +
                                    " day=" + String.valueOf(day);
			logger.error(errorMessage);
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                                               errorMessage);
		}
		
		cache.insert(s, calendarObj.getDayOfWeek());

		logger.info("Successfully compute year "
                    + String.valueOf(year) 
                    + " and day "
                    + String.valueOf(day));
		
		return calendarObj;
    }
}