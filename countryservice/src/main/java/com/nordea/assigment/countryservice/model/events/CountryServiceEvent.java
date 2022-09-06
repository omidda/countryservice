package com.nordea.assigment.countryservice.model.events;

import org.springframework.context.ApplicationEvent;

public class CountryServiceEvent extends ApplicationEvent {
    private String message;

    public CountryServiceEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}