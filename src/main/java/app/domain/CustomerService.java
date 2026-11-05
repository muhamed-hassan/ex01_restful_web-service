package app.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.domain.exceptions.DomainValidationException;
import app.persistence.entities.BankCard;
import app.persistence.entities.BankTransaction;
import app.persistence.entities.BankTransaction.TxType;
import app.persistence.entities.BorrowedLoan;
import app.persistence.entities.Customer;
import app.persistence.entities.IbanConfiguration;
import app.persistence.entities.InitiatedTransaction;
import app.persistence.entities.IssuedBankCard;
import app.persistence.entities.Loan;
import app.persistence.repositories.BankCardRepository;
import app.persistence.repositories.BankTransactionRepository;
import app.persistence.repositories.BorrowedLoanRepository;
import app.persistence.repositories.CustomerRepository;
import app.persistence.repositories.IbanConfigurationRepository;
import app.persistence.repositories.InitiatedTransactionRepository;
import app.persistence.repositories.IssuedBankCardRepository;
import app.persistence.repositories.LoanRepository;
import app.web.controllers.models.CustomerUpdateModel;
import app.web.controllers.models.NewTransactionCreateModel;

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
	private InitiatedTransactionRepository initiatedTransactionRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private BorrowedLoanRepository borrowedLoanRepository;
	
	@Autowired
	private IbanConfigurationRepository ibanConfigurationRepository;
	
	@Transactional
	public void openBankAccount(Customer newCustomer) {
		
		Random random = new Random(System.currentTimeMillis());        
		long generatedNumber = Math.abs(random.nextLong());		        
		String generatedNumberString = generatedNumber + "";
		String accountNumber = generatedNumberString.substring(0, 8);
		newCustomer.getBankAccountInformation().setAccountNumber(accountNumber);
		
		customerRepository.save(newCustomer);		
	}
	
	@Transactional
	public void issueBankCard(int customerId, int bankCardTypeId, Date dateOfIssuance) {
		
		Customer issuer = customerRepository.findById(customerId);
		
		BankCard selectedBankCardType = bankCardRepository.findById(bankCardTypeId);
				
		IssuedBankCard newBankCard = new IssuedBankCard();
		newBankCard.setIssuer(issuer);
		newBankCard.setBankCard(selectedBankCardType);
		newBankCard.setDateOfIssuance(dateOfIssuance);
		
		issuedBankCardRepository.save(newBankCard);		
	}
	
	@Transactional
	public void borrowLoan(int customerId, int loanTypeId, float amount, Date borrowingDate) {
		
		Customer borrower = customerRepository.findById(customerId);
		
		Loan loanType = loanRepository.findById(loanTypeId);			
		
		BorrowedLoan borrowedLoan = new BorrowedLoan();
		borrowedLoan.setBorrower(borrower);
		borrowedLoan.setLoan(loanType);
		borrowedLoan.setAmount(amount);
		borrowedLoan.setBorrowingDate(borrowingDate);
		
		borrowedLoanRepository.save(borrowedLoan);
	}
	
	@Transactional
	public void intiateTransaction(int initiatorId, int bankTransactionTypeId, NewTransactionCreateModel newTransactionCreateModel, Date occurredOn) {
				
		BankTransaction bankTransactionType = bankTransactionRepository.findById(bankTransactionTypeId);
				
		InitiatedTransaction initiatedTransaction = new InitiatedTransaction();
		initiatedTransaction.setAmount(newTransactionCreateModel.getAmount());
		initiatedTransaction.setOccurredOn(occurredOn);		
		initiatedTransaction.setBankTransaction(bankTransactionType);
		initiatedTransaction.setNotes(newTransactionCreateModel.getNotes());
		
		Customer initiator = customerRepository.findById(initiatorId);
		Customer beneficiary = null;
		String initiatedTxType = bankTransactionType.getType().toUpperCase();
		if (TxType.DEPOSIT.name().equals(initiatedTxType)) {
			
			if (initiator.getId() == newTransactionCreateModel.getBeneficiaryId()) {
				
				// initiator and beneficiary will be the same in this case
				beneficiary = initiator; // deposit to self
				
			} else {
				
				beneficiary = customerRepository.findById(newTransactionCreateModel.getBeneficiaryId());
				
			}
			
			float adjustedBalanceOfBeneficiary = beneficiary.getBankAccountInformation().getBalance() + newTransactionCreateModel.getAmount();
			beneficiary.getBankAccountInformation().setBalance(adjustedBalanceOfBeneficiary);
			
			customerRepository.update(beneficiary);
			
		} else if (TxType.WITHDRAWAL.name().equals(initiatedTxType)) {
			
			validateInitiatorBalance(initiator.getBankAccountInformation().getBalance(), newTransactionCreateModel.getAmount());
			
			// from initiator (Customer) -> to hisself/herself - initiator and beneficiary will be the same in this case
			beneficiary = initiator;
			
			float adjustedBalanceOfBeneficiary = beneficiary.getBankAccountInformation().getBalance() - newTransactionCreateModel.getAmount();
			beneficiary.getBankAccountInformation().setBalance(adjustedBalanceOfBeneficiary);
			
			customerRepository.update(beneficiary);
			
		} else if (TxType.TRANSFER.name().equals(initiatedTxType)) {
		    
			validateInitiatorBalance(initiator.getBankAccountInformation().getBalance(), newTransactionCreateModel.getAmount());
			
			beneficiary = customerRepository.findById(newTransactionCreateModel.getBeneficiaryId());
			
			float adjustedBalanceOfInitiator = initiator.getBankAccountInformation().getBalance() - newTransactionCreateModel.getAmount();
			initiator.getBankAccountInformation().setBalance(adjustedBalanceOfInitiator);
			customerRepository.update(initiator);
			
			float adjustedBalanceOfBeneficiary = beneficiary.getBankAccountInformation().getBalance() + newTransactionCreateModel.getAmount();
			beneficiary.getBankAccountInformation().setBalance(adjustedBalanceOfBeneficiary);
			customerRepository.update(beneficiary);
			
		}
		
		
		initiatedTransaction.setInitiator(initiator);
		initiatedTransaction.setBeneficiary(beneficiary);
		
		
		initiatedTransactionRepository.save(initiatedTransaction);		
	}
	
	public Object[] getDetailedViewOfCustomerInformation(int customerId) {
				
		Object[] rawRecordPart1 = customerRepository.findDetailedViewOfCustomerInformation(customerId);
		
		IbanConfiguration ibanConfiguration = ibanConfigurationRepository.findById(1);		
		rawRecordPart1[6] = toIban(ibanConfiguration, (String) rawRecordPart1[6]); 
		
		
		Object[] rawRecordPart2 = new Object[3];
		
		ArrayList<Object[]> rawDataOfIssuedBankCards = issuedBankCardRepository.findTheLast5IssuedBankCardsThatBelongsToCustomerOf(customerId);
		rawRecordPart2[0] = rawDataOfIssuedBankCards;
		
		ArrayList<Object[]> rawDataOfBorrowedLoans = borrowedLoanRepository.findTheLast5BorrowedLoansThatBelongsToCustomerOf(customerId);
		rawRecordPart2[1] = rawDataOfBorrowedLoans;
		
		ArrayList<Object[]> rawDataOfTransactions = initiatedTransactionRepository.findTheLast5TransactionsThatBelongsToCustomerOf(customerId);
		rawRecordPart2[2] = rawDataOfTransactions;
		
		
		Object[] rawRecord = new Object[12];
		rawRecord[0] = rawRecordPart1[0];
		rawRecord[1] = rawRecordPart1[1];
		rawRecord[2] = rawRecordPart1[2];
		rawRecord[3] = rawRecordPart1[3];
		rawRecord[4] = rawRecordPart1[4];
		rawRecord[5] = rawRecordPart1[5];
		rawRecord[6] = rawRecordPart1[6];
		rawRecord[7] = rawRecordPart1[7];
		rawRecord[8] = rawRecordPart1[8];
		rawRecord[9] = rawRecordPart2[0];
		rawRecord[10] = rawRecordPart2[1];
		rawRecord[11] = rawRecordPart2[2];
		
		return rawRecord;
	}
	
	public Object[] getUpdateViewOfCustomerInformation(int customerId) {
		
		Object[] rawRecord = customerRepository.findUpdateViewOfCustomerInformation(customerId);
		
		return rawRecord;
	}
	
	@Transactional
	public void updateCustomerInformation(int customerId, CustomerUpdateModel customerUpdateModel) {
		
		customerRepository.update(customerId, customerUpdateModel);
	}
	
	/* *************************************************************************************************** */
	
	private void validateInitiatorBalance(float initiatorBalance, float transactionAmount) {
		
		LinkedHashMap<String, String> errors = new LinkedHashMap<String, String>(0);
		
		if (initiatorBalance < transactionAmount) {
			errors.put("amount", "Insufficient balance !");
		}
		
		if (!errors.isEmpty()) {			
			DomainValidationException domainValidationException = new DomainValidationException();
			domainValidationException.setErrors(errors);
			throw domainValidationException;
		}
	}

	private String toIban(IbanConfiguration ibanConfiguration, String accountNumber) {
		
		return ibanConfiguration.getCountryCode() + ibanConfiguration.getCheckDigits() + 
				ibanConfiguration.getBankCode() + ibanConfiguration.getSortCode() + accountNumber;
	}

}
