package de.oose.taskboard.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.oose.taskboard.shared.bo.TaskBO;
import de.oose.taskboard.shared.bo.UserBO;


public interface TaskServiceAsync {

	void addTask(UserBO userBO, TaskBO taskBO, AsyncCallback<TaskBO> callback);

	void updateTask(TaskBO taskBO, AsyncCallback<TaskBO> callback);

	void deleteTask(TaskBO taskBO, AsyncCallback<Void> callback);

	void getTasks(UserBO user, String status, int start, int count,
			AsyncCallback<List<TaskBO>> callback);

	void getTaskCount(UserBO user, String status, AsyncCallback<Integer> callback);

	void getAllTasks(AsyncCallback<List<TaskBO>> callback);

}
