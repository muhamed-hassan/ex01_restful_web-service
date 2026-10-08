package app.persistence.repositories;

import app.persistence.entities.BorrowedLoan;
import lib.persistence.repositories.BaseRepository;

public class BorrowedLoanRepository extends BaseRepository<BorrowedLoan> {

	protected BorrowedLoanRepository() {
		super(BorrowedLoan.class);
	}

}
