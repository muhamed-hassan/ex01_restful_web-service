package app.web.controllers.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "name", "nationalId", "dateOfBirth", "mobile", "email", "mailingAddress", "iban", "balance" })
public class DetailedViewOfCustomerReadModel extends CustomerModel {
	
	private String name;
	
	private String nationalId;
	
	private String dateOfBirth; // yyyy-MM-dd
	
	private String iban; // calculated field based on the accountNumber	
	
	private float balance;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

}
