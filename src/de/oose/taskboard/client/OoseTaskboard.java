package de.oose.taskboard.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

import de.oose.taskboard.client.service.TaskService;
import de.oose.taskboard.client.service.TaskServiceAsync;

public class OoseTaskboard implements EntryPoint {
	
//	private TaskServiceAsync taskService = GWT.create(TaskService.class);
	private final WidgetInjector injector = GWT.create(WidgetInjector.class);

	@Override
	public void onModuleLoad() {
		RootPanel.get().setSize("800px", "600px");
		AppController appViewer = injector.getAppController();
		appViewer.go(RootPanel.get());	
	}
}
