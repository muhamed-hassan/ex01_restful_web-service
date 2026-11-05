package app.domain.exceptions;

import java.util.LinkedHashMap;

public class DomainValidationException extends RuntimeException {

	private LinkedHashMap<String, String> errors;
	
	public LinkedHashMap<String, String> getErrors() {
		return errors;
	}

	public void setErrors(LinkedHashMap<String, String> errors) {
		this.errors = errors;
	}
	
}
