package app.web.controllers.transformers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import app.persistence.entities.ContactInfo;
import app.persistence.entities.Customer;
import app.web.controllers.models.CustomerCreateModel;

@Component
public class CustomerTransformer {
	
	@Value("${date.pattern}")
    private String datePattern;
	
	public Customer toCustomer(CustomerCreateModel customerCreateModel) {
		
		Customer customer = new Customer();
		customer.setName(customerCreateModel.getName());
		customer.setNationalId(customerCreateModel.getNationalId());
		
		try {			
			DateFormat dateFormat = new SimpleDateFormat(datePattern);
			customer.setDateOfBirth(dateFormat.parse(customerCreateModel.getDateOfBirth()));			
		} catch (ParseException e) {
			throw new IllegalArgumentException("Date of birth contains invalid characters which shall be in the form of yyyy-MM-dd.");
		}
		
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setMobile(customerCreateModel.getMobile());
		contactInfo.setEmail(customerCreateModel.getEmail());
		contactInfo.setMailingAddress(customerCreateModel.getMailingAddress());
		customer.setContactInfo(contactInfo);
		
		return customer;
	}

}
