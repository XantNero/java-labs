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
	public Calendar() {
		this.year = 1970;
		this.day = 1;
		this.dayOfWeek = "Thursday";
	}
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
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (getClass() != o.getClass())
			return false;
		Calendar calendar = (Calendar)o;
		return year == calendar.year && day == calendar.day && dayOfWeek == calendar.dayOfWeek;	
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
