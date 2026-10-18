package app.web.controllers.transformers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.persistence.entities.Loan;
import app.web.controllers.models.LoanReadModel;

@Component
public class LoanTransformer {
	
	@Autowired
	private SimpleDateFormat simpleDateFormat;
	
	public ArrayList<LoanReadModel> toLoanReadModels(ArrayList<Loan> loans) {
		
		ArrayList<LoanReadModel> loanReadModels = new ArrayList<LoanReadModel>();
		for (int cursor = 0; cursor < loans.size(); cursor++) {
			
			Loan currentLoan = loans.get(cursor);
			
			LoanReadModel loanReadModel = new LoanReadModel();
			loanReadModel.setId(currentLoan.getId());
			loanReadModel.setType(currentLoan.getType());
			
			loanReadModels.add(loanReadModel);
		}
		
		return loanReadModels;
	}
	
	public Date toBorrowingDate(String borrowingDateTextualValue) {
		
		Date borrowingDate = null;
		try {
			borrowingDate = simpleDateFormat.parse(borrowingDateTextualValue);
		} catch (ParseException e) {}
		
		return borrowingDate;
	}

}
