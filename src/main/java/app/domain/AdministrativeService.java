package app.domain;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.persistence.entities.BankCard;
import app.persistence.entities.BankTransaction;
import app.persistence.entities.Loan;
import app.persistence.repositories.BankCardRepository;
import app.persistence.repositories.BankTransactionRepository;
import app.persistence.repositories.LoanRepository;

@Service
public class AdministrativeService {

	@Autowired
	private BankCardRepository bankCardRepository;
	
	@Autowired
	private BankTransactionRepository bankTransactionRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	public ArrayList<BankCard> getAvailableBankCardTypes() {
		
		ArrayList<BankCard> availableBankCardTypes = bankCardRepository.findAll();
		
		return availableBankCardTypes;		
	}
	
	public ArrayList<BankTransaction> getAvailableBankTransactionTypes() {
		
		ArrayList<BankTransaction> availableBankTransactionTypes = bankTransactionRepository.findAll();
		
		return availableBankTransactionTypes;
	}
	
	public ArrayList<Loan> getAvailableLoanTypes() {
		
		ArrayList<Loan> availableLoanTypes = loanRepository.findAll();
		
		return availableLoanTypes;
	}
	
}
