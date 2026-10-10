package app.web.error_handler;

import java.util.LinkedHashMap;

public class ErrorResponseWithForm {
	
	private LinkedHashMap<String, String> errorInformation;

	public LinkedHashMap<String, String> getErrorInformation() {
		return errorInformation;
	}

	public void setErrorInformation(LinkedHashMap<String, String> errorInformation) {
		this.errorInformation = errorInformation;
	}
	
}
