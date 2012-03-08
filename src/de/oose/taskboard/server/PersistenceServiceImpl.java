package de.oose.taskboard.server;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.inject.persist.Transactional;

import de.oose.taskboard.server.entity.Task;

public class PersistenceServiceImpl implements PersistenceService {
	
	@Inject
	private EntityManager em;
	
	/* (non-Javadoc)
	 * @see de.oose.taskboard.server.PersistenceService#createTask(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public Task createTask(String title, String description, String status) {
		Task task = new Task();
		task.setTitle(title);
		task.setDescription(description);
		task.setStatus(status);
		em.persist(task);
		return task;
	}

}
