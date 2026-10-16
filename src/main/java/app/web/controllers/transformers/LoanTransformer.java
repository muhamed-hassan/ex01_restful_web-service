package app.web.controllers.transformers;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import app.persistence.entities.Loan;
import app.web.controllers.models.LoanReadModel;

@Component
public class LoanTransformer {
	
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

}
