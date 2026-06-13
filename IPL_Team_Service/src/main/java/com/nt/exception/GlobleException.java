package com.nt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nt.utility.Constants;
import com.nt.utility.ResponseMessage;

@RestControllerAdvice
public class GlobleException {

	@ExceptionHandler(TeamNotFoundException.class)
	public ResponseEntity<ResponseMessage> TeamNotFoundExcpetion(TeamNotFoundException ex) {
		ex.printStackTrace();
		;
		ResponseMessage rm = ResponseMessage.builder().message(ex.getMessage()).status(Constants.FAILED)
				.statusCode(Constants.STATUS_NOT_FOUND).build();

		return new ResponseEntity<ResponseMessage>(rm, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> globleException(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TeamAlreadyExistsException.class)
	public ResponseEntity<ResponseMessage> globleTeamIsExsitedException(TeamAlreadyExistsException ex) {
		ex.printStackTrace();
		ResponseMessage response = ResponseMessage.builder().message("Team is Exists in DB").status(Constants.FAILED)
				.statusCode(Constants.STATUS_BAD_REQUEST).build();
		return ResponseEntity.ok(response);
	}
}
