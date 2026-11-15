package app.web.controllers.validators;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Component;

import app.web.controllers.error_handler.WebValidationException;
import app.web.controllers.models.CustomerCreateModel;
import app.web.controllers.models.CustomerUpdateModel;
import app.web.controllers.models.NewBankCardCreateModel;
import app.web.controllers.models.NewLoanCreateModel;
import app.web.controllers.models.NewTransactionCreateModel;

// TODO: as a future enhancement, you can store the validation information in DB and load them during APP startup
@Component
public class CustomerValidator {
	
	private String nameField = "name";
	private String nameFieldIsRequiredMsg = "Name is required.";
	private String nameFieldValidationRegex = "[a-zA-Z\\ ]{5,250}";
	private String nameFieldIsInvalidMsg = "Name contains invalid characters which shall contain letters with minimum of 5 characters and maximum of 250 characters.";	
	
	private String nationalIdField = "nationalId";
	private String nationalIdFieldIsRequiredMsg = "National ID is required.";
	private String nationalIdFieldValidationRegex = "[0-9]{14}";
	private String nationalIdFieldIsInvalidMsg = "National ID contains invalid characters which shall contain 14 digits only.";
	
	private String dobField = "dob";
	private String dobFieldIsRequiredMsg = "Date of birth is required.";
	private String dobFieldValidationRegex = "((19[7-9][0-9])|(2[0-9][0-9][0-9]))-(0[1-9]|1[0-2])-(0[1-9]|([1-2][0-9])|3[0-1])";
	private String dobFieldIsInvalidMsg = "Date of birth contains invalid characters which shall be in the form of yyyy-MM-dd.";
	
	private String mobileField = "mobile";
	private String mobileFieldIsRequiredMsg = "Mobile is required.";
	private String mobileFieldValidationRegex = "(07)[0-9]{9}";
	private String mobileFieldIsInvalidMsg = "Mobile contains invalid characters which shall be in the form of 07123456789.";
	
	private String emailField = "email";
	private String emailFieldIsRequiredMsg = "Email is required.";
	private String emailFieldValidationRegex = "[a-zA-Z0-9_\\.]+(\\@)[a-zA-Z0-9]+(\\.)[a-zA-Z]+";
	private String emailFieldIsInvalidMsg = "Email format is invalid.";
	
	private String mailingAddressField = "mailingAddress";
	private String mailingAddressFieldIsRequiredMsg = "Mailing address is required.";
	private String mailingAddressFieldValidationRegex = "[a-zA-Z0-9-,\\ ]{15,500}";
	private String mailingAddressFieldIsInvalidMsg = "Mailing address contains invalid characters which shall contain alphanumeric characters with minimum of 15 characters and maximum of 500 characters.";
	
	private String beneficiaryAccountNumberField = "beneficiaryAccountNumber";
	private String beneficiaryAccountNumberFieldIsRequiredMsg = "Beneficiary Account number is required.";
	private String beneficiaryAccountNumberFieldValidationRegex = "[1-9]{1}[0-9]{7}";
	private String beneficiaryAccountNumberFieldIsInvalidMsg = "Beneficiary account number contains invalid characters which shall consist of 8 digits.";
	
	private String notesField = "notes";
	private String notesFieldIsRequiredMsg = "Notes is required.";
	private String notesFieldValidationRegex = "[a-zA-Z0-9-,\\ ]{50,1000}";
	private String notesFieldIsInvalidMsg = "Notes contains invalid characters which shall contain alphanumeric characters with minimum of 50 characters and maximum of 1000 characters.";
	
	private String balanceField = "balance";
	private String balanceFieldIsInvalidMsg = "Initial balance should be 1,000.0 £";
	
	private String dateOfIssuanceField = "dateOfIssuance"; 
	private String dateOfIssuanceFieldIsRequiredMsg = "Date of issuance is required.";
	private String dateOfIssuanceFieldValidationRegex = "(2[0-9][0-9][0-9])-(0[1-9]|1[0-2])-(0[1-9]|([1-2][0-9])|3[0-1])";
	private String dateOfIssuanceFieldIsInvalidMsg = "Date of issuance contains invalid characters which shall be in the form of yyyy-MM-dd.";
	
	private String amountField = "amount";
	private String amountFieldIsInvalidMsg = "Amount should be a positive value.";
	
