package app.persistence.repositories;

import app.persistence.entities.BankCard;
import lib.persistence.repositories.BaseRepository;

public class BankCardRepository extends BaseRepository<BankCard> {

	protected BankCardRepository() {
		super(BankCard.class);
	}

}
