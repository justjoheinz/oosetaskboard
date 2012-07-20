package de.oose.taskboard.client.presenter;

import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.ui.HasWidgets;

public interface Presenter extends HasHandlers {
	public void go(final HasWidgets container);
}
