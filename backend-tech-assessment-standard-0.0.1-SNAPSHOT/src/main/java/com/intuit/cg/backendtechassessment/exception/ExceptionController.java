package com.intuit.cg.backendtechassessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(SellerNotFoundException.class)
	public ResponseEntity<ExceptionResponse> resourceNotFound(SellerNotFoundException ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("Not Found");
		response.setErrorMessage(ex.getMessage());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BuyerNotFoundException.class)
	public ResponseEntity<ExceptionResponse> resourceNotFound(BuyerNotFoundException ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("Not Found");
		response.setErrorMessage(ex.getMessage());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProjectNotFoundException.class)
	public ResponseEntity<ExceptionResponse> resourceNotFound(ProjectNotFoundException ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("Not Found");
		response.setErrorMessage(ex.getMessage());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BidNotFoundException.class)
	public ResponseEntity<ExceptionResponse> resourceNotFound(BidNotFoundException ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("Not Found");
		response.setErrorMessage(ex.getMessage());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}

}
