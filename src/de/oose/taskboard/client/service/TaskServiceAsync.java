package de.oose.taskboard.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.oose.taskboard.shared.bo.TaskBO;

public interface TaskServiceAsync {

	void getTasks(AsyncCallback<List<TaskBO>> callback);

	void addTask(TaskBO taskBO, AsyncCallback<TaskBO> callback);

}
