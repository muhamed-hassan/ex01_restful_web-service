package app.web.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.domain.CustomerService;
import app.persistence.entities.Customer;
import app.web.controllers.models.CustomerReadModelForUpdate;
import app.web.controllers.models.CustomerCreateModel;
import app.web.controllers.models.CustomerUpdateModel;
import app.web.controllers.models.DetailedViewOfCustomerReadModel;
import app.web.controllers.models.NewBankCardCreateModel;
import app.web.controllers.models.NewLoanCreateModel;
import app.web.controllers.models.NewTransactionCreateModel;
import app.web.controllers.transformers.CustomerTransformer;
import app.web.controllers.transformers.IssuedBankCardTransformer;
import app.web.controllers.transformers.InitiatedTransactionTransformer;
import app.web.controllers.validators.CustomerValidator;

@RequestMapping("customers")
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerTransformer customerTransformer;
	
	@Autowired
	private IssuedBankCardTransformer issuedBankCardTransformer;
	
	@Autowired
	private InitiatedTransactionTransformer initiatedTransactionTransformer;
	
	@Autowired
	private CustomerValidator customerValidator;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> openBankAccount(@RequestBody CustomerCreateModel customerCreateModel) {
		
		customerValidator.validateCustomerCreateModel(customerCreateModel);	
		
		Customer customer = customerTransformer.toCustomer(customerCreateModel);
		
		customerService.openBankAccount(customer);
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "{customerId}/new-bank-cards/{bankCardTypeId}")
	public ResponseEntity<Object> issueBankCard(@PathVariable int customerId, @PathVariable int bankCardTypeId, @RequestBody NewBankCardCreateModel newBankCardCreateModel) {
		
		customerValidator.validateNewBankCardCreateModel(newBankCardCreateModel);
		
		Date dateOfIssuance = issuedBankCardTransformer.toDate(newBankCardCreateModel.getDateOfIssuance());
		
		customerService.issueBankCard(customerId, bankCardTypeId, dateOfIssuance);
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "{customerId}/new-loans/{loanTypeId}")
	public ResponseEntity<Object> borrowLoan(@PathVariable int customerId, @PathVariable int loanTypeId, @RequestBody NewLoanCreateModel newLoanCreateModel) {
		
		customerValidator.validateNewLoanCreateModel(newLoanCreateModel);
		
		Date dateOfIssuance = issuedBankCardTransformer.toDate(newLoanCreateModel.getBorrowingDate());
		
		customerService.borrowLoan(customerId, loanTypeId, newLoanCreateModel.getAmount(), dateOfIssuance);
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "{initiatorId}/new-transactions/{bankTransactionTypeId}")
	public ResponseEntity<Object> initiateTransaction(@PathVariable int initiatorId, @PathVariable int bankTransactionTypeId, @RequestBody NewTransactionCreateModel newTransactionCreateModel) {
		
		customerValidator.validateNewTransactionCreateModel(newTransactionCreateModel);
		
		Date transformedOccurredOnStrDate = initiatedTransactionTransformer.toDate(newTransactionCreateModel.getOccurredOn());
		
		customerService.initiateTransaction(initiatorId, bankTransactionTypeId, newTransactionCreateModel, transformedOccurredOnStrDate);
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "{customerId}/read-only-detailed-view")
	public ResponseEntity<DetailedViewOfCustomerReadModel> getDetailedViewOfCustomerInformation(@PathVariable int customerId) {
		
		Object[] rawRecord = customerService.getDetailedViewOfCustomerInformation(customerId);
				
		DetailedViewOfCustomerReadModel detailedCustomerReadModel = customerTransformer.toDetailedViewOfCustomerReadModel(rawRecord);
		
		return new ResponseEntity<DetailedViewOfCustomerReadModel>(detailedCustomerReadModel, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "{customerId}/read-view-for-update")
	public ResponseEntity<CustomerReadModelForUpdate> getUpdateViewOfCustomerInformation(@PathVariable int customerId) {
		
		Object[] rawRecord = customerService.getUpdateViewOfCustomerInformation(customerId);
				
		CustomerReadModelForUpdate briefCustomerReadModel = customerTransformer.toUpdateViewOfCustomerReadModel(rawRecord);
		
		return new ResponseEntity<CustomerReadModelForUpdate>(briefCustomerReadModel, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "{customerId}")
	public ResponseEntity<Object> updateCustomerInformation(@PathVariable int customerId, @RequestBody CustomerUpdateModel customerUpdateModel) {

		customerValidator.validateCustomerUpdateModel(customerUpdateModel);
		
		customerService.updateCustomerInformation(customerId, customerUpdateModel);
		
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
}
