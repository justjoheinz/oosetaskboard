package de.oose.taskboard.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class DeleteTaskEvent extends GwtEvent<DeleteTaskEventHandler> {
	public static Type<DeleteTaskEventHandler> TYPE = new Type<DeleteTaskEventHandler>();

	@Override
	public Type<DeleteTaskEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(DeleteTaskEventHandler handler) {
		handler.onDeleteTask(this);
		
	}

}
