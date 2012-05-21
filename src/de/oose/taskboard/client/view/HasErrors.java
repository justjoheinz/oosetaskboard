package de.oose.taskboard.client.view;

import java.io.Serializable;

import de.oose.taskboard.shared.validation.ValidationResult;

//interface
public interface HasErrors {
	
	void displayErrors (ValidationResult<? extends Serializable> result);

}
