package app.web.controllers.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "name", "nationalId", "dateOfBirth", "mobile", "email", "mailingAddress" })
public class CustomerCreateModel extends CustomerModel {

	private String name;
	
	private String nationalId;
	
	private String dateOfBirth; // yyyy-MM-dd
	
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
	
}
