package app.web.controllers.validators;

import java.util.LinkedHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.infrastructure.configuration.WebValidatorsLoader;
import app.persistence.entities.NumericValidationRule;
import app.persistence.entities.TextualValidationRule;
import app.persistence.entities.ValidationRule;
import app.web.controllers.error_handler.WebValidationException;
import app.web.controllers.models.CustomerCreateModel;
import app.web.controllers.models.CustomerUpdateModel;
import app.web.controllers.models.NewBankCardCreateModel;
import app.web.controllers.models.NewLoanCreateModel;
import app.web.controllers.models.NewTransactionCreateModel;

@Component
public class CustomerValidator {
	
	@Autowired
	private WebValidatorsLoader webValidatorsLoader;
	
	private LinkedHashMap<String, ValidationRule> validationRules;
	
	@PostConstruct
	public void initValidationRules() {
		
		LinkedHashMap<String, LinkedHashMap<String, ValidationRule>> validationRulesOfValidators = 
				webValidatorsLoader.getValidationRulesOfValidators();
		
		validationRules = validationRulesOfValidators.get(this.getClass().getSimpleName());
	}
	
	private String nameField = "name";
	private String nationalIdField = "nationalId";	
	private String mobileField = "mobile";
	private String emailField = "email";
	private String mailingAddressField = "mailingAddress";
	private String dobField = "dateOfBirth";
	private String balanceField = "balance";
	private String joinedOnField = "joinedOn";
	private String beneficiaryAccountNumberField = "beneficiaryAccountNumber";
	private String notesField = "notes";	
	private String dateOfIssuanceField = "dateOfIssuance"; 
	private String amountField = "amount";
	private String borrowingDateField = "borrowingDate"; 
	private String occurredOnField = "occurredOn"; 
	
	public void validateCustomerCreateModel(CustomerCreateModel customerCreateModel) {
		
		LinkedHashMap<String, String> errors = new LinkedHashMap<String, String>(0);
		
		
		TextualValidationRule textualValidationRuleOfNameField = (TextualValidationRule) validationRules.get(nameField);		
		String errMsgOfNameField = getErrMsgIfAny(customerCreateModel.getName(), textualValidationRuleOfNameField.getRequiredMessage(), 
				textualValidationRuleOfNameField.getValidationRegexPattern(), textualValidationRuleOfNameField.getValidationMessage());
		if (errMsgOfNameField != null) {
			errors.put(nameField, errMsgOfNameField);
		}
		
		TextualValidationRule textualValidationRuleOfNationalIdField = (TextualValidationRule) validationRules.get(nationalIdField);
		String errMsgOfNationalIdField = getErrMsgIfAny(customerCreateModel.getNationalId(), textualValidationRuleOfNationalIdField.getRequiredMessage(), 
				textualValidationRuleOfNationalIdField.getValidationRegexPattern(), textualValidationRuleOfNationalIdField.getValidationMessage());
		if (errMsgOfNationalIdField != null) {
			errors.put(nationalIdField, errMsgOfNationalIdField);
		}
		
		TextualValidationRule textualValidationRuleOfMobileField = (TextualValidationRule) validationRules.get(mobileField);
		String errMsgOfMobileField = getErrMsgIfAny(customerCreateModel.getMobile(), textualValidationRuleOfMobileField.getRequiredMessage(), 
				textualValidationRuleOfMobileField.getValidationRegexPattern(), textualValidationRuleOfMobileField.getValidationMessage());
		if (errMsgOfMobileField != null) {
			errors.put(mobileField, errMsgOfMobileField);
		}
		
		TextualValidationRule textualValidationRuleOfEmailField = (TextualValidationRule) validationRules.get(emailField);
		String errMsgOfEmailField = getErrMsgIfAny(customerCreateModel.getEmail(), textualValidationRuleOfEmailField.getRequiredMessage(), 
				textualValidationRuleOfEmailField.getValidationRegexPattern(), textualValidationRuleOfEmailField.getValidationMessage());
		if (errMsgOfEmailField != null) {
			errors.put(emailField, errMsgOfEmailField);
		}
		
		TextualValidationRule textualValidationRuleOfMailingAddressField = (TextualValidationRule) validationRules.get(mailingAddressField);
		String errMsgOfMailingAddressField = getErrMsgIfAny(customerCreateModel.getMailingAddress(), textualValidationRuleOfMailingAddressField.getRequiredMessage(), 
				textualValidationRuleOfMailingAddressField.getValidationRegexPattern(), textualValidationRuleOfMailingAddressField.getValidationMessage());
		if (errMsgOfMailingAddressField != null) {
			errors.put(mailingAddressField, errMsgOfMailingAddressField);
		}
		
		TextualValidationRule textualValidationRuleOfDobField = (TextualValidationRule) validationRules.get(dobField);
		String errMsgOfDobField = getErrMsgIfAny(customerCreateModel.getDateOfBirth(), textualValidationRuleOfDobField.getRequiredMessage(), 
				textualValidationRuleOfDobField.getValidationRegexPattern(), textualValidationRuleOfDobField.getValidationMessage());
		if (errMsgOfDobField != null) {
			errors.put(dobField, errMsgOfDobField);
		}
		
		NumericValidationRule numericValidationRuleOfBalanceField = (NumericValidationRule) validationRules.get(balanceField);		
		if (customerCreateModel.getBalance() != 1000) { // TODO: try to move it to DB in NumericValidationRule
			errors.put(balanceField, numericValidationRuleOfBalanceField.getValidationMessage());
		}
		
		TextualValidationRule textualValidationRuleOfJoinedOnField = (TextualValidationRule) validationRules.get(joinedOnField);
		String errMsgOfJoinedOnField = getErrMsgIfAny(customerCreateModel.getJoinedOn(), textualValidationRuleOfJoinedOnField.getRequiredMessage(), 
				textualValidationRuleOfJoinedOnField.getValidationRegexPattern(), textualValidationRuleOfJoinedOnField.getValidationMessage());
		if (errMsgOfJoinedOnField != null) {
			errors.put(joinedOnField, errMsgOfJoinedOnField);
		}
		
		
		finalizeValidation(errors);
	}
	
