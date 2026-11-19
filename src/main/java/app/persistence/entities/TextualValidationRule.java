package app.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Table(name = "textual_validation_rule")
@Entity
@PrimaryKeyJoinColumn(name = "textual_validation_rule_id")
public class TextualValidationRule extends ValidationRule {
	
	@Column(name = "required_message")
	private String requiredMessage; 
	
	@Column(name = "validation_regex_pattern")
	private String validationRegexPattern;

	public String getRequiredMessage() {
		return requiredMessage;
	}

	public void setRequiredMessage(String requiredMessage) {
		this.requiredMessage = requiredMessage;
	}

	public String getValidationRegexPattern() {
		return validationRegexPattern;
	}

	public void setValidationRegexPattern(String validationRegexPattern) {
		this.validationRegexPattern = validationRegexPattern;
	}
	
}
