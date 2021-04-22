package com.web.restservice;

import java.util.List;

public class CalendarsPostResponse {
    private List<Calendar> calendars;
    private String popularDay;
    private int inputCount;
    private int nonValidCount;
  
    public CalendarsPostResponse(List<Calendar> calendars, String popularDay, int inputCount, int nonValidCount) {
        this.calendars = calendars;
        this.popularDay = popularDay;
        this.inputCount = inputCount;
        this.nonValidCount = nonValidCount;
    }
  
    public List<Calendar> getCalendars() {
        return calendars;
    }

    public String getPopularDay() {
        return popularDay;
    }

    public int getInputCount() {
        return inputCount;
    }

    public int getNonValidCount() {
        return nonValidCount;
    }
}
  