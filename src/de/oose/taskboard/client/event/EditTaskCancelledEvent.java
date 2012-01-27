package de.oose.taskboard.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditTaskCancelledEvent extends GwtEvent<EditTaskCancelledEventHandler> {
	public static Type<EditTaskCancelledEventHandler> TYPE = new Type<EditTaskCancelledEventHandler>();

	@Override
	public Type<EditTaskCancelledEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditTaskCancelledEventHandler handler) {
		handler.onEditTaskCancelled(this);
		
	}

}
