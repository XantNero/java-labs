package com.web.restservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;


@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad request")
	@ExceptionHandler(HttpClientErrorException.class)
	public void badRequest(HttpClientErrorException ex) {
		logger.error(ex.getMessage());

	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server error")
	@ExceptionHandler(HttpServerErrorException.class)
	public void internalError(HttpServerErrorException ex) {
		logger.error(ex.getMessage());

	}

}