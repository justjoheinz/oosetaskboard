package de.oose.taskboard.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface UpdateTasksHandler extends EventHandler {
	
	public void onUpdateTaskList(UpdateTasksEvent event);

}
