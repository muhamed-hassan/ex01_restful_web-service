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
	
	private String dateOfBirthField = "dob";
	private String dateOfBirthFieldIsRequiredMsg = "Date of birth is required.";
	private String dateOfBirthFieldValidationRegex = "((19[7-9][0-9])|(2[0-9][0-9][0-9]))-(0[1-9]|1[0-2])-(0[1-9]|([1-2][0-9])|3[0-1])";
	private String dateOfBirthFieldIsInvalidMsg = "Date of birth contains invalid characters which shall be in the form of yyyy-MM-dd.";
	
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
		
		String dateOfIssuance = newBankCardCreateModel.getDateOfIssuance();
		if (dateOfIssuance == null || dateOfIssuance.isEmpty()) {			
			errors.put(dateOfIssuanceField, dateOfIssuanceFieldIsRequiredMsg);			
		} else {			
			dateOfIssuance = dateOfIssuance.trim();
			if (!dateOfIssuance.matches(dateOfIssuanceFieldValidationRegex)) {
				errors.put(dateOfIssuanceField, dateOfIssuanceFieldIsInvalidMsg);
			}			
		}
		
		if (!errors.isEmpty()) {			
			WebValidationException webValidationException = new WebValidationException();
			webValidationException.setErrors(errors);
			throw webValidationException;
		}		
	}
	
	public void validateCustomerCreateModel(CustomerCreateModel customerCreateModel) {
		
		LinkedHashMap<String, String> errors = new LinkedHashMap<String, String>(0);
		
		String name = customerCreateModel.getName();
		if (name == null || name.isEmpty()) {			
			errors.put(nameField, nameFieldIsRequiredMsg);			
		} else {			
			name = name.trim();
			if (!name.matches(nameFieldValidationRegex)) {
				errors.put(nameField, nameFieldIsInvalidMsg);
			}			
		}
		
		String nationalId = customerCreateModel.getNationalId();
		if (nationalId == null || nationalId.isEmpty()) {			
			errors.put(nationalIdField, nationalIdFieldIsRequiredMsg);			
		} else {			
			nationalId = nationalId.trim();
			if (!nationalId.matches(nationalIdFieldValidationRegex)) {
				errors.put(nationalIdField, nationalIdFieldIsInvalidMsg);
			}			
		}
		
		String dateOfBirth = customerCreateModel.getDateOfBirth();
		if (dateOfBirth == null || dateOfBirth.isEmpty()) {			
			errors.put(dateOfBirthField, dateOfBirthFieldIsRequiredMsg);			
		} else {			
			dateOfBirth = dateOfBirth.trim();
			if (!dateOfBirth.matches(dateOfBirthFieldValidationRegex)) {
				errors.put(dateOfBirthField, dateOfBirthFieldIsInvalidMsg);
			}			
		}
		
		String mobile = customerCreateModel.getMobile();
		if (mobile == null || mobile.isEmpty()) {			
			errors.put(mobileField, mobileFieldIsRequiredMsg);			
		} else {			
			mobile = mobile.trim();
			if (!mobile.matches(mobileFieldValidationRegex)) {
				errors.put(mobileField, mobileFieldIsInvalidMsg);
			}			
		}
		
		String email = customerCreateModel.getEmail();
		if (email == null || email.isEmpty()) {			
			errors.put(emailField, emailFieldIsRequiredMsg);			
		} else {			
			email = email.trim();
			if (!email.matches(emailFieldValidationRegex)) {
				errors.put(emailField, emailFieldIsInvalidMsg);
			}			
		}
		
		String mailingAddress = customerCreateModel.getMailingAddress();
		if (mailingAddress == null || mailingAddress.isEmpty()) {			
			errors.put(mailingAddressField, mailingAddressFieldIsRequiredMsg);			
		} else {			
			mailingAddress = mailingAddress.trim();
			if (!mailingAddress.matches(mailingAddressFieldValidationRegex)) {
				errors.put(mailingAddressField, mailingAddressFieldIsInvalidMsg);
			}			
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
		
		String mobile = customerUpdateModel.getMobile();
		if (mobile == null || mobile.isEmpty()) {			
			errors.put(mobileField, mobileFieldIsRequiredMsg);			
		} else {			
			mobile = mobile.trim();
			if (!mobile.matches(mobileFieldValidationRegex)) {
				errors.put(mobileField, mobileFieldIsInvalidMsg);
			}			
		}
		
		String email = customerUpdateModel.getEmail();
		if (email == null || email.isEmpty()) {			
			errors.put(emailField, emailFieldIsRequiredMsg);			
		} else {			
			email = email.trim();
			if (!email.matches(emailFieldValidationRegex)) {
				errors.put(emailField, emailFieldIsInvalidMsg);
			}			
		}
		
		String mailingAddress = customerUpdateModel.getMailingAddress();
		if (mailingAddress == null || mailingAddress.isEmpty()) {			
			errors.put(mailingAddressField, mailingAddressFieldIsRequiredMsg);			
		} else {			
			mailingAddress = mailingAddress.trim();
			if (!mailingAddress.matches(mailingAddressFieldValidationRegex)) {
				errors.put(mailingAddressField, mailingAddressFieldIsInvalidMsg);
			}			
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
			errors.put("amount", "Amount should be a positive value.");
		}
		
		String borrowingDate = newLoanCreateModel.getBorrowingDate();
		if (borrowingDate == null || borrowingDate.isEmpty()) {			
			errors.put(borrowingDateField, borrowingDateFieldIsRequiredMsg);			
		} else {			
			borrowingDate = borrowingDate.trim();
			if (!borrowingDate.matches(borrowingDateFieldValidationRegex)) {
				errors.put(borrowingDateField, borrowingDateFieldIsInvalidMsg);
			}			
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
			errors.put("amount", "Amount should be a positive value.");
		}
		
		String occurredOn = newTransactionCreateModel.getOccurredOn();
		if (occurredOn == null || occurredOn.isEmpty()) {			
			errors.put(occurredOnField, occurredOnFieldIsRequiredMsg);			
		} else {			
			occurredOn = occurredOn.trim();
			if (!occurredOn.matches(occurredOnFieldValidationRegex)) {
				errors.put(occurredOnField, occurredOnFieldIsInvalidMsg);
			}			
		}
		
		String beneficiaryAccountNumber = newTransactionCreateModel.getBeneficiaryAccountNumber();
		if (beneficiaryAccountNumber == null || beneficiaryAccountNumber.isEmpty()) {			
			errors.put(beneficiaryAccountNumberField, beneficiaryAccountNumberFieldIsRequiredMsg);			
		} else {			
			beneficiaryAccountNumber = beneficiaryAccountNumber.trim();
			if (!beneficiaryAccountNumber.matches(beneficiaryAccountNumberFieldValidationRegex)) {
				errors.put(beneficiaryAccountNumberField, beneficiaryAccountNumberFieldIsInvalidMsg);
			}			
		}
		
		String notes = newTransactionCreateModel.getNotes();
		if (notes == null || notes.isEmpty()) {			
			errors.put(notesField, notesFieldIsRequiredMsg);			
		} else {			
			notes = notes.trim();
			if (!notes.matches(notesFieldValidationRegex)) {
				errors.put(notesField, notesFieldIsInvalidMsg);
			}			
		}
		
		if (!errors.isEmpty()) {			
			WebValidationException webValidationException = new WebValidationException();
			webValidationException.setErrors(errors);
			throw webValidationException;
		}
	}
	
}
