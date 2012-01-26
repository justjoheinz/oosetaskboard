package de.oose.taskboard.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface EditTaskHandler extends EventHandler {
	
	public void onEditTask(EditTaskEvent event);

}
