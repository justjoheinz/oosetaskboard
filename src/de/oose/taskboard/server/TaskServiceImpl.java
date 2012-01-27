package de.oose.taskboard.server;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.dozer.Mapper;

import com.google.inject.persist.Transactional;

import de.oose.taskboard.client.service.TaskService;
import de.oose.taskboard.server.entity.Task;
import de.oose.taskboard.shared.bo.TaskBO;


public class TaskServiceImpl implements TaskService {

	@Inject
	Mapper mapper;

	@Inject
	EntityManager em;

	private static List<TaskBO> tasks = new ArrayList<TaskBO>();

	public TaskServiceImpl() {

	}

	@Override
	public List<TaskBO> getTasks() {
		Query query = em.createQuery("from Task");
		List<Task> tasks = query.getResultList();
		List<TaskBO> taskBOs = new ArrayList<TaskBO>();
		for (Task t : tasks) {
			TaskBO bo = mapper.map(t, TaskBO.class);
			taskBOs.add(bo);
		}
		return taskBOs;
	}

	@Override
	@Transactional
	public TaskBO addTask(TaskBO taskBO) {
		tasks.add(taskBO);
		Task entity = mapper.map(taskBO, Task.class);
		em.persist(entity);
		taskBO = mapper.map(entity, TaskBO.class);
		return taskBO;
	}
}