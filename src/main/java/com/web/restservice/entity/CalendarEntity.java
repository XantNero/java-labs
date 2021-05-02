package com.web.restservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "calendar")
public class CalendarEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	private Integer year;
	private Integer day;
	private String dayOfWeek;

    CalendarEntity(int year, int day, String dayOfWeek) {
        this.year = year;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
    }

    CalendarEntity(Calendar calendar) {
        this.year = calendar.getYear();
        this.day = calendar.getDay();
        this.dayOfWeek = calendar.getDayOfWeek();
    }

    CalendarEntity() {
        this(0, 0, "undifined");
    }
	
    public Integer getId() {
        return id;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getDay() {
        return day;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }
}
