package de.oose.taskboard.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public class DeleteTaskEvent extends
		GwtEvent<DeleteTaskEvent.DeleteTaskHandler> {

	public static Type<DeleteTaskHandler> TYPE = new Type<DeleteTaskHandler>();

	public interface DeleteTaskHandler extends EventHandler {
		void onDeleteTask(DeleteTaskEvent event);
	}

	public interface DeleteTaskHasHandlers extends HasHandlers {
		HandlerRegistration addDeleteTaskHandler(DeleteTaskHandler handler);
	}

	public DeleteTaskEvent() {
	}

	@Override
	protected void dispatch(DeleteTaskHandler handler) {
		handler.onDeleteTask(this);
	}

	@Override
	public Type<DeleteTaskHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<DeleteTaskHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source) {
		source.fireEvent(new DeleteTaskEvent());
	}
}
