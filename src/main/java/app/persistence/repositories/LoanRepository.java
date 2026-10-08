package app.persistence.repositories;

import app.persistence.entities.Loan;
import lib.persistence.repositories.BaseRepository;

public class LoanRepository extends BaseRepository<Loan> {

	protected LoanRepository() {
		super(Loan.class);
	}

}
