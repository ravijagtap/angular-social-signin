package com.windmillsmartsolutions.exception;

import com.windmillsmartsolutions.service.Messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Exception extends RuntimeException {
	
	@Autowired
	Messages messageService;
	
	public RuntimeException throwException(EntityType entityType, ExceptionType exceptionType, String... args) {
        return new RuntimeException(messageService.get(entityType.toString() + "." + exceptionType.value, args));
    }	
}
