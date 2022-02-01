package com.capg.ems.controller;

import java.util.Arrays;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capg.ems.beans.Designation;
import com.capg.ems.beans.Employee;
import com.capg.ems.exception.EmployeeNotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class ExceptionController {
	
	private Logger LOGGER = LoggerFactory.getLogger(Employee.class);

	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<String> exceptionConstraintViolationException(ConstraintViolationException exception) {
		LOGGER.info("Exception Controller initialized for ConstraintViolationException");
		StringBuilder message = new StringBuilder();
		exception.getConstraintViolations().forEach(i -> message.append(i.getConstraintDescriptor().getMessageTemplate()));
		LOGGER.info("Exception Controller executed successfully for ConstraintViolationException with message"+message);
		return new ResponseEntity<>(message.toString(), HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(value = EmployeeNotFoundException.class)
	public ResponseEntity<String> exceptionEmployeeNotFoundException(EmployeeNotFoundException exception){
		LOGGER.info("Exception Controller initialized for EmployeeNotFoundException");
		String message = exception.getMessage();
		LOGGER.info("Exception Controller executed successfully for EmployeeNotFoundException with message"+message);
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = InvalidFormatException.class)
	public ResponseEntity<Object> exceptionInvalidFormatException(InvalidFormatException exception) {
		ResponseEntity<Object> response = null;
		if (exception.getTargetType() == com.capg.ems.beans.Designation.class) {
			response = new ResponseEntity<>("Enter a valid Designation. The valid designations are "+Arrays.asList(Designation.values()), HttpStatus.NOT_ACCEPTABLE);
		}else {
			response = new ResponseEntity<>(exception.getTargetType(), HttpStatus.NOT_ACCEPTABLE);
		}
		return response;
	}
}
