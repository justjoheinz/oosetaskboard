package de.oose.taskboard.shared.bo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import de.oose.taskboard.server.entity.User;
import de.oose.taskboard.shared.enums.TaskVisibility;
import de.oose.taskboard.shared.validation.Validatable;
import de.oose.taskboard.shared.validation.ValidationError;
import de.oose.taskboard.shared.validation.ValidationResult;

public class TaskBO implements Serializable, Validatable {

	private int id;
	@NotNull
	private String title;
	private String description;
	@NotNull
	private String status;
	private long version;
	private UserBO user;
	
	private TaskVisibility visibility;

	public static final String WORK = "WORK";
	public static final String PLANNING = "PLANNING";
	public static final String DONE = "DONE";
	public static final String REVIEW = "REVIEW";

	public TaskBO() {
	}

	public TaskBO(int id, String title, String description, String status) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public TaskVisibility getVisibility() {
		return visibility;
	}

	public void setVisibility(TaskVisibility visibility) {
		this.visibility = visibility;
	}

	public ValidationResult<TaskBO> validate() {
		ValidationResult<TaskBO> result = new ValidationResult<TaskBO>();
		if (title == null || title.length() < 5) {
			result.add(new ValidationError<TaskBO>(this, "title",
					"Der Titel braucht zumindest 5 Buchstaben."));
		}
		if (description == null || description.length() < 10) {
			result.add(new ValidationError<TaskBO>(this, "description",
					"Die Beschreibung braucht zumindest 10 Buchstaben."));
		}
		if (status == null || status.isEmpty()) {
			result.add(new ValidationError<TaskBO>(this, "status",
					"Der Status ist leer."));
		} else if (!PLANNING.equals(status) && !WORK.equals(status)
				&& !DONE.equals(status) && !REVIEW.equals(status)) {
			result.add(new ValidationError<TaskBO>(this, "status",
					"Es wurde kein gültiger Status gesetzt."));
		}
		return result;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public UserBO getUser() {
		return user;
	}

	public void setUser(UserBO user) {
		this.user = user;
	}
	
	

}