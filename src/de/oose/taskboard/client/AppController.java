package de.oose.taskboard.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

import de.oose.taskboard.client.event.EditTaskEvent;
import de.oose.taskboard.client.event.EditTaskHandler;
import de.oose.taskboard.client.event.UpdateTasksEvent;
import de.oose.taskboard.client.event.UpdateTasksHandler;
import de.oose.taskboard.client.presenter.EditTaskPresenter;
import de.oose.taskboard.client.presenter.Presenter;
import de.oose.taskboard.client.presenter.TaskListPresenter;
import de.oose.taskboard.client.service.TaskServiceAsync;
import de.oose.taskboard.client.view.EditTaskView;
import de.oose.taskboard.client.view.TaskListView;

public class AppController implements Presenter, ValueChangeHandler<String> {

	private final HandlerManager eventBus;
	private HasWidgets container;

	private TaskServiceAsync taskService;

	public AppController(TaskServiceAsync taskService, HandlerManager eventBus) {
		this.eventBus = eventBus;
		this.taskService = taskService;
		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);

		eventBus.addHandler(EditTaskEvent.TYPE, new EditTaskHandler() {

			@Override
			public void onEditTask(EditTaskEvent event) {
				History.newItem("edit", false);
				Presenter presenter = new EditTaskPresenter(new EditTaskView(),
						taskService, eventBus);
				presenter.go(container);
			}
		});

		eventBus.addHandler(UpdateTasksEvent.TYPE, new UpdateTasksHandler() {

			@Override
			public void onUpdateTaskList(UpdateTasksEvent event) {
				History.newItem("update", false);
				Presenter presenter = new TaskListPresenter(new TaskListView(),
						taskService, eventBus);
				presenter.go(container);
			}
		});
	}

	// Methode die aufgerufen wird, wenn im Browser "Back", "Forward" geklickt
	// werden
	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			Presenter presenter = null;

			if (token.equals("taskList")) {
				presenter = new TaskListPresenter(new TaskListView(),
						taskService, eventBus);
			}

			if (presenter != null) {
				presenter.go(container);
			}
		}

	}

	// setzen des Initialzustandes f�r die History. Damit wird die Startview
	// gesetzt
	// oder, falls ein Historyeintrag gesetzt ist, entsprechend an die richtige
	// View
	// weitergeleitet
	@Override
	public void go(HasWidgets container) {
		this.container = container;

		if ("".equals(History.getToken())) {
			History.newItem("taskList");
		} else {
			History.fireCurrentHistoryState();
		}

	}

}
