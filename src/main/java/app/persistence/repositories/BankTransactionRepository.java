package app.persistence.repositories;

import app.persistence.entities.BankTransaction;
import lib.persistence.repositories.BaseRepository;

public class BankTransactionRepository extends BaseRepository<BankTransaction> {

	protected BankTransactionRepository() {
		super(BankTransaction.class);
	}

}
