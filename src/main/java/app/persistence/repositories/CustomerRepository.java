package app.persistence.repositories;

import app.persistence.entities.Customer;
import lib.persistence.repositories.BaseRepository;

public class CustomerRepository extends BaseRepository<Customer> {

	protected CustomerRepository() {
		super(Customer.class);
	}

}