	private String borrowingDateField = "borrowingDate"; 
	private String borrowingDateFieldIsRequiredMsg = "Borrowing date is required.";
	private String borrowingDateFieldValidationRegex = "(2[0-9][0-9][0-9])-(0[1-9]|1[0-2])-(0[1-9]|([1-2][0-9])|3[0-1])";
	private String borrowingDateFieldIsInvalidMsg = "Borrowing date contains invalid characters which shall be in the form of yyyy-MM-dd.";
	
	private String occurredOnField = "occurredOn"; 
	private String occurredOnFieldIsRequiredMsg = "Date of occurred-on is required.";
	private String occurredOnFieldValidationRegex = "(2[0-9][0-9][0-9])-(0[1-9]|1[0-2])-(0[1-9]|([1-2][0-9])|3[0-1])";
	private String occurredOnFieldIsInvalidMsg = "Date of occurred-on contains invalid characters which shall be in the form of yyyy-MM-dd.";
	
	public void validateNewBankCardCreateModel(NewBankCardCreateModel newBankCardCreateModel) {
		
		LinkedHashMap<String, String> errors = new LinkedHashMap<String, String>(0);
		
		String errMsgOfDateOfIssuanceField = getErrMsgIfAny(newBankCardCreateModel.getDateOfIssuance(), dateOfIssuanceFieldIsRequiredMsg, 
				dateOfIssuanceFieldValidationRegex, dateOfIssuanceFieldIsInvalidMsg);
		if (errMsgOfDateOfIssuanceField != null) {
			errors.put(dateOfIssuanceField, errMsgOfDateOfIssuanceField);
		}
		
		if (!errors.isEmpty()) {			
			WebValidationException webValidationException = new WebValidationException();
			webValidationException.setErrors(errors);
			throw webValidationException;
		}		
	}
	
	public void validateCustomerCreateModel(CustomerCreateModel customerCreateModel) {
		
		LinkedHashMap<String, String> errors = new LinkedHashMap<String, String>(0);
		
		String errMsgOfNameField = getErrMsgIfAny(customerCreateModel.getName(), nameFieldIsRequiredMsg, nameFieldValidationRegex, nameFieldIsInvalidMsg);
		if (errMsgOfNameField != null) {
			errors.put(nameField, errMsgOfNameField);
		}
		
		String errMsgOfNationalIdField = getErrMsgIfAny(customerCreateModel.getNationalId(), nationalIdFieldIsRequiredMsg, nationalIdFieldValidationRegex, 
				nationalIdFieldIsInvalidMsg);
		if (errMsgOfNationalIdField != null) {
			errors.put(nationalIdField, errMsgOfNationalIdField);
		}
		
		String errMsgOfDobField = getErrMsgIfAny(customerCreateModel.getDateOfBirth(), dobFieldIsRequiredMsg, dobFieldValidationRegex, dobFieldIsInvalidMsg);
		if (errMsgOfDobField != null) {
			errors.put(dobField, errMsgOfDobField);
		}
		
		String errMsgOfMobileField = getErrMsgIfAny(customerCreateModel.getMobile(), mobileFieldIsRequiredMsg, mobileFieldValidationRegex, mobileFieldIsInvalidMsg);
		if (errMsgOfMobileField != null) {
			errors.put(mobileField, errMsgOfMobileField);
		}
		
		String errMsgOfEmailField = getErrMsgIfAny(customerCreateModel.getEmail(), emailFieldIsRequiredMsg, emailFieldValidationRegex, emailFieldIsInvalidMsg);
		if (errMsgOfEmailField != null) {
			errors.put(emailField, errMsgOfEmailField);
		}
		
		String errMsgOfMailingAddressField = getErrMsgIfAny(customerCreateModel.getMailingAddress(), mailingAddressFieldIsRequiredMsg, mailingAddressFieldValidationRegex, 
				mailingAddressFieldIsInvalidMsg);
		if (errMsgOfMailingAddressField != null) {
			errors.put(mailingAddressField, errMsgOfMailingAddressField);
		}
		
		float balance = customerCreateModel.getBalance();
		if (balance != 1000) {
			errors.put(balanceField, balanceFieldIsInvalidMsg);
		}
		
		if (!errors.isEmpty()) {			
			WebValidationException webValidationException = new WebValidationException();
			webValidationException.setErrors(errors);
			throw webValidationException;
		}
	}
	
