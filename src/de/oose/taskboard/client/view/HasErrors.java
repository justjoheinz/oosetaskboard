package de.oose.taskboard.client.view;

import de.oose.taskboard.shared.validation.Validatable;
import de.oose.taskboard.shared.validation.ValidationResult;

//interface
public interface HasErrors {
	
	void displayErrors (ValidationResult<? extends Validatable> result);

}
