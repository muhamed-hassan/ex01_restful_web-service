package app.web.controllers.models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "dateOfIssuance", "type"})
public class IssuedBankCardReadModel {
	
	private int id;	
	
	private String dateOfIssuance; // yyyy-MM-dd
	
	private String type;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateOfIssuance() {
		return dateOfIssuance;
	}

	public void setDateOfIssuance(String dateOfIssuance) {
		this.dateOfIssuance = dateOfIssuance;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}
		IssuedBankCardReadModel other = (IssuedBankCardReadModel) object;
		return id == other.getId();
	}

}
