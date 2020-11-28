package com.windmillsmartsolutions.service;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

/**
 * @author pramodgavali
 * Class to access the localized message with default English locale.
 * The message needs to be defined in src/resources/message.properties
 * 
 */
@Component
public class Messages {

    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
    }

    /**
     * Returns the message by given code 
     * @param code
     * @return
     */
    public String get(String code) {
        return accessor.getMessage(code);
    }
    /**
     * Returns the formated message by given code and values 
     * Example - Question {0} is already defined 
     * @param code - message code
     * @param args - values to  substitute
     * @return
     */
    public String get(String code, Object args[]) {
        return accessor.getMessage(code, args);
    }

}