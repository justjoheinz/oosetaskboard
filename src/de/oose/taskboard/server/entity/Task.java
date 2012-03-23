package de.oose.taskboard.server.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import de.oose.taskboard.shared.enums.TaskVisibility;

@Entity
public class Task {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	private String description;
	private String status;
	@Enumerated
	private TaskVisibility visibility;
	@Version
	private long version;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private User user;

	public Task() {

	}
	
	public TaskVisibility getVisibility() {
		return visibility;
	}

	public void setVisibility(TaskVisibility visibility) {
		this.visibility = visibility;
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

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
