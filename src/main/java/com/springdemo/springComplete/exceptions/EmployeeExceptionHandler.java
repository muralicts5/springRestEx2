package com.springdemo.springComplete.exceptions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice 
public class EmployeeExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorStatus> employeeExceptionHandler1
						(HttpServletRequest req, Exception e) 
	{
		System.out.println(e);
		System.out.println("exception arthimetich/or common global handler");
		ErrorStatus error = new ErrorStatus(new Date(), e.getMessage(), req.getRequestURI());
		return new ResponseEntity<ErrorStatus>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorStatus> employeeExceptionHandler
						(HttpServletRequest req, Exception e) 
	{
		System.out.println("exception user not found handler");
		ErrorStatus error = new ErrorStatus(new Date(), e.getMessage(), req.getRequestURI());
		return new ResponseEntity<ErrorStatus>(error, HttpStatus.NOT_FOUND);

	}
	
}