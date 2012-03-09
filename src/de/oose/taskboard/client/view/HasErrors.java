package de.oose.taskboard.client.view;

import de.oose.taskboard.shared.bo.TaskBO;
import de.oose.taskboard.shared.validation.ValidationResult;

public interface HasErrors<T> {
	
	void displayErrors (ValidationResult<TaskBO> result);

}
