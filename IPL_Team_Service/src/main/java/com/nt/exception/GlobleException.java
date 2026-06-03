package com.nt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nt.utility.ResponseMessage;

@RestControllerAdvice
public class GlobleException {

	@ExceptionHandler(TeamNotFoundException.class)
	public ResponseEntity<ResponseMessage> TeamNotFoundExcpetion(TeamNotFoundException ex) {

		ResponseMessage rm = ResponseMessage.builder().message(ex.getMessage()).statusCode(HttpStatus.BAD_REQUEST).build();

		return new ResponseEntity<ResponseMessage>(rm, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> globleException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
