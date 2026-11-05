package app.web.controllers.models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "name", "nationalId", "dateOfBirth", "mobile", "email", "mailingAddress", "iban", "balance", "joinedOn",
	"last5IssuedBankCards", "last5BorrowedLoans", "last5Transactions" })
public class DetailedViewOfCustomerReadModel extends CustomerModel {
	
	private String name;
	
	private String nationalId;
	
	private String dateOfBirth; // yyyy-MM-dd
	
	private String iban; // calculated field based on the accountNumber	
	
	private float balance;
	
	private String joinedOn; // yyyy-MM-dd
	
	private ArrayList<IssuedBankCardReadModel> last5IssuedBankCards;
	
	private ArrayList<BorrowedLoanReadModel> last5BorrowedLoans;
	
	private ArrayList<TransactionReadModel> last5Transactions;

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

	public String getJoinedOn() {
		return joinedOn;
	}

	public void setJoinedOn(String joinedOn) {
		this.joinedOn = joinedOn;
	}

	public ArrayList<IssuedBankCardReadModel> getLast5IssuedBankCards() {
		return last5IssuedBankCards;
	}

	public void setLast5IssuedBankCards(ArrayList<IssuedBankCardReadModel> last5IssuedBankCards) {
		this.last5IssuedBankCards = last5IssuedBankCards;
	}

	public ArrayList<BorrowedLoanReadModel> getLast5BorrowedLoans() {
		return last5BorrowedLoans;
	}

	public void setLast5BorrowedLoans(ArrayList<BorrowedLoanReadModel> last5BorrowedLoans) {
		this.last5BorrowedLoans = last5BorrowedLoans;
	}

	public ArrayList<TransactionReadModel> getLast5Transactions() {
		return last5Transactions;
	}

	public void setLast5Transactions(ArrayList<TransactionReadModel> last5Transactions) {
		this.last5Transactions = last5Transactions;
	}
	
}
