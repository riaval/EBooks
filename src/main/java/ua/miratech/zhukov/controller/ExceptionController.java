package ua.miratech.zhukov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal server error")
	public void exception(Exception e) {
		e.printStackTrace();
	}

	@ExceptionHandler(SecurityException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Not authorized to access this resource")
	public void securityExceptionHandler(SecurityException e) {
		System.out.println(e.getMessage());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad request")
	public void illegalArgumentExceptionHandler(IllegalArgumentException e) {
		System.out.println(e.getMessage());
	}

}
