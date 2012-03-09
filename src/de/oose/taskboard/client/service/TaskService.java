package de.oose.taskboard.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.oose.taskboard.server.entity.Task;
import de.oose.taskboard.shared.bo.TaskBO;

@RemoteServiceRelativePath("GWT.rpc")
public interface TaskService extends RemoteService {
	public List<TaskBO> getTasks();
	public List<TaskBO> getTasks(String status);
	public List<TaskBO> getTasks(String status, int start, int count);
	public TaskBO addTask(TaskBO taskBO);
	public TaskBO updateTask(TaskBO taskBO);
	public void deleteTask(TaskBO taskBO);
	public Integer getTaskCount(String status);
}
