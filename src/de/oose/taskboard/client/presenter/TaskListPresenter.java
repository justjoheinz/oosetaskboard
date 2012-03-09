package de.oose.taskboard.client.presenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

import de.oose.taskboard.client.event.EditTaskEvent;
import de.oose.taskboard.client.service.TaskServiceAsync;
import de.oose.taskboard.client.util.DefaultAsyncCallback;
import de.oose.taskboard.client.view.TaskListView;
import de.oose.taskboard.shared.bo.TaskBO;

@Singleton
public class TaskListPresenter implements Presenter {

	private final TaskListView display;
	private List<TaskBO> tasks;
	private final HandlerManager eventBus;
	private final TaskServiceAsync taskService;

	
	
	@Inject
	public TaskListPresenter(TaskListView display, TaskServiceAsync taskServie, HandlerManager eventBus) {
		this.display = display;
		this.eventBus = eventBus;
		this.taskService = taskServie; 
		this.tasks = null;
		bind();
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		fetchTasks();
	}

	private void fetchTasks() {
		taskService.getTasks(new DefaultAsyncCallback<List<TaskBO>>() {
			@Override
			public void onSuccess(List<TaskBO> result) {
				display.setTaskList(result);
			}
		});
	}

	public void bind() {
		display.getTaskButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EditTaskEvent(null));	
			}
		});
		
		display.getTaskboard().addSelectionHandler(new SelectionHandler<TaskBO>() {
			
			@Override
			public void onSelection(SelectionEvent<TaskBO> event) {
				eventBus.fireEvent(new EditTaskEvent(event.getSelectedItem()));
			}
		});
	}
	
}
