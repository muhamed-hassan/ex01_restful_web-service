package app.web.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.domain.AdministrativeService;
import app.persistence.entities.BankCard;
import app.web.controllers.models.BankCardReadModel;
import app.web.controllers.transformers.BankCardTransformer;

@RequestMapping("bank-cards")
@RestController
public class BankCardController {
	
	@Autowired
	private AdministrativeService administrativeService;
	
	@Autowired
	private BankCardTransformer bankCardTransformer;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<ArrayList<BankCardReadModel>> getAvailableBankCardTypes() {
		
		ArrayList<BankCard> bankCards = administrativeService.getAvailableBankCardTypes();
		
		ArrayList<BankCardReadModel> bankCardReadModels = bankCardTransformer.toModels(bankCards);
		
		return new ResponseEntity<ArrayList<BankCardReadModel>>(bankCardReadModels, HttpStatus.OK);
	}

}
