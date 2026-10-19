package app.web.controllers.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "amount", "borrowingDate" })
public class NewLoanCreateModel {
	
	private float amount;
	
	private String borrowingDate; // yyyy-MM-dd

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getBorrowingDate() {
		return borrowingDate;
	}

	public void setBorrowingDate(String borrowingDate) {
		this.borrowingDate = borrowingDate;
	}
	
}
