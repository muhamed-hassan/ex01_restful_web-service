package app.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Table(name = "numeric_validation_rule")
@Entity
@PrimaryKeyJoinColumn(name = "numeric_validation_rule_id")
public class NumericValidationRule extends ValidationRule {

}
