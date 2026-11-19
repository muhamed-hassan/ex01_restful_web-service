package app.infrastructure.configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import app.persistence.entities.ValidationRule;
import app.persistence.entities.Validator;
import app.persistence.repositories.ValidationRuleRepository;
import app.persistence.repositories.ValidatorRepository;

@Configuration
public class WebValidatorsSettings {
	
	@Autowired
	private ValidatorRepository validatorRepository;
	
	@Autowired
	private ValidationRuleRepository validationRuleRepository;
	
	@Bean
	public WebValidatorsLoader webValidatorsLoader() {
		
		ArrayList<Validator> loadedValidators = validatorRepository.findAll();		
		ArrayList<ValidationRule> loadedValidationRules = validationRuleRepository.findAll();
		
		LinkedHashMap<String, LinkedHashMap<String, ValidationRule>> transformedValidators = 
				new LinkedHashMap<String, LinkedHashMap<String, ValidationRule>>();
		
		for (int cursorOverValidators = 0; cursorOverValidators < loadedValidators.size(); cursorOverValidators++) {
			
			String validatorName = loadedValidators.get(cursorOverValidators).getName();
			LinkedHashMap<String, ValidationRule> validationRules = new LinkedHashMap<String, ValidationRule>();
			
			for (int cursorOverValidationRules = 0; cursorOverValidationRules < loadedValidationRules.size(); cursorOverValidationRules++) {
				
				String fieldName = loadedValidationRules.get(cursorOverValidationRules).getFieldName();
				ValidationRule validationRule = loadedValidationRules.get(cursorOverValidationRules);
				validationRules.put(fieldName, validationRule);
			}
			
			transformedValidators.put(validatorName, validationRules);
		}
		
		WebValidatorsLoader webValidatorsLoader = new WebValidatorsLoader();
		webValidatorsLoader.setValidationRulesOfValidators(transformedValidators);
		
		return webValidatorsLoader;
	}

}
