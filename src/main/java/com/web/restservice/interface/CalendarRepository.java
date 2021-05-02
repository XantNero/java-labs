package com.web.restservice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CalendarRepository extends CrudRepository<CalendarEntity, Integer> {

    @Query(value = "SELECT * FROM calendar WHERE year=?1 and day=?2 limit 1", nativeQuery = true)
    CalendarEntity findOneByYearAndDay(Integer year, Integer day);
}