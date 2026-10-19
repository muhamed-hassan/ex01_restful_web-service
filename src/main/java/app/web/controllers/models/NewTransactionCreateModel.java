package app.web.controllers.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "amount", "occurredOn" })
public class NewTransactionCreateModel {

	private float amount;
	
	private String occurredOn; // yyyy-MM-dd

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getOccurredOn() {
		return occurredOn;
	}

	public void setOccurredOn(String occurredOn) {
		this.occurredOn = occurredOn;
	}
	
}
