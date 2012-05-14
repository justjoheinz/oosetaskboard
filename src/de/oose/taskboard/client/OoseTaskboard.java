package de.oose.taskboard.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

import de.oose.taskboard.client.service.TaskService;
import de.oose.taskboard.client.service.TaskServiceAsync;

public class OoseTaskboard implements EntryPoint {
	
	private TaskServiceAsync taskService = GWT.create(TaskService.class);
	private HandlerManager eventBus = new HandlerManager(null);
	

	@Override
	public void onModuleLoad() {
		RootPanel.get().setSize("800px", "600px");
		AppController appViewer = new AppController(taskService, eventBus);
		appViewer.go(RootPanel.get());	
	}
}
