package app.web.controllers.transformers;

import org.springframework.stereotype.Component;

import app.persistence.entities.Loan;
import app.web.controllers.models.LoanReadModel;

@Component
public class LoanTransformer extends BaseTransformer<Loan, LoanReadModel> {

}
