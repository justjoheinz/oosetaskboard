package de.oose.taskboard.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

import de.oose.taskboard.shared.bo.TaskBO;

public class UpdateTaskEvent extends
		GwtEvent<UpdateTaskEvent.UpdateTaskHandler> {

	public static Type<UpdateTaskHandler> TYPE = new Type<UpdateTaskHandler>();
	private TaskBO taskBO;

	public interface UpdateTaskHandler extends EventHandler {
		void onUpdateTask(UpdateTaskEvent event);
	}

	public interface UpdateTaskHasHandlers extends HasHandlers {
		HandlerRegistration addUpdateTaskHandler(UpdateTaskHandler handler);
	}

	public UpdateTaskEvent(TaskBO taskBO) {
		this.taskBO = taskBO;
	}

	public TaskBO getTaskBO() {
		return taskBO;
	}

	@Override
	protected void dispatch(UpdateTaskHandler handler) {
		handler.onUpdateTask(this);
	}

	@Override
	public Type<UpdateTaskHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<UpdateTaskHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, TaskBO taskBO) {
		source.fireEvent(new UpdateTaskEvent(taskBO));
	}
}
