package app.web.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.domain.AdministrativeService;
import app.persistence.entities.BankTransaction;
import app.web.controllers.models.BankTransactionReadModel;
import app.web.controllers.transformers.BankTransactionTransformer;

@RequestMapping("bank-transactions")
@RestController
public class BankTransactionController {

	@Autowired
	private AdministrativeService administrativeService;
	
	@Autowired
	private BankTransactionTransformer bankTransactionTransformer;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<ArrayList<BankTransactionReadModel>> getAvailableBankTransactionTypes() {
		
		ArrayList<BankTransaction> bankTransactions = administrativeService.getAvailableBankTransactionTypes();
		
		ArrayList<BankTransactionReadModel> bankTransactionReadModels = bankTransactionTransformer.toModels(bankTransactions);
		
		return new ResponseEntity<ArrayList<BankTransactionReadModel>>(bankTransactionReadModels, HttpStatus.OK);
	}
	
}
