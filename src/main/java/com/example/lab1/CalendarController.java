package com.example.lab1;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarController {

	@GetMapping("/calendar")
	public Calendar calendar(@RequestParam(value = "year", defaultValue = "1970") int year, 
	@RequestParam(value = "day", defaultValue = "1") int day) {
		return new Calendar(year, day);
	}
}