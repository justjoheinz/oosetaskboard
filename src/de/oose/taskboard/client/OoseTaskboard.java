package de.oose.taskboard.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

public class OoseTaskboard implements EntryPoint {

	@Override
	public void onModuleLoad() {
		RootPanel.get().setSize("800px", "600px");
		HandlerManager eventBus = new HandlerManager(null);
		AppController appViewer = new AppController(eventBus);
		appViewer.go(RootPanel.get());
		
	}

}
