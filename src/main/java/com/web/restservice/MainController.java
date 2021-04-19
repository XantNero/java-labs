package com.web.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.Thread;

import org.springframework.http.ResponseEntity;


@RestController
public class MainController {

	@Autowired
	private CounterService counterService;
	@Autowired
	private CalendarService calendarService;

	@GetMapping("/calendar")
	public ResponseEntity<Object> getCalendar(@RequestParam(value = "year", defaultValue = "1970") int year, 
	@RequestParam(value = "day", defaultValue = "1") int day) {
		counterService.incrementCounter();
		return new ResponseEntity<>(calendarService.getCalendar(year, day), HttpStatus.OK);
	}

	@GetMapping("/counter")
	public ResponseEntity<Object>  counter() {

		return new ResponseEntity<>(counterService.getCounter(), HttpStatus.OK);
	}
}
