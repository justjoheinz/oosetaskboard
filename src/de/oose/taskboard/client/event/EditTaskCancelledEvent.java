package de.oose.taskboard.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public class EditTaskCancelledEvent extends
		GwtEvent<EditTaskCancelledEvent.EditTaskCancelledHandler> {

	public static Type<EditTaskCancelledHandler> TYPE = new Type<EditTaskCancelledHandler>();

	public interface EditTaskCancelledHandler extends EventHandler {
		void onEditTaskCancelled(EditTaskCancelledEvent event);
	}

	public interface EditTaskCancelledHasHandlers extends HasHandlers {
		HandlerRegistration addEditTaskCancelledHandler(
				EditTaskCancelledHandler handler);
	}

	public EditTaskCancelledEvent() {
	}

	@Override
	protected void dispatch(EditTaskCancelledHandler handler) {
		handler.onEditTaskCancelled(this);
	}

	@Override
	public Type<EditTaskCancelledHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<EditTaskCancelledHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source) {
		source.fireEvent(new EditTaskCancelledEvent());
	}
}
