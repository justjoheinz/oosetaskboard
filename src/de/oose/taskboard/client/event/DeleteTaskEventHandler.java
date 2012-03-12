package de.oose.taskboard.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface DeleteTaskEventHandler extends EventHandler{
	void onDeleteTask(DeleteTaskEvent event);

}
