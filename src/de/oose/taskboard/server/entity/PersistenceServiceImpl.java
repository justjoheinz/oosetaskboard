package de.oose.taskboard.server.entity;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.persist.Transactional;

import de.oose.taskboard.shared.enums.TaskVisibility;

public class PersistenceServiceImpl implements PersistenceService {

	@Inject
	private EntityManager em;

	static final Logger LOG = LoggerFactory
			.getLogger(PersistenceServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.oose.taskboard.server.PersistenceService#createTask(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public Task createTask(String title, String description, String status,
			TaskVisibility visibility) {
		Task task = new Task();
		task.setTitle(title);
		task.setDescription(description);
		task.setStatus(status);
		task.setVisibility(visibility);
		em.persist(task);
		LOG.info("Created task '{}' with status '{}'", title, status);
		return task;
	}

	@Override
	@Transactional
	public void deleteTask(int id) {
		Task task = em.find(Task.class, id);
		LOG.info("deleted task {}", id);
		em.remove(task);
	}

	@Override
	@Transactional
	public Task updateTask(int id, String title, String description,
			String status, TaskVisibility visibility) {
		Task task = em.find(Task.class, id);
		task.setTitle(title);
		task.setDescription(description);
		task.setStatus(status);
		task.setVisibility(visibility);
		em.persist(task);
		LOG.info("Updated task {} with title '{}'", id, title);
		return task;
	}


	
	private List<Task> getTasks(String status) {
		Query query = createTaskQuery(status);
		List<Task> tasks = query.getResultList();
		LOG.info("found {} tasks with status {}", tasks.size(), status);
		return tasks;
	}

	@Override
	public List<Task> getTasks(String status, int start, int count) {
		Query query = createTaskQuery(status);
		query.setFirstResult(start);
		query.setMaxResults(count);
		List<Task> tasks = query.getResultList();
		LOG.info("found {} tasks with status {}, from {}, up to {}",
				new Object[] { tasks.size(), status, start, count });
		return tasks;
	}

	@Override
	public int getTaskCount(String status) {
		// TODO this should work, but it does not
		// Query query =
		// em.createQuery("select count(*) from Task t where t.status = '" +
		// status + "'");
		// return query.getFirstResult();
		// TODO hence we do it brute force - again
		int count = getTasks(status).size();
		LOG.info("There are {} tasks with status {}", count, status);
		return count;
	}

	private Query createTaskQuery(String status) {
		String query = "from Task t where t.status = '" + status + "'";
		LOG.trace("Query is '{}'", query);
		return em.createQuery(query);
	}

	@Override
	public List<Task> getPrivateTasks(int userId) {
		String queryText = "from Task t where t.visibility = '"
				+ TaskVisibility.PRIVATE + "' and t.user=" + userId;
		Query query = em.createQuery(queryText, Task.class);
		return query.getResultList();
	}
	
}
