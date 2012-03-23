package de.oose.taskboard.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface LoginHandler extends EventHandler {
	public void onLogin(LoginEvent event);
}
