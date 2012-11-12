package de.oose.taskboard.server.service;

import java.util.List;

import de.oose.taskboard.server.entity.Task;
import de.oose.taskboard.shared.enums.TaskVisibility;

public interface TaskPersistenceService {

	Task createTask(int userId, String title, String description, String status, TaskVisibility visibility);

	void deleteTask(int id);

	Task updateTask(int id, String title, String description, String status, TaskVisibility visibility);

	List<Task> getTasks(String status, int start, int count);

	int getTaskCount(int userID, String status);
	
	List<Task> getPrivateTasks(int userId);

	List<Task> getAllTasks();
}