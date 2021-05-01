package com.web.restservice;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import org.springframework.http.converter.HttpMessageNotReadableException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice{

	@ExceptionHandler(ResponseStatusException.class)
	@ResponseBody
	public ResponseEntity<ExceptionResponse> handleResposeStatusException(ResponseStatusException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getReason(),
        request.getDescription(false),ex.getStatus().getReasonPhrase());
		return 	new ResponseEntity<ExceptionResponse>(exceptionResponse, ex.getStatus());
	}


	@ExceptionHandler(NumberFormatException.class)
	@ResponseBody
	public ResponseEntity<ExceptionResponse> handleNumberFormatException(NumberFormatException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false),HttpStatus.BAD_REQUEST.getReasonPhrase());
		return  new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidFormatException.class)
	@ResponseBody
	public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(InvalidFormatException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage().substring(0, ex.getMessage().indexOf(':')),
        request.getDescription(false),HttpStatus.BAD_REQUEST.getReasonPhrase());
		return  new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}