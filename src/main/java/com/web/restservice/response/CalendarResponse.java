package com.web.restservice;


public class CalendarResponse {

	private final int year;
	private final int day;
	private final String dayOfWeek;

    public CalendarResponse() {
        this(1970, 1, "Thursday");
	}

	public CalendarResponse(int year, int day, String dayOfWeek) {
		this.year = year;
		this.day = day;
		this.dayOfWeek = dayOfWeek;
	}

    public CalendarResponse(Calendar calendar) {
        this.year = calendar.getYear();
        this.day = calendar.getDay();
        this.dayOfWeek = calendar.getDayOfWeek();
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
}
