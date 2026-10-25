package app.web.controllers.transformers;

import org.springframework.stereotype.Component;

import app.persistence.entities.BankTransaction;
import app.web.controllers.models.BankTransactionReadModel;

@Component
public class BankTransactionTransformer extends BaseTransformer<BankTransaction, BankTransactionReadModel> {

}
