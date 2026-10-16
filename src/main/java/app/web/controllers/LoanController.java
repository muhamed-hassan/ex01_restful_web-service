package app.web.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.domain.AdministrativeService;
import app.persistence.entities.Loan;
import app.web.controllers.models.LoanReadModel;
import app.web.controllers.transformers.LoanTransformer;

@RequestMapping("loans")
@RestController
public class LoanController {
	
	@Autowired
	private AdministrativeService administrativeService;
	
	@Autowired
	private LoanTransformer loanTransformer;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<ArrayList<LoanReadModel>> getAvailableLoanTypes() {
	
		ArrayList<Loan> loans = administrativeService.getAvailableLoanTypes();
		
		ArrayList<LoanReadModel> loanReadModels = loanTransformer.toLoanReadModels(loans);
		
		return new ResponseEntity<ArrayList<LoanReadModel>>(loanReadModels, HttpStatus.OK);
	}

}
