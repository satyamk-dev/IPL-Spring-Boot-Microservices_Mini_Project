package com.nt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nt.utility.Constants;
import com.nt.utility.ResponseMessage;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(PlayerNotFoundException.class)
	public ResponseEntity<ResponseMessage> playerNotFound(PlayerNotFoundException ex) {
		ResponseMessage responseMessage = ResponseMessage.builder().message(ex.getMessage()).status(Constants.FAILED)
				.statusCode(Constants.STATUS_NOT_FOUND).build();
		return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MyException.class)
	public ResponseEntity<ResponseMessage> myExceptionController(MyException ex) {
		ResponseMessage res = ResponseMessage.builder().message(ex.getMessage()).status(Constants.ERROR)
				.statusCode(Constants.STATUS_BAD_REQUEST).build();
		return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseMessage> ExceptionController(Exception e) {
		e.printStackTrace();
		ResponseMessage res = ResponseMessage.builder().message(e.getMessage()).status(Constants.FAILED)
				.statusCode(Constants.STATUS_BAD_REQUEST).build();
		return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
	}

}
