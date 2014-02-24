package ua.miratech.zhukov.service.implementation;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ua.miratech.zhukov.service.ErrorService;

@Service
public class ErrorServiceImpl implements ErrorService {

	@Override
	public void runtimeException(Logger logger, String message) {
		IllegalArgumentException exception = new IllegalArgumentException(message);
		logger.error(exception);
		throw exception;
	}

	@Override
	public void illegalArgumentException(Logger logger, String message) {
		IllegalArgumentException exception = new IllegalArgumentException(message);
		logger.error(exception);
		throw exception;
	}

	@Override
	public void securityException(Logger logger, String message) {
		IllegalArgumentException exception = new IllegalArgumentException(message);
		logger.error(exception);
		throw exception;
	}

}
