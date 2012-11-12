package de.oose.taskboard.server.service;

import de.oose.taskboard.server.entity.User;

public interface UserPersistenceService {
	
	public User getAccount(String name, boolean create);

}
