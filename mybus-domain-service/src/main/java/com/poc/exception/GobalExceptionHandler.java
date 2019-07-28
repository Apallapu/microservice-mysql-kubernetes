package com.poc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GobalExceptionHandler {
	@ExceptionHandler(TicketIdNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorResponse handle404Error(TicketIdNotFoundException e) {
		return new ErrorResponse(e.getErrorCode(), e.getErrorMessage());

	}

	
	@ExceptionHandler(TicketUpdateException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorResponse errorForUpdate(TicketUpdateException e) {
		return new ErrorResponse(e.getErrorCode(), e.getErrorMessage());

	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorResponse handle500Error() {
		return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Unable to process the request please try after some time");

	}
}