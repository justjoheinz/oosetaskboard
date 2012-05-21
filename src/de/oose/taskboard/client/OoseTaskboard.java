package de.oose.taskboard.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.oose.taskboard.client.service.TaskService;
import de.oose.taskboard.client.service.TaskServiceAsync;

public class OoseTaskboard implements EntryPoint {
	
	private TaskServiceAsync taskService = GWT.create(TaskService.class);
	private HandlerManager eventBus = new HandlerManager(null);
	

	@Override
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get("container");
		AppController appController = new AppController(taskService, eventBus);
		appController.go(rootPanel);	
	}
}
