package de.oose.taskboard.shared.validation;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ValidationException extends RuntimeException{
	
	private ValidationResult<? extends Serializable> validationResult;
	
	private ValidationException() {
		validationResult = null;
	}
	
	public <T extends Serializable> ValidationException(ValidationResult<T> validationResult) {
		this.validationResult = (ValidationResult<? extends Serializable>)validationResult;
	}
	
	public <T extends Serializable> ValidationResult<T> getValidationResult() {
		return (ValidationResult<T>) validationResult;
	}
	
	public boolean hasErrors() {
		return (validationResult != null && !validationResult.isEmpty());
	}
	
	

}