	public void validateCustomerUpdateModel(CustomerUpdateModel customerUpdateModel) {
		
		LinkedHashMap<String, String> errors = new LinkedHashMap<String, String>(0);
		
		String errMsgOfMobileField = getErrMsgIfAny(customerUpdateModel.getMobile(), mobileFieldIsRequiredMsg, mobileFieldValidationRegex, mobileFieldIsInvalidMsg);
		if (errMsgOfMobileField != null) {
			errors.put(mobileField, errMsgOfMobileField);
		}
		
		String errMsgOfEmailField = getErrMsgIfAny(customerUpdateModel.getEmail(), emailFieldIsRequiredMsg, emailFieldValidationRegex, emailFieldIsInvalidMsg);
		if (errMsgOfEmailField != null) {
			errors.put(emailField, errMsgOfEmailField);
		}
		
		String errMsgOfMailingAddressField = getErrMsgIfAny(customerUpdateModel.getMailingAddress(), mailingAddressFieldIsRequiredMsg, mailingAddressFieldValidationRegex, 
				mailingAddressFieldIsInvalidMsg);
		if (errMsgOfMailingAddressField != null) {
			errors.put(mailingAddressField, errMsgOfMailingAddressField);
		}
		
		if (!errors.isEmpty()) {			
			WebValidationException webValidationException = new WebValidationException();
			webValidationException.setErrors(errors);
			throw webValidationException;
		}
	}
	
	public void validateNewLoanCreateModel(NewLoanCreateModel newLoanCreateModel) {
		
		LinkedHashMap<String, String> errors = new LinkedHashMap<String, String>(0);
		
		if (newLoanCreateModel.getAmount() <= 0) {
			errors.put(amountField, amountFieldIsInvalidMsg);
		}
		
		String errMsgOfBorrowingDateField = getErrMsgIfAny(newLoanCreateModel.getBorrowingDate(), borrowingDateFieldIsRequiredMsg, borrowingDateFieldValidationRegex, 
				borrowingDateFieldIsInvalidMsg);
		if (errMsgOfBorrowingDateField != null) {
			errors.put(borrowingDateField, errMsgOfBorrowingDateField);
		}
		
		if (!errors.isEmpty()) {			
			WebValidationException webValidationException = new WebValidationException();
			webValidationException.setErrors(errors);
			throw webValidationException;
		}
	}
	
	public void validateNewTransactionCreateModel(NewTransactionCreateModel newTransactionCreateModel) {
		
		LinkedHashMap<String, String> errors = new LinkedHashMap<String, String>(0);
		
		if (newTransactionCreateModel.getAmount() <= 0) {
			errors.put(amountField, amountFieldIsInvalidMsg);
		}
		
		String errMsgOfOccurredOnField = getErrMsgIfAny(newTransactionCreateModel.getOccurredOn(), occurredOnFieldIsRequiredMsg, occurredOnFieldValidationRegex, 
				occurredOnFieldIsInvalidMsg);
		if (errMsgOfOccurredOnField != null) {
			errors.put(occurredOnField, errMsgOfOccurredOnField);
		}
		
		String errMsgOfBeneficiaryAccountNumberField = getErrMsgIfAny(newTransactionCreateModel.getBeneficiaryAccountNumber(), beneficiaryAccountNumberFieldIsRequiredMsg, 
				beneficiaryAccountNumberFieldValidationRegex, beneficiaryAccountNumberFieldIsInvalidMsg);
		if (errMsgOfBeneficiaryAccountNumberField != null) {
			errors.put(beneficiaryAccountNumberField, errMsgOfBeneficiaryAccountNumberField);
		}
		
		String errMsgOfNotesField = getErrMsgIfAny(newTransactionCreateModel.getNotes(), notesFieldIsRequiredMsg, notesFieldValidationRegex, notesFieldIsInvalidMsg);
		if (errMsgOfNotesField != null) {
			errors.put(notesField, errMsgOfNotesField);
		}
		
		if (!errors.isEmpty()) {			
			WebValidationException webValidationException = new WebValidationException();
			webValidationException.setErrors(errors);
			throw webValidationException;
		}
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
	
}
