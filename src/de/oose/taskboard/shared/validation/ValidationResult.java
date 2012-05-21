package de.oose.taskboard.shared.validation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class ValidationResult<T extends Serializable>  extends ArrayList<ValidationError<T>>{

	public ValidationResult() {
		super();
	}

	public ValidationResult(Collection<? extends ValidationError<T>> arg0) {
		super(arg0);
	}

	public ValidationResult(int arg0) {
		super(arg0);
	}
	
	public boolean hasErrors() {
		return !isOk();
	}
	
	public boolean isOk() {
		return isEmpty();
	}

	
}
