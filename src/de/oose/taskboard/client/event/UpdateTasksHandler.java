package de.oose.taskboard.client.event;

import com.google.gwt.event.shared.EventHandler;

import de.oose.taskboard.shared.bo.TaskBO;

public interface UpdateTasksHandler extends EventHandler {
	
	public void onUpdateTaskList(UpdateTasksEvent event);

}
