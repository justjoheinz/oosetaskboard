package de.oose.taskboard.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.oose.taskboard.client.event.EditTaskCancelledEvent;
import de.oose.taskboard.client.event.UpdateTasksEvent;
import de.oose.taskboard.client.service.TaskServiceAsync;
import de.oose.taskboard.shared.bo.TaskBO;

public class EditTaskPresenter implements Presenter {
	
	public final Display display;
	private HandlerManager eventBus;
	private TaskServiceAsync taskService;
	
	public interface Display {
		public HasValue<String> getTaskTitle();
		public HasValue<String> getDescription();
		public HasValue<String> getStatus();
		public HasClickHandlers getAddButton();
		public HasClickHandlers getCancelButton();
		public Widget asWidget();
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		display.getTaskTitle().setValue("");
		display.getDescription().setValue("");
		display.getStatus().setValue("");
		container.add(display.asWidget());
	}
	
	public void bind() {
	    display.getAddButton().addClickHandler(new ClickHandler() {
	      public void onClick(ClickEvent event) {
	        saveTask();
	      }
	    });
	    
	    display.getCancelButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EditTaskCancelledEvent());
				
			}
		});
	}
	
	public EditTaskPresenter(Display display, TaskServiceAsync taskService, HandlerManager eventBus) {
		this.display = display;
		this.eventBus = eventBus;
		this.taskService = taskService;
		bind();
	}
	
	public void saveTask() {
		TaskBO taskBO = new TaskBO();
		taskBO.setDescription(display.getDescription().getValue());
		taskBO.setTitle(display.getTaskTitle().getValue());
		taskBO.setStatus(display.getStatus().getValue());
		taskService.addTask(taskBO, new AsyncCallback<TaskBO>() {
			
			@Override
			public void onSuccess(TaskBO result) {
				eventBus.fireEvent(new UpdateTasksEvent(result));
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}

}
