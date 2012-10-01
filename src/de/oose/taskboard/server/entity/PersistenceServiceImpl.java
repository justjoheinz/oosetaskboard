package de.oose.taskboard.server.entity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
	public Task createTask(int userId, String title, String description, String status,
			TaskVisibility visibility) {
		
		User user = em.find(User.class, userId);
		Task task = new Task();
		task.setTitle(title);
		task.setDescription(description);
		task.setStatus(status);
		task.setVisibility(visibility);
		task.setUser(user);
		user.getTasks().add(task);
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
		LOG.info("querying tasks with status {}", status);
		return execQuery(status);
	}

	@Override
	public List<Task> getTasks(String status, int start, int count) {
		LOG.info("querying tasks with status {}, from {}, up to {}",
				new Object[] { status, start, count });
		return execQuery(status, start, count);
	}

	@Override
	public int getTaskCount(int userId, String status) {
		List<Task> tasks = getTasks(status);
		List<Task> result = new ArrayList<Task>();
		if (tasks == null) return 0;
		for (Task task : tasks) {
			if (task.getUser().getId() == userId || task.getVisibility().equals(TaskVisibility.PUBLIC)) {
				result.add(task);
			}
		}
		int count = result.size();
		LOG.info("There are {} tasks with status {}", count, status);
		return count;
	}

	@Override
	public List<Task> getPrivateTasks(int userId) {
		String queryText = "from Task t where t.visibility = '"
				+ TaskVisibility.PRIVATE + "' and t.user=" + userId;
		TypedQuery<Task> query = em.createQuery(queryText, Task.class);
		return query.getResultList();
	}

	private TypedQuery<Task> createTaskQuery(String status) {
		String query = "from Task t where t.status = '" + status + "'";
		LOG.trace("Query is '{}'", query);
		return em.createQuery(query, Task.class);
	}

	private List<Task> execQuery(String status) {
		TypedQuery<Task> query = createTaskQuery(status);
		return query.getResultList();
	}

	private List<Task> execQuery(String status, int start, int count) {
		TypedQuery<Task> query = createTaskQuery(status);
		query.setFirstResult(start);
		query.setMaxResults(count);
		return query.getResultList();
	}

}
