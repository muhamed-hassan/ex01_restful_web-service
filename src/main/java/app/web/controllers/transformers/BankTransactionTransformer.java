package app.web.controllers.transformers;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import app.persistence.entities.BankTransaction;
import app.web.controllers.models.BankTransactionReadModel;

@Component
public class BankTransactionTransformer extends BaseTransformer<BankTransaction, BankTransactionReadModel> {
	
	/*public ArrayList<BankTransactionReadModel> toBankTransactionReadModels(ArrayList<BankTransaction> bankTransactions) {
		
		ArrayList<BankTransactionReadModel> bankTransactionReadModels = new ArrayList<BankTransactionReadModel>();
		for (int cursor = 0; cursor < bankTransactions.size(); cursor++) {
			
			BankTransaction currentBankTransaction = bankTransactions.get(cursor);
			
			BankTransactionReadModel bankTransactionReadModel = new BankTransactionReadModel();
			bankTransactionReadModel.setId(currentBankTransaction.getId());
			bankTransactionReadModel.setType(currentBankTransaction.getType());
			
			bankTransactionReadModels.add(bankTransactionReadModel);
		}
		
		return bankTransactionReadModels;
	}*/

}
