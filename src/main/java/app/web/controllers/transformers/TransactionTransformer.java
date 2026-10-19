package app.web.controllers.transformers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionTransformer {
	
	@Autowired
	private SimpleDateFormat simpleDateFormat;
	
	public Date toOccurredOn(String occurredOnTextualValue) {
		
		Date occurredOn = null;
		try {
			occurredOn = simpleDateFormat.parse(occurredOnTextualValue);
		} catch (ParseException e) {}
		
		return occurredOn;
	}

}
