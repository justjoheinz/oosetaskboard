package de.oose.taskboard.server.entity;

import java.util.List;

import de.oose.taskboard.shared.enums.TaskVisibility;

public interface PersistenceService {

	Task createTask(String title, String description, String status, TaskVisibility visibility);

	void deleteTask(int id);

	Task updateTask(int id, String title, String description, String status, TaskVisibility visibility);

	List<Task> getTasks();

	List<Task> getTasks(String status);

	List<Task> getTasks(String status, int start, int count);

	int getTaskCount(String status);
	
	List<Task> getPrivateTasks(int userId);
}