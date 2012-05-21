package de.oose.taskboard.shared.validation;

import java.io.Serializable;



/** Any business object (BO) which can provide ValidationResults can implement this interface.
 * 
 * @see ValidationResult
 * @see de.oose.taskboard.shared.bo.TaskBO
 * @author markusklink
 *
 */
public interface Validatable {
	
	public ValidationResult<? extends Serializable> validate();

}
