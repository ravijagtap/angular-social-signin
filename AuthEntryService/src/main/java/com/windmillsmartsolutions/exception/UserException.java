package com.windmillsmartsolutions.exception;

import org.springframework.stereotype.Component;

@Component
public class UserException extends Exception {

	/**
	 * Generic method to Throw RuntimeException while servicing User entity
	 * @param entityType
	 * @param exceptionType
	 * @param args
	 * @return RuntimeException
	 */
	public String exception(EntityType entityType, ExceptionType exceptionType, String... args) {
		RuntimeException runtimeException = throwException(entityType, exceptionType, args);
		return runtimeException.getMessage();
    }

}
