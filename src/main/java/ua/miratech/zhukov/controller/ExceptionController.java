package ua.miratech.zhukov.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

	private static final Logger logger = Logger.getLogger(ExceptionController.class);

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal server error")
	public void exception(Exception e) {
//		logger.debug(e);
	}

	@ExceptionHandler(SecurityException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Not authorized to access this resource")
	public void securityExceptionHandler(SecurityException e) {
//		logger.debug(e);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad request")
	public void illegalArgumentExceptionHandler(IllegalArgumentException e) {
//		logger.debug(e);
	}

}
