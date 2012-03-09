package de.oose.taskboard.client;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

import de.oose.taskboard.client.event.DeleteTaskEvent;
import de.oose.taskboard.client.event.DeleteTaskEventHandler;
import de.oose.taskboard.client.event.EditTaskCancelledEvent;
import de.oose.taskboard.client.event.EditTaskCancelledEventHandler;
import de.oose.taskboard.client.event.EditTaskEvent;
import de.oose.taskboard.client.event.EditTaskHandler;
import de.oose.taskboard.client.event.UpdateTasksEvent;
import de.oose.taskboard.client.event.UpdateTasksHandler;
import de.oose.taskboard.client.presenter.EditTaskPresenter;
import de.oose.taskboard.client.presenter.Presenter;
import de.oose.taskboard.client.presenter.TaskListPresenter;
import de.oose.taskboard.client.service.TaskServiceAsync;
import de.oose.taskboard.client.view.TaskListView;

/**
 * The main class of the application, responsible for reacting to events and
 * dispatching to the correct presenters, which coordinate the view.
 * 
 * @author markusklink
 * 
 */
@Singleton
public class AppController implements Presenter, ValueChangeHandler<String> {

	private static final String HISTORY_EDIT = "edit";
	private static final String HISTORY_TASKLIST = "taskList";
	private static final String HISTORY_UPDATE = "update";
	@Inject
	private HandlerManager eventBus;
	@Inject
	private TaskServiceAsync taskService;

	private HasWidgets container;

	@Inject
	private EditTaskPresenter editTaskPresenter;
	@Inject
	private TaskListPresenter taskListPresenter;

	@Inject
	public AppController(TaskServiceAsync taskService, HandlerManager eventBus) {
		this.taskService = taskService;
		this.eventBus = eventBus;
		bind();

	}

	private void bind() {
		History.addValueChangeHandler(this);

		eventBus.addHandler(EditTaskEvent.TYPE, new EditTaskHandler() {

			@Override
			public void onEditTask(EditTaskEvent event) {
				History.newItem(HISTORY_EDIT, false); 

				if (event.getTaskBO() != null) {
					editTaskPresenter.setTask(event.getTaskBO());
				} else {
					editTaskPresenter.setTask(null);
				}
				editTaskPresenter.go(container);
			}
		});

		eventBus.addHandler(EditTaskCancelledEvent.TYPE,
				new EditTaskCancelledEventHandler() {

					@Override
					public void onEditTaskCancelled(EditTaskCancelledEvent event) {
						doEditTaskCancelled();
					}
				});

		eventBus.addHandler(UpdateTasksEvent.TYPE, new UpdateTasksHandler() {

			@Override
			public void onUpdateTaskList(UpdateTasksEvent event) {
				History.newItem(HISTORY_UPDATE, false);
				TaskListView taskListView = GWT
						.create(de.oose.taskboard.client.view.TaskListView.class);
				// Presenter presenter = new TaskListPresenter(taskListView,
				// taskService, eventBus);
				taskListPresenter.go(container);
				// presenter.go(container);
			}
		});
		
		eventBus.addHandler(DeleteTaskEvent.TYPE, new DeleteTaskEventHandler() {
			
			@Override
			public void onDeleteTask(DeleteTaskEvent event) {
				History.newItem(HISTORY_TASKLIST);
			}
		});
	}

	private void doEditTaskCancelled() {
		History.newItem(HISTORY_TASKLIST);
	}

	// Methode die aufgerufen wird, wenn im Browser "Back", "Forward" geklickt
	// werden
	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {

			if (token.equals(HISTORY_TASKLIST)) {
				TaskListView taskListView = GWT
						.create(de.oose.taskboard.client.view.TaskListView.class);
				// presenter = new TaskListPresenter(taskListView, taskService,
				// eventBus);
				taskListPresenter.go(container);
			}

			// if (presenter != null) {
			// presenter.go(container);
			// }
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
			History.newItem(HISTORY_TASKLIST);
		} else {
			History.fireCurrentHistoryState();
		}

	}

}
