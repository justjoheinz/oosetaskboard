package de.oose.taskboard.client.presenter;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.oose.taskboard.client.event.EditTaskEvent;
import de.oose.taskboard.client.service.TaskServiceAsync;
import de.oose.taskboard.shared.bo.TaskBO;

public class TaskListPresenter implements Presenter {

	private final Display display;
	private List<TaskBO> tasks;
	private final HandlerManager eventBus;
	private TaskServiceAsync taskService;

	public interface Display {
		public void setTaskList(List<TaskBO> tasks);
		public HasClickHandlers getTaskButton();
		public HasSelectionHandlers<TaskBO> getTaskboard();
		public Widget asWidget();
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		fetchTasks();
	}

	private void fetchTasks() {
		taskService.getTasks(new AsyncCallback<List<TaskBO>>() {
			
			@Override
			public void onSuccess(List<TaskBO> result) {
				display.setTaskList(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}

	public TaskListPresenter(Display display, TaskServiceAsync taskServie, HandlerManager eventBus) {
		this.display = display;
		this.eventBus = eventBus;
		this.taskService = taskServie; 
		this.tasks = null;
		bind();
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
				Window.alert("SELECT!!!");
				eventBus.fireEvent(new EditTaskEvent(event.getSelectedItem()));
			}
		});
	}
	
}
