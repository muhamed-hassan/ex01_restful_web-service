package app.web.controllers.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "mobile", "email", "mailingAddress" })
public class BriefViewOfCustomerReadModel extends CustomerModel {

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
