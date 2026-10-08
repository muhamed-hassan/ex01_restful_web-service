package app.domain;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.persistence.entities.BankCard;
import app.persistence.entities.BankTransaction;
import app.persistence.entities.BorrowedLoan;
import app.persistence.entities.Customer;
import app.persistence.entities.IssuedBankCard;
import app.persistence.entities.Loan;
import app.persistence.entities.Transaction;
import app.persistence.repositories.BankCardRepository;
import app.persistence.repositories.BankTransactionRepository;
import app.persistence.repositories.BorrowedLoanRepository;
import app.persistence.repositories.CustomerRepository;
import app.persistence.repositories.IssuedBankCardRepository;
import app.persistence.repositories.LoanRepository;
import app.persistence.repositories.TransactionRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BankCardRepository bankCardRepository;
	
	@Autowired
	private IssuedBankCardRepository issuedBankCardRepository;
	
	@Autowired
	private BankTransactionRepository bankTransactionRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private BorrowedLoanRepository borrowedLoanRepository;
	
	@Transactional
	public void openBankAccount(Customer newCustomer) {
		
		Random random = new Random(System.currentTimeMillis());        
		long generatedNumber = Math.abs(random.nextLong());		        
		String generatedNumberString = generatedNumber + "";
		String accountNumber = generatedNumberString.substring(0, 8);
		newCustomer.getBankAccountInfo().setAccountNumber(accountNumber);
		
		newCustomer.getBankAccountInfo().setBalance(0);
		
		customerRepository.save(newCustomer);		
	}
	
	@Transactional
	public void issueBankCard(int customerId, int bankCardTypeId) {
		
		Customer issuer = customerRepository.findById(customerId);
		
		BankCard selectedBankCardType = bankCardRepository.findById(bankCardTypeId);
		
		Date dateOfIssuance = new Date();
		
		IssuedBankCard newlyIssuedBankCard = new IssuedBankCard();
		newlyIssuedBankCard.setIssuer(issuer);
		newlyIssuedBankCard.setType(selectedBankCardType);
		newlyIssuedBankCard.setDateOfIssuance(dateOfIssuance);
		
		issuedBankCardRepository.save(newlyIssuedBankCard);		
	}
	
	@Transactional
	public void intiateTransaction(int customerId, int bankTransactionTypeId, float amount) {
		
		Customer initiator = customerRepository.findById(customerId);
				
		BankTransaction bankTransactionType = bankTransactionRepository.findById(bankTransactionTypeId);
		
		Date occurredOn = new Date();
		
		Transaction transaction = new Transaction();
		transaction.setInitiator(initiator);
		transaction.setType(bankTransactionType);
		transaction.setAmount(amount);
		transaction.setOccurredOn(occurredOn);
		
		transactionRepository.save(transaction);		
	}
	
	@Transactional
	public void borrowLoan(int customerId, int loanTypeId, float amount) {
		
		Customer borrower = customerRepository.findById(customerId);
		
		Loan loanType = loanRepository.findById(loanTypeId);
				
		Date borrowingDate = new Date();
		
		BorrowedLoan borrowedLoan = new BorrowedLoan();
		borrowedLoan.setBorrower(borrower);
		borrowedLoan.setType(loanType);
		borrowedLoan.setAmount(amount);
		borrowedLoan.setBorrowingDate(borrowingDate);
		
		borrowedLoanRepository.save(borrowedLoan);
	}

}
