package de.oose.taskboard.server.service;

import java.util.List;

import de.oose.taskboard.server.entity.Task;
import de.oose.taskboard.shared.enums.TaskVisibility;

/**
 * The interface TaskPersistenceService gives an abstraction to the Database by
 * using primitive types to create or query for entities.
 * 
 * The motivation is to encapsulate database access in one place.
 * 
 * @author markusklink
 * 
 */
public interface TaskPersistenceService {

	Task createTask(int userId, String title, String description,
			String status, TaskVisibility visibility);

	void deleteTask(int id);

	Task updateTask(int id, String title, String description, String status,
			TaskVisibility visibility);

	List<Task> getTasks(String status, int start, int count);

	int getTaskCount(int userID, String status);

	List<Task> getPrivateTasks(int userId);

	List<Task> getAllTasks();
}