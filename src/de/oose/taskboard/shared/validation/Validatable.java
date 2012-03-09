package de.oose.taskboard.shared.validation;

public interface Validatable {
	
	public ValidationResult<? extends Validatable> validate();

}
