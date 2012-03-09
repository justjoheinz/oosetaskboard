package de.oose.taskboard.shared.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import de.oose.taskboard.shared.validation.ValidationResult;

public class TaskBO implements Serializable {

	private int id;
	@NotNull
	private String title;
	private String description;
	@NotNull
	private String status;

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

	public List<ValidationResult<TaskBO>> validate() {
		List<ValidationResult<TaskBO>> result = new ArrayList<ValidationResult<TaskBO>>();
		if (title == null || title.length() < 5) {
			result.add(new ValidationResult<TaskBO>(this, "title",
					"Der Titel braucht zumindest 5 Buchstaben."));
		}
		if (description == null || description.length() < 10) {
			result.add(new ValidationResult<TaskBO>(this, "description",
					"Die Beschreibung braucht zumindest 10 Buchstaben."));
		}
		return result;
	}

}