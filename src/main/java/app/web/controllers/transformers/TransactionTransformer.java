package app.web.controllers.transformers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.persistence.entities.Transaction;

@Component
public class TransactionTransformer extends BaseTransformer<Transaction, Object> {
	
	/*@Autowired
	private SimpleDateFormat simpleDateFormat;
	
	public Date toOccurredOn(String occurredOnTextualValue) {
		
		Date occurredOn = null;
		try {
			occurredOn = simpleDateFormat.parse(occurredOnTextualValue);
		} catch (ParseException e) {}
		
		return occurredOn;
	}*/

}
