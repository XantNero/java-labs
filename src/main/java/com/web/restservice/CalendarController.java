package com.web.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.http.HttpStatus;
import java.util.logging.Logger;


@RestController
public class CalendarController {

	protected Logger logger = Logger.getLogger(CalendarController.class.getName());

	@GetMapping("/calendar")
	public Calendar calendar(@RequestParam(value = "year", defaultValue = "1970") int year, 
	@RequestParam(value = "day", defaultValue = "1") int day) {

		if (year < 1520)	
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid year " + String.valueOf(year));
		if (day <= 0 || day > 366 || (day == 366 && !Calendar.isLeapYear(year)))
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid day" + String.valueOf(day));

		Calendar calendarObj;
		try {
			calendarObj = new Calendar(year, day);
		} catch(RuntimeException ex) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
			 	"Computation error: year=" + String.valueOf(year) + " day=" + String.valueOf(day));
		}
		
		return calendarObj;
	}
}

