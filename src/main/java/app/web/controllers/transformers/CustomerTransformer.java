package app.web.controllers.transformers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.persistence.entities.ContactInfo;
import app.persistence.entities.Customer;
import app.web.controllers.models.BriefViewOfCustomerReadModel;
import app.web.controllers.models.CustomerCreateModel;
import app.web.controllers.models.DetailedViewOfCustomerReadModel;

@Component
public class CustomerTransformer extends BaseTransformer<Customer, Object> {
		
	public Customer toCustomer(CustomerCreateModel customerCreateModel) {
		
		Customer customer = new Customer();
		customer.setName(customerCreateModel.getName());
		customer.setNationalId(customerCreateModel.getNationalId());
		
		try {			
			customer.setDateOfBirth(simpleDateFormat.parse(customerCreateModel.getDateOfBirth()));			
		} catch (ParseException e) {}
		
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setMobile(customerCreateModel.getMobile());
		contactInfo.setEmail(customerCreateModel.getEmail());
		contactInfo.setMailingAddress(customerCreateModel.getMailingAddress());
		customer.setContactInfo(contactInfo);
		
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
		
		return detailedViewOfCustomerReadModel;
	}
	
	public BriefViewOfCustomerReadModel toBriefViewOfCustomerReadModel(Object[] rawRecord) {
		
		BriefViewOfCustomerReadModel briefViewOfCustomerReadModel = new BriefViewOfCustomerReadModel();
		briefViewOfCustomerReadModel.setMobile((String) rawRecord[0]);
		briefViewOfCustomerReadModel.setEmail((String) rawRecord[1]);
		briefViewOfCustomerReadModel.setMailingAddress((String) rawRecord[2]);
		
		return briefViewOfCustomerReadModel;
	}

}
