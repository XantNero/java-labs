package com.web.restservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

import org.springframework.web.server.ResponseStatusException;

@Service
class CalendarService {

	@Autowired
	private Cache cache;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public Calendar getCalendar(int year, int day)
        	throws ResponseStatusException {
		validateParameters(year, day);
		
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
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                                               errorMessage);
		}
		
		cache.insert(s, calendarObj.getDayOfWeek());

		logger.info("Successfully compute year " + String.valueOf(year) 
                    + " and day " + String.valueOf(day));
		
		return calendarObj;
    }

	public Calendar getCalendar(DayOfYear dayOfYear) {	
		return getCalendar(dayOfYear.getYear(), dayOfYear.getDay());
	}

	public List<Calendar> getCalendars(List<DayOfYear> days) {
		logger.info("Proccessing sequence of parameters");
		return days.stream()
			.parallel()
			.filter(dayOfYear -> {
				return 	dayOfYear.getYear() >= 1520 &&
						dayOfYear.getDay() > 0 &&
						dayOfYear.getDay() <= 365 &&
						(dayOfYear.getDay() != 366 || Calendar.isLeapYear(dayOfYear.getYear()));
			})
			.parallel()	
			.map(dayOfYear -> getCalendar(dayOfYear))
			.collect(Collectors.toCollection(ArrayList::new));
	}

	private boolean validateParameters(int year, int day) throws ResponseStatusException {
		if (year < 1520) {
			String errorMessage = "Invalid year " + String.valueOf(year);
			logger.error(errorMessage);
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST,errorMessage);
		}
			
		if (day <= 0 || day > 366 ||
			 	(day == 366 && !Calendar.isLeapYear(year))) {
			String errorMessage = "Invalid day " + String.valueOf(day);
			logger.error(errorMessage);
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, errorMessage);
		}
		return true;
	}
}