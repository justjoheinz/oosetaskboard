package de.oose.taskboard.client.presenter;

import javax.inject.Inject;
import javax.inject.Singleton;

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

@Singleton
public class EditTaskPresenter implements Presenter {
	
	private final Display display;
	private final HandlerManager eventBus;
	private final TaskServiceAsync taskService;
	
	public interface Display extends HasValue<TaskBO>{
		public HasClickHandlers getConfirmationButton();
		public HasClickHandlers getCancelButton();
		public Widget asWidget();
	}
	
	@Inject
	public EditTaskPresenter(Display display, TaskServiceAsync taskService, HandlerManager eventBus) {
		this.display = display;
		this.eventBus = eventBus;
		this.taskService = taskService;
		bind();
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}
	
	public void bind() {
	    display.getConfirmationButton().addClickHandler(new ClickHandler() {
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
	
	
	
	public void saveTask() {
		TaskBO taskBO = new TaskBO();
		taskBO = display.getValue();
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
	
	public void setTask(TaskBO task) {
		display.setValue(task);
	}

}
