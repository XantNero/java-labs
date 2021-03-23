package com.web.restservice;


public class Calendar {

	private final int year;
	private final int day;
	private final String dayOfWeek;
	private final String days[] = {
		"Monday",
		"Tuesday",
		"Wednesday",
		"Thursday",
		"Friday",
		"Saturday",
		"Sunday"
	};
	private final int startYear = 1201;
	public Calendar(int year, int day, String dayOfWeek) {
		this.year = year;
		this.day = day;
		this.dayOfWeek = dayOfWeek;
	}
	public Calendar(int year, int day) {
		this.year = year;
		this.day = day;
		int yearDif = year - startYear;
		yearDif += yearDif / 4 - yearDif / 100 + yearDif / 400;
		this.dayOfWeek = days[(yearDif + day - 1) % 7];
	}
	

	public int getYear() {
		return year;
	}

	public int getDay() {
		return day;
	}
	
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	static public boolean isLeapYear(int year) {
		return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
	}
}
