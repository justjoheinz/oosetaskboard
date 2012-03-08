package de.oose.taskboard.server.entity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.inject.persist.Transactional;

import de.oose.taskboard.shared.bo.TaskBO;


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
	
	@Override
	@Transactional
	public void deleteTask(int id) {
		Task task = em.find(Task.class, id);
		em.remove(task);
	}
	
	@Override
	@Transactional
	public Task updateTask(int id, String title, String description, String status) {
		Task task = em.find(Task.class, id);
		task.setTitle(title);
		task.setDescription(description);
		task.setStatus(status);
		em.persist(task);
		return task;
	}
	
	@Override
	public List<Task> getTasks() {
		Query query = em.createQuery("from Task");
		List<Task> tasks = query.getResultList();
		return tasks;
	}


}
