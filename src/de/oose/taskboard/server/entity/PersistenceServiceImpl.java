package de.oose.taskboard.server.entity;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;

import com.google.inject.persist.Transactional;

public class PersistenceServiceImpl implements PersistenceService {

	@Inject
	private EntityManager em;
//	
//	@Inject 
//	private Logger logger;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.oose.taskboard.server.PersistenceService#createTask(java.lang.String,
	 * java.lang.String, java.lang.String)
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
	public Task updateTask(int id, String title, String description,
			String status) {
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

	@Override
	public List<Task> getTasks(String status) {
		Query query = createTaskQuery(status);
		List<Task> tasks = query.getResultList();
		return tasks;
	}

	@Override
	public List<Task> getTasks(String status, int start, int count) {
		Query query = createTaskQuery(status);
		query.setFirstResult(start);
		query.setMaxResults(count);
		List<Task> tasks = query.getResultList();
		return tasks;
	}
	
	@Override
	public int getTaskCount(String status) {
		Query query = em.createQuery("select count(*) from Task t");
		return query.getFirstResult();
	}

	private Query createTaskQuery(String status) {
		return em.createQuery("from Task t where t.status = '" + status + "'");
	}

}
