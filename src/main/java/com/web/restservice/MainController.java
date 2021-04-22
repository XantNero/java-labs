package com.web.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.Thread;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.Collectors;

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
	public ResponseEntity<Object> getCounter() {
		return new ResponseEntity<>(counterService.getCounter(), HttpStatus.OK);
	}

	@PostMapping(path = "/calendars", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> postCalendars(@RequestBody List<DayOfYear> days) {
		List<Calendar> calendars = calendarService.getCalendars(days);
		String popularDay = calendars
			.stream()
			.collect(Collectors.groupingBy(calendar -> calendar.getDayOfWeek(), Collectors.counting()))
			.entrySet().stream().max(Map.Entry.comparingByValue())
			.map(entry -> entry.getKey()).orElse("Not defined");
		int inputCount = days.size();
		int nonValidCount = inputCount - calendars.size();
		
		counterService.incrementCounter();
		return new ResponseEntity<>(new CalendarsPostResponse(calendars, popularDay, inputCount, nonValidCount), HttpStatus.OK);
	}		
}