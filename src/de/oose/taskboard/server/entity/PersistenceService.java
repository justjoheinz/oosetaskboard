package de.oose.taskboard.server.entity;


public interface PersistenceService {

	Task createTask(String title, String description, String status);

	void deleteTask(int id);

	Task updateTask(int id, String title, String description, String status);

}