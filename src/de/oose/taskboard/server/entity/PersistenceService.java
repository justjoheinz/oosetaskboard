package de.oose.taskboard.server.entity;

import java.util.List;

import de.oose.taskboard.shared.enums.TaskVisibility;

public interface PersistenceService {

	Task createTask(int userId, String title, String description, String status, TaskVisibility visibility);

	void deleteTask(int id);

	Task updateTask(int id, String title, String description, String status, TaskVisibility visibility);

	List<Task> getTasks(String status, int start, int count);

	int getTaskCount(int userID, String status);
	
	List<Task> getPrivateTasks(int userId);
}