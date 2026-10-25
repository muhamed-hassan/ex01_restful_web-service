package app.web.controllers.transformers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.persistence.entities.IssuedBankCard;

@Component
public class IssuedBankCardTransformer extends BaseTransformer<IssuedBankCard, Object> {
	
	/*@Autowired
	private SimpleDateFormat simpleDateFormat;
	
	public Date toDateOfIssuance(String dateOfIssuanceTextualValue) {
		
		Date dateOfIssuance = null;
		try {
			dateOfIssuance = simpleDateFormat.parse(dateOfIssuanceTextualValue);
		} catch (ParseException e) {}
		
		return dateOfIssuance;
	}*/

}
