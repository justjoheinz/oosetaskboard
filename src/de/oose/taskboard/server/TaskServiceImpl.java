package de.oose.taskboard.server;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.oose.taskboard.client.service.TaskService;
import de.oose.taskboard.shared.bo.TaskBO;

public class TaskServiceImpl extends RemoteServiceServlet implements
		TaskService {

	Mapper mapper = null;
	private static List<TaskBO> tasks = new ArrayList<TaskBO>();

	public TaskServiceImpl() {
		mapper = new DozerBeanMapper();

		tasks.add(new TaskBO(1, "Task1", "Ein Task", TaskBO.DONE));
		tasks.add(new TaskBO(2, "Task2", "Ein Task", TaskBO.PLANNING));
		tasks.add(new TaskBO(3, "Task3", "Ein Task", TaskBO.DONE));
		tasks.add(new TaskBO(4, "Task4", "Ein Task", TaskBO.REVIEW));
	}

	@Override
	public List<TaskBO> getTasks() {
		return tasks;
	}

	@Override
	public TaskBO addTask(TaskBO taskBO) {
		tasks.add(taskBO);
		return taskBO;
	}
}