package app.web.controllers.transformers;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import app.persistence.entities.BankCard;
import app.web.controllers.models.BankCardReadModel;

@Component
public class BankCardTransformer {
	
	public ArrayList<BankCardReadModel> toBankCardReadModels(ArrayList<BankCard> bankCards) {
		
		ArrayList<BankCardReadModel> bankCardReadModels = new ArrayList<BankCardReadModel>();
		for (int cursor = 0; cursor < bankCards.size(); cursor++) {
			
			BankCard currentBankCard = bankCards.get(cursor);
			
			BankCardReadModel bankCardReadModel = new BankCardReadModel();
			bankCardReadModel.setId(currentBankCard.getId());
			bankCardReadModel.setType(currentBankCard.getType());
			
			bankCardReadModels.add(bankCardReadModel);
		}
		
		return bankCardReadModels;
	}
	
}
