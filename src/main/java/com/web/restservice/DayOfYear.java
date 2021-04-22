package com.web.restservice;


public class DayOfYear {

	private final int year;
	private final int day;

	public DayOfYear(int year, int day) {
		this.year = year;
		this.day = day;
	}

	public int getYear() {
		return year;
	}

	public int getDay() {
		return day;
	}
}