	public void validateNewBankCardCreateModel(NewBankCardCreateModel newBankCardCreateModel) {
		
		LinkedHashMap<String, String> errors = new LinkedHashMap<String, String>(0);
		
		
		TextualValidationRule textualValidationRuleOfDateOfIssuanceField = (TextualValidationRule) validationRules.get(dateOfIssuanceField);
		String errMsgOfDateOfIssuanceField = getErrMsgIfAny(newBankCardCreateModel.getDateOfIssuance(), textualValidationRuleOfDateOfIssuanceField.getRequiredMessage(), 
				textualValidationRuleOfDateOfIssuanceField.getValidationRegexPattern(), textualValidationRuleOfDateOfIssuanceField.getValidationMessage());
		if (errMsgOfDateOfIssuanceField != null) {
			errors.put(dateOfIssuanceField, errMsgOfDateOfIssuanceField);
		}
		
		
		finalizeValidation(errors);
	}
	
	public void validateNewLoanCreateModel(NewLoanCreateModel newLoanCreateModel) {
		
		LinkedHashMap<String, String> errors = new LinkedHashMap<String, String>(0);
		
		
		NumericValidationRule numericValidationRuleOfAmountField = (NumericValidationRule) validationRules.get(amountField);
		if (newLoanCreateModel.getAmount() <= 0) { // TODO: try to move it to DB in NumericValidationRule
			errors.put(amountField, numericValidationRuleOfAmountField.getValidationMessage());
		}
		
		TextualValidationRule textualValidationRuleOfBorrowingDateField = (TextualValidationRule) validationRules.get(borrowingDateField);
		String errMsgOfBorrowingDateField = getErrMsgIfAny(newLoanCreateModel.getBorrowingDate(), textualValidationRuleOfBorrowingDateField.getRequiredMessage(), 
				textualValidationRuleOfBorrowingDateField.getValidationRegexPattern(), textualValidationRuleOfBorrowingDateField.getValidationMessage());
		if (errMsgOfBorrowingDateField != null) {
			errors.put(borrowingDateField, errMsgOfBorrowingDateField);
		}
		
		
		finalizeValidation(errors);
	}
	
