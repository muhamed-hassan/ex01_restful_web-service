package app.persistence.repositories;

import app.persistence.entities.Transaction;
import lib.persistence.repositories.BaseRepository;

public class TransactionRepository extends BaseRepository<Transaction> {

	protected TransactionRepository() {
		super(Transaction.class);
	}

}
