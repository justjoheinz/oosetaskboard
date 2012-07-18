package de.oose.taskboard.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

import de.oose.taskboard.shared.bo.TaskBO;

public class EditTaskEvent extends GwtEvent<EditTaskEvent.EditTaskHandler> {

	public static Type<EditTaskHandler> TYPE = new Type<EditTaskHandler>();
	private TaskBO taskBO;

	public interface EditTaskHandler extends EventHandler {
		void onEditTask(EditTaskEvent event);
	}

	public interface EditTaskHasHandlers extends HasHandlers {
		HandlerRegistration addEditTaskHandler(EditTaskHandler handler);
	}

	public EditTaskEvent(TaskBO taskBO) {
		this.taskBO = taskBO;
	}

	public TaskBO getTaskBO() {
		return taskBO;
	}

	@Override
	protected void dispatch(EditTaskHandler handler) {
		handler.onEditTask(this);
	}

	@Override
	public Type<EditTaskHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<EditTaskHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, TaskBO taskBO) {
		source.fireEvent(new EditTaskEvent(taskBO));
	}
}
