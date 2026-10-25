package app.persistence.repositories;

import java.util.LinkedHashSet;

import org.springframework.stereotype.Repository;

import app.persistence.entities.Customer;
import app.web.controllers.models.CustomerUpdateModel;
import lib.persistence.repositories.BaseRepository;

@Repository
public class CustomerRepository extends BaseRepository<Customer> {
	
	public Object[] findDetailedViewOfCustomerInformation(int customerId) {
		
		LinkedHashSet<String> pathsOfFields = new LinkedHashSet<String>();
		pathsOfFields.add("customer.name");
		pathsOfFields.add("customer.nationalId");
		pathsOfFields.add("customer.dateOfBirth");
		pathsOfFields.add("customer.contactInfo.mobile");
		pathsOfFields.add("customer.contactInfo.email");
		pathsOfFields.add("customer.contactInfo.mailingAddress");
		pathsOfFields.add("customer.bankAccountInfo.accountNumber");
		pathsOfFields.add("customer.bankAccountInfo.balance");
		
		Object[] rawRecord = findById(customerId, pathsOfFields);
		
		return rawRecord;
	}
	
	public Object[] findBriefViewOfCustomerInformation(int customerId) {
		
		LinkedHashSet<String> pathsOfFields = new LinkedHashSet<String>();
		pathsOfFields.add("customer.contactInfo.mobile");
		pathsOfFields.add("customer.contactInfo.email");
		pathsOfFields.add("customer.contactInfo.mailingAddress");
		
		Object[] rawRecord = findById(customerId, pathsOfFields);
		
		return rawRecord;
	}
	
	public void update(int customerId, CustomerUpdateModel customerUpdateModel) {
		
		LinkedHashSet<String> pathsOfFields = new LinkedHashSet<String>();
		pathsOfFields.add("customer.contactInfo.mobile");
		pathsOfFields.add("customer.contactInfo.email");
		pathsOfFields.add("customer.contactInfo.mailingAddress");
		
		updateById(customerId, pathsOfFields, customerUpdateModel);		
	}

}
