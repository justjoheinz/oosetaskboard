package de.oose.taskboard.client.event;

import com.google.gwt.event.shared.GwtEvent;

import de.oose.taskboard.shared.bo.TaskBO;

public class EditTaskEvent extends GwtEvent<EditTaskHandler>{
	public static Type<EditTaskHandler> TYPE = new Type<EditTaskHandler>();
	private final TaskBO taskBO;
	
	public EditTaskEvent(TaskBO taskBO) {
		this.taskBO = taskBO;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<EditTaskHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditTaskHandler handler) {
		handler.onEditTask(this);
	}

	public TaskBO getTaskBO() {
		return taskBO;
	}

}
