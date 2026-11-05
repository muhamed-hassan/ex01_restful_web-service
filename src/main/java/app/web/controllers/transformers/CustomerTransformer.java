package app.web.controllers.transformers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Component;

import app.persistence.entities.BankAccountInformation;
import app.persistence.entities.ContactInformation;
import app.persistence.entities.Customer;
import app.web.controllers.models.BorrowedLoanReadModel;
import app.web.controllers.models.CustomerReadModelForUpdate;
import app.web.controllers.models.CustomerCreateModel;
import app.web.controllers.models.DetailedViewOfCustomerReadModel;
import app.web.controllers.models.IssuedBankCardReadModel;
import app.web.controllers.models.TransactionReadModel;

@Component
public class CustomerTransformer extends BaseTransformer<Customer, Object> {
		
	public Customer toCustomer(CustomerCreateModel customerCreateModel) {
		
		Customer customer = new Customer();
		customer.setName(customerCreateModel.getName());
		customer.setNationalId(customerCreateModel.getNationalId());
		
		try {			
			customer.setDateOfBirth(simpleDateFormat.parse(customerCreateModel.getDateOfBirth()));
			customer.setJoinedOn(simpleDateFormat.parse(customerCreateModel.getJoinedOn()));
		} catch (ParseException e) {}
		
		ContactInformation contactInfo = new ContactInformation();
		contactInfo.setMobile(customerCreateModel.getMobile());
		contactInfo.setEmail(customerCreateModel.getEmail());
		contactInfo.setMailingAddress(customerCreateModel.getMailingAddress());
		customer.setContactInformation(contactInfo);
		
		BankAccountInformation bankAccountInformation = new BankAccountInformation();
		bankAccountInformation.setBalance(customerCreateModel.getBalance());
		customer.setBankAccountInformation(bankAccountInformation);
		
		return customer;
	}
	
	public DetailedViewOfCustomerReadModel toDetailedViewOfCustomerReadModel(Object[] rawRecord) {
				
		DetailedViewOfCustomerReadModel detailedViewOfCustomerReadModel = new DetailedViewOfCustomerReadModel();
		detailedViewOfCustomerReadModel.setName((String) rawRecord[0]);		
		detailedViewOfCustomerReadModel.setNationalId((String) rawRecord[1]);
		detailedViewOfCustomerReadModel.setDateOfBirth(simpleDateFormat.format((Date) rawRecord[2]));	
		detailedViewOfCustomerReadModel.setMobile((String) rawRecord[3]);
		detailedViewOfCustomerReadModel.setEmail((String) rawRecord[4]);
		detailedViewOfCustomerReadModel.setMailingAddress((String) rawRecord[5]);		
		detailedViewOfCustomerReadModel.setIban((String) rawRecord[6]);
		detailedViewOfCustomerReadModel.setBalance((float) rawRecord[7]);
		detailedViewOfCustomerReadModel.setJoinedOn(simpleDateFormat.format((Date) rawRecord[8]));
		
		// last5IssuedBankCards
		// [{id: int32, dateOfIssuance: String[formatted as yyyy-MM-dd]}, type: String, ...]
		ArrayList<IssuedBankCardReadModel> last5IssuedBankCards = new ArrayList<IssuedBankCardReadModel>(); 
		ArrayList<Object[]> rawDataOfIssuedBankCards = (ArrayList<Object[]>) rawRecord[9];
		for (int cursor = 0; cursor < rawDataOfIssuedBankCards.size(); cursor++) {
			
			Object[] currentIssuedBankCard = rawDataOfIssuedBankCards.get(cursor);
			
			IssuedBankCardReadModel issuedBankCardReadModel = new IssuedBankCardReadModel();
			issuedBankCardReadModel.setId((int) currentIssuedBankCard[0]);						
			issuedBankCardReadModel.setDateOfIssuance(simpleDateFormat.format((Date) currentIssuedBankCard[1]));
			issuedBankCardReadModel.setType((String) currentIssuedBankCard[2]);
			
			last5IssuedBankCards.add(issuedBankCardReadModel);
		}
		detailedViewOfCustomerReadModel.setLast5IssuedBankCards(last5IssuedBankCards);
		
		// last5BorrowedLoans
		// [{id: int32, amount: float, borrowingDate: String[formatted as yyyy-MM-dd]}, type: String,  ...]
		ArrayList<BorrowedLoanReadModel> last5BorrowedLoans = new ArrayList<BorrowedLoanReadModel>(); 
		ArrayList<Object[]> rawDataOfBorrowedLoans = (ArrayList<Object[]>) rawRecord[10];
		for (int cursor = 0; cursor < rawDataOfBorrowedLoans.size(); cursor++) {
			
			Object[] currentBorrowedLoan = rawDataOfBorrowedLoans.get(cursor);
			
			BorrowedLoanReadModel borrowedLoanReadModel = new BorrowedLoanReadModel();
			borrowedLoanReadModel.setId((int) currentBorrowedLoan[0]);
			borrowedLoanReadModel.setAmount((float) currentBorrowedLoan[1]);			
			borrowedLoanReadModel.setBorrowingDate(simpleDateFormat.format((Date) currentBorrowedLoan[2]));
			borrowedLoanReadModel.setType((String) currentBorrowedLoan[3]);
			
			last5BorrowedLoans.add(borrowedLoanReadModel);
		}
		detailedViewOfCustomerReadModel.setLast5BorrowedLoans(last5BorrowedLoans);
		
		
		// last5Transactions
		// [{id: int32, amount: float, occurredOn: String[formatted as yyyy-MM-dd]}, type: String,  ...]
		ArrayList<TransactionReadModel> last5Transactions = new ArrayList<TransactionReadModel>();
		ArrayList<Object[]> rawDataOfTransactions = (ArrayList<Object[]>) rawRecord[11];
		for (int cursor = 0; cursor < rawDataOfTransactions.size(); cursor++) {
			
			Object[] currentTransaction = rawDataOfTransactions.get(cursor);
			
			TransactionReadModel transactionReadModel = new TransactionReadModel();
			transactionReadModel.setId((int) currentTransaction[0]);
			transactionReadModel.setAmount((float) currentTransaction[1]);						
			transactionReadModel.setOccurredOn(simpleDateFormat.format((Date) currentTransaction[2]));
			transactionReadModel.setType((String) currentTransaction[3]);
			
			last5Transactions.add(transactionReadModel);
		}
		detailedViewOfCustomerReadModel.setLast5Transactions(last5Transactions);
		
		return detailedViewOfCustomerReadModel;
	}
	
	public CustomerReadModelForUpdate toUpdateViewOfCustomerReadModel(Object[] rawRecord) {
		
		CustomerReadModelForUpdate customerReadModelForUpdate = new CustomerReadModelForUpdate();
		customerReadModelForUpdate.setMobile((String) rawRecord[0]);
		customerReadModelForUpdate.setEmail((String) rawRecord[1]);
		customerReadModelForUpdate.setMailingAddress((String) rawRecord[2]);
		
		return customerReadModelForUpdate;
	}

}
