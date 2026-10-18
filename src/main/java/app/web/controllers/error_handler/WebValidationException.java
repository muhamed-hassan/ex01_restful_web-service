package app.web.controllers.error_handler;

import java.util.LinkedHashMap;

public class WebValidationException extends RuntimeException {
	
	private LinkedHashMap<String, String> errors;
	
	public LinkedHashMap<String, String> getErrors() {
		return errors;
	}

	public void setErrors(LinkedHashMap<String, String> errors) {
		this.errors = errors;
	}
	
}
