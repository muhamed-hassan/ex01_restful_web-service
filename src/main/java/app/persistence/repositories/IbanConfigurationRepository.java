package app.persistence.repositories;

import app.persistence.entities.IbanConfiguration;
import lib.persistence.repositories.BaseRepository;

public class IbanConfigurationRepository extends BaseRepository<IbanConfiguration> {

	protected IbanConfigurationRepository() {
		super(IbanConfiguration.class);
	}

}
