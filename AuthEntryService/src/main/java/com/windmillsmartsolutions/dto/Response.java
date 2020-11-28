/**
 * 
 */
package com.windmillsmartsolutions.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author ravjagta
 *
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {

    private Status status;
    private T payload;
    private Object errors;
    private Object metadata;

    public static <T> Response<T> badRequest(String errorMsg) {
    	return getResponseWithError(errorMsg, Status.BAD_REQUEST);
    }

    public static <T> Response<T> ok() {
        Response<T> response = new Response<>();
        response.setStatus(Status.OK);
        return response;
    }

    public static <T> Response<T> unauthorized(String errorMsg) {
    	return getResponseWithError(errorMsg, Status.UNAUTHORIZED);
    }

    public static <T> Response<T> validationException(String errorMsg) {
    	return getResponseWithError(errorMsg, Status.VALIDATION_EXCEPTION);
    }

    public static <T> Response<T> wrongCredentials(String errorMsg) {
    	return getResponseWithError(errorMsg, Status.WRONG_CREDENTIALS);
    }

    public static <T> Response<T> accessDenied(String errorMsg) {
    	return getResponseWithError(errorMsg, Status.ACCESS_DENIED);
    }

    public static <T> Response<T> exception(String errorMsg) {
    	return getResponseWithError(errorMsg, Status.EXCEPTION);
    }

    public static <T> Response<T> notFound(String errorMsg) {
        return getResponseWithError(errorMsg, Status.NOT_FOUND);
    }
	
	public static <T> Response<T> duplicateEntity(String errorMsg) {
		return getResponseWithError(errorMsg, Status.DUPLICATE_ENTITY);
    }
    
    public static <T> Response<T> duplicateCustID(String errorMsg) {
		return getResponseWithError(errorMsg, Status.DUPLICATE_CUSTID);
	}
	
	private static <T> Response<T> getResponseWithError(String errorMsg, Status status) {
		Response<T> response = new Response<>();
        ResponseError error = new ResponseError()
                .setDetails(errorMsg);            
        response.setErrors(error);
        response.setStatus(status);
        return response;
	}
	
    public enum Status {
        OK, BAD_REQUEST, UNAUTHORIZED, VALIDATION_EXCEPTION, EXCEPTION, WRONG_CREDENTIALS, ACCESS_DENIED, NOT_FOUND, DUPLICATE_ENTITY,DUPLICATE_CUSTID
    }
}