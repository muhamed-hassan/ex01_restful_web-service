package app.web.controllers.transformers;

import org.springframework.stereotype.Component;

import app.persistence.entities.BankCard;
import app.web.controllers.models.BankCardReadModel;

@Component
public class BankCardTransformer extends BaseTransformer<BankCard, BankCardReadModel> {
	
}
