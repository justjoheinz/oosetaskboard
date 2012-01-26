package de.oose.taskboard.client.event;

import com.google.gwt.event.shared.GwtEvent;

import de.oose.taskboard.shared.bo.TaskBO;

public class UpdateTasksEvent extends GwtEvent<UpdateTasksHandler> {

	public static final Type<UpdateTasksHandler> TYPE = new Type<UpdateTasksHandler>();

	private TaskBO taskBO;
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<UpdateTasksHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(UpdateTasksHandler handler) {
		handler.onUpdateTaskList(this);	
	}
	
	public UpdateTasksEvent(TaskBO taskBO) {
		this.taskBO = taskBO;
	}

	public TaskBO getTaskBO() {
		return taskBO;
	}

}
