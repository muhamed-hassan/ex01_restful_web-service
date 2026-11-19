package app.infrastructure.configuration;

import java.util.LinkedHashMap;

import app.persistence.entities.ValidationRule;

public class WebValidatorsLoader {
	
	private LinkedHashMap<String, LinkedHashMap<String, ValidationRule>> validationRulesOfValidators;

	public LinkedHashMap<String, LinkedHashMap<String, ValidationRule>> getValidationRulesOfValidators() {
		return validationRulesOfValidators;
	}

	public void setValidationRulesOfValidators(
			LinkedHashMap<String, LinkedHashMap<String, ValidationRule>> validationRulesOfValidators) {
		this.validationRulesOfValidators = validationRulesOfValidators;
	}
	
}
