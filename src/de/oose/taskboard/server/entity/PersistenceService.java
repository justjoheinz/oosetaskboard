package de.oose.taskboard.server.entity;

import java.util.List;


public interface PersistenceService {

	Task createTask(String title, String description, String status);

	void deleteTask(int id);

	Task updateTask(int id, String title, String description, String status);

	List<Task> getTasks();

}