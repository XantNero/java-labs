package com.web.restservice;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class CalendarServiceTestConfigruation {

    @Bean
    @Primary
    public Cache Cache() {
        return Mockito.mock(Cache.class);
    }
}