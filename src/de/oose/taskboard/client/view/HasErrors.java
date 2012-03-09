package de.oose.taskboard.client.view;

import java.util.List;

import de.oose.taskboard.shared.validation.ValidationResult;

public interface HasErrors<T> {
	
	void displayErrors (List<ValidationResult<T>> result);

}
