package app.persistence.repositories;

import app.persistence.entities.IssuedBankCard;
import lib.persistence.repositories.BaseRepository;

public class IssuedBankCardRepository extends BaseRepository<IssuedBankCard> {

	protected IssuedBankCardRepository() {
		super(IssuedBankCard.class);
	}

}
