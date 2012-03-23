package de.oose.taskboard.shared.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserBO implements Serializable{
	
	private long version;
	private String name;
	private int id;
	private List<TaskBO> tasks = new ArrayList<TaskBO>();
	
	public UserBO() {
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<TaskBO> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskBO> tasks) {
		this.tasks = tasks;
	}

	
}
