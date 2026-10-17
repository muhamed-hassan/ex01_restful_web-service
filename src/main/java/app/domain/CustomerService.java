package app.domain;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.persistence.entities.BankAccountInformation;
import app.persistence.entities.BankCard;
import app.persistence.entities.BankTransaction;
import app.persistence.entities.BorrowedLoan;
import app.persistence.entities.Customer;
import app.persistence.entities.IbanConfiguration;
import app.persistence.entities.IssuedBankCard;
import app.persistence.entities.Loan;
import app.persistence.entities.Transaction;
import app.persistence.repositories.BankCardRepository;
import app.persistence.repositories.BankTransactionRepository;
import app.persistence.repositories.BorrowedLoanRepository;
import app.persistence.repositories.CustomerRepository;
import app.persistence.repositories.IbanConfigurationRepository;
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
	
	@Autowired
	private IbanConfigurationRepository ibanConfigurationRepository;
	
	@Transactional
	public void openBankAccount(Customer newCustomer) {
		
		BankAccountInformation bankAccountInformation = new BankAccountInformation();
		
		Random random = new Random(System.currentTimeMillis());        
		long generatedNumber = Math.abs(random.nextLong());		        
		String generatedNumberString = generatedNumber + "";
		String accountNumber = generatedNumberString.substring(0, 8);
		bankAccountInformation.setAccountNumber(accountNumber);
		
		bankAccountInformation.setBalance(0);
		
		newCustomer.setBankAccountInformation(bankAccountInformation);
		
		customerRepository.save(newCustomer);		
	}
	
	@Transactional
	public void issueBankCard(int customerId, int bankCardTypeId) {
		
		Customer issuer = customerRepository.findById(customerId);
		
		BankCard selectedBankCardType = bankCardRepository.findById(bankCardTypeId);
		
		//Date dateOfIssuance = new Date();
		
		IssuedBankCard newlyIssuedBankCard = new IssuedBankCard();
		newlyIssuedBankCard.setIssuer(issuer);
		newlyIssuedBankCard.setType(selectedBankCardType);
		newlyIssuedBankCard.setDateOfIssuance(/*dateOfIssuance*/null);
		
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
	
	public Object[] getDetailedViewOfCustomerInformation(int customerId) {
				
		Object[] rawRecord = customerRepository.findDetailedViewOfCustomerInformation(customerId);
		
		IbanConfiguration ibanConfiguration = ibanConfigurationRepository.findById(1);
		
		rawRecord[6] = toIban(ibanConfiguration, (String) rawRecord[6]); 
		
		return rawRecord;
	}
	
	public Object[] getBriefViewOfCustomerInformation(int customerId) {
		
		Object[] rawRecord = customerRepository.findBriefViewOfCustomerInformation(customerId);
		
		return rawRecord;
	}
	
	/* *************************************************************************************************** */
	
	private String toIban(IbanConfiguration ibanConfiguration, String accountNumber) {
		
		return ibanConfiguration.getCountryCode() + ibanConfiguration.getCheckDigits() + 
				ibanConfiguration.getBankCode() + ibanConfiguration.getSortCode() + accountNumber;
	}

}
