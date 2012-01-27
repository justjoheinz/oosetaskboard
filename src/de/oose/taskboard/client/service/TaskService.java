package de.oose.taskboard.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.oose.taskboard.shared.bo.TaskBO;

@RemoteServiceRelativePath("GWT.rpc")
public interface TaskService extends RemoteService {
	public List<TaskBO> getTasks();
	public TaskBO addTask(TaskBO taskBO);
}
