package app.web.controllers.error_handler;

import java.util.LinkedHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lib.persistence.repositories.exceptions.DataNotFoundException;

@ControllerAdvice
public class RestErrorHandler {
		
	@ExceptionHandler
    public ResponseEntity<LinkedHashMap<String, String>> handleWebValidationException(WebValidationException exception) {
				
		return new ResponseEntity<LinkedHashMap<String, String>>(exception.getErrors(), HttpStatus.BAD_REQUEST);		
	}

}
