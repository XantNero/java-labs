package com.web.restservice;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice /*extends ResponseEntityExceptionHandler*/{

	// public GlobalExceptionHandlingControllerAdvice() {
    //     super();
    // }

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<ExceptionResponse> badRequest(HttpClientErrorException ex, WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false),HttpStatus.BAD_REQUEST.getReasonPhrase());
		return  new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpServerErrorException.class)
	public ResponseEntity<ExceptionResponse> internalError(HttpServerErrorException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		return  new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<ExceptionResponse> internalError(NumberFormatException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false),HttpStatus.BAD_REQUEST.getReasonPhrase());
		return  new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}