	public void validateNewTransactionCreateModel(NewTransactionCreateModel newTransactionCreateModel) {
		
		LinkedHashMap<String, String> errors = new LinkedHashMap<String, String>(0);
		
		
		NumericValidationRule numericValidationRuleOfAmountField = (NumericValidationRule) validationRules.get(amountField);
		if (newTransactionCreateModel.getAmount() <= 0) { // TODO: try to move it to DB in NumericValidationRule
			errors.put(amountField, numericValidationRuleOfAmountField.getValidationMessage());
		}
		
		TextualValidationRule textualValidationRuleOfOccurredOnField = (TextualValidationRule) validationRules.get(occurredOnField);
		String errMsgOfOccurredOnField = getErrMsgIfAny(newTransactionCreateModel.getOccurredOn(), textualValidationRuleOfOccurredOnField.getRequiredMessage(), 
				textualValidationRuleOfOccurredOnField.getValidationRegexPattern(), textualValidationRuleOfOccurredOnField.getValidationMessage());
		if (errMsgOfOccurredOnField != null) {
			errors.put(occurredOnField, errMsgOfOccurredOnField);
		}
		
		TextualValidationRule textualValidationRuleOfBeneficiaryAccountNumberField = (TextualValidationRule) validationRules.get(beneficiaryAccountNumberField);
		String errMsgOfBeneficiaryAccountNumberField = getErrMsgIfAny(newTransactionCreateModel.getBeneficiaryAccountNumber(), 
				textualValidationRuleOfBeneficiaryAccountNumberField.getRequiredMessage(), textualValidationRuleOfBeneficiaryAccountNumberField.getValidationRegexPattern(), 
				textualValidationRuleOfBeneficiaryAccountNumberField.getValidationMessage());
		if (errMsgOfBeneficiaryAccountNumberField != null) {
			errors.put(beneficiaryAccountNumberField, errMsgOfBeneficiaryAccountNumberField);
		}
		
		TextualValidationRule textualValidationRuleOfNotesField = (TextualValidationRule) validationRules.get(notesField);
		String errMsgOfNotesField = getErrMsgIfAny(newTransactionCreateModel.getNotes(), textualValidationRuleOfNotesField.getRequiredMessage(), 
				textualValidationRuleOfNotesField.getValidationRegexPattern(), textualValidationRuleOfNotesField.getValidationMessage());
		if (errMsgOfNotesField != null) {
			errors.put(notesField, errMsgOfNotesField);
		}
		
		
		finalizeValidation(errors);
	}
	
	public void validateCustomerUpdateModel(CustomerUpdateModel customerUpdateModel) {
		
		LinkedHashMap<String, String> errors = new LinkedHashMap<String, String>(0);
		
		
		TextualValidationRule textualValidationRuleOfMobileField = (TextualValidationRule) validationRules.get(mobileField);
		String errMsgOfMobileField = getErrMsgIfAny(customerUpdateModel.getMobile(), textualValidationRuleOfMobileField.getRequiredMessage(), 
				textualValidationRuleOfMobileField.getValidationRegexPattern(), textualValidationRuleOfMobileField.getValidationMessage());
		if (errMsgOfMobileField != null) {
			errors.put(mobileField, errMsgOfMobileField);
		}
		
		TextualValidationRule textualValidationRuleOfEmailField = (TextualValidationRule) validationRules.get(emailField);
		String errMsgOfEmailField = getErrMsgIfAny(customerUpdateModel.getEmail(), textualValidationRuleOfEmailField.getRequiredMessage(), 
				textualValidationRuleOfEmailField.getValidationRegexPattern(), textualValidationRuleOfEmailField.getValidationMessage());
		if (errMsgOfEmailField != null) {
			errors.put(emailField, errMsgOfEmailField);
		}
		
		TextualValidationRule textualValidationRuleOfMailingAddressField = (TextualValidationRule) validationRules.get(mailingAddressField);
		String errMsgOfMailingAddressField = getErrMsgIfAny(customerUpdateModel.getMailingAddress(), textualValidationRuleOfMailingAddressField.getRequiredMessage(), 
				textualValidationRuleOfMailingAddressField.getValidationRegexPattern(), textualValidationRuleOfMailingAddressField.getValidationMessage());
		if (errMsgOfMailingAddressField != null) {
			errors.put(mailingAddressField, errMsgOfMailingAddressField);
		}
		

		finalizeValidation(errors);
	}
	
	/* *************************************************************************************************** */
	
	private boolean doesItHasAnEmptyValue(String textualValue) {
		
		return textualValue == null || textualValue.isEmpty();
	}
	
	private String getErrMsgIfAny(String textualValue, String requiredMessage, String validationRegex, String validationMessage) {
		
		String errorMessage = null;
		if (doesItHasAnEmptyValue(textualValue)) {			
			errorMessage = requiredMessage;			
		} else {			
			textualValue = textualValue.trim();
			if (!textualValue.matches(validationRegex)) {
				errorMessage = validationMessage;
			}			
		}
		
		return errorMessage;
	}
	
	private void finalizeValidation(LinkedHashMap<String, String> errors) {
		
		if (!errors.isEmpty()) {			
			WebValidationException webValidationException = new WebValidationException();
			webValidationException.setErrors(errors);
			throw webValidationException;
		}
	}
	
}
