package de.oose.taskboard.server;

import com.google.inject.persist.Transactional;

import de.oose.taskboard.server.entity.Task;

public interface PersistenceService {

	@Transactional
	public abstract Task createTask(String title, String description,
			String status);

	public abstract void removeTask(int id);

}