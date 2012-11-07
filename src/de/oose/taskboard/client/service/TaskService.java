package de.oose.taskboard.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.oose.taskboard.shared.bo.TaskBO;
import de.oose.taskboard.shared.bo.UserBO;

@RemoteServiceRelativePath("GWT.rpc")
public interface TaskService extends RemoteService {
	public List<TaskBO> getTasks(UserBO user, String status, int start, int count);
	public TaskBO addTask(UserBO userBO, TaskBO taskBO);
	public TaskBO updateTask(TaskBO taskBO);
	public void deleteTask(TaskBO taskBO);
	public Integer getTaskCount(UserBO user, String status);
	public List<TaskBO> getAllTasks();
}
