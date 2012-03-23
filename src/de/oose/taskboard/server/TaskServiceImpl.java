package de.oose.taskboard.server;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dozer.Mapper;

import com.google.inject.persist.Transactional;

import de.oose.taskboard.client.service.TaskService;
import de.oose.taskboard.server.entity.PersistenceService;
import de.oose.taskboard.server.entity.Task;
import de.oose.taskboard.shared.bo.TaskBO;


public class TaskServiceImpl implements TaskService {

	@Inject
	private Mapper mapper;

	@Inject
	private EntityManager em;
	
	@Inject
	private PersistenceService ps;


	public TaskServiceImpl() {

	}

	

	@Override
	@Transactional
	public TaskBO addTask(TaskBO taskBO) {
		taskBO.validate();
		Task task = ps.createTask(taskBO.getTitle(), taskBO.getDescription(), taskBO.getStatus(), taskBO.getVisibility());
		taskBO = mapper.map(task, TaskBO.class);
		return taskBO;
	}

	@Override
	public TaskBO updateTask(TaskBO taskBO) {
		taskBO.validate();
		Task task = ps.updateTask(taskBO.getId(), taskBO.getTitle(), taskBO.getDescription(), taskBO.getStatus(), taskBO.getVisibility());
		taskBO = mapper.map(task, TaskBO.class);
		return taskBO;
	}

	@Override
	public void deleteTask(TaskBO taskBO) {
		ps.deleteTask(taskBO.getId());
	}
	
	public List<TaskBO> getTasks(String status, int start, int count) {
		List<Task> tasks = ps.getTasks(status, start, count);
		return map(tasks);
	}
	
	public Integer getTaskCount(String status) {
		return ps.getTaskCount(status);
	}
	
	private List<TaskBO> map(List<Task> tasks) {
		if (tasks == null) return null;
		List<TaskBO> taskBOs = new ArrayList<TaskBO>(tasks.size());
		for (Task t : tasks) {
			TaskBO bo = mapper.map(t, TaskBO.class);
			taskBOs.add(bo);
		}
		return taskBOs;
	}
	
}