package de.oose.taskboard.client.presenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

import de.oose.taskboard.client.event.EditTaskCancelledEvent;
import de.oose.taskboard.client.event.UpdateTasksEvent;
import de.oose.taskboard.client.service.TaskServiceAsync;
import de.oose.taskboard.client.view.EditTaskView;
import de.oose.taskboard.shared.bo.TaskBO;
import de.oose.taskboard.shared.validation.ValidationResult;

@Singleton
public class EditTaskPresenter implements Presenter {

	private final EditTaskView display;
	private final HandlerManager eventBus;
	private final TaskServiceAsync taskService;

	

	@Inject
	public EditTaskPresenter(EditTaskView display, TaskServiceAsync taskService,
			HandlerManager eventBus) {
		this(display, taskService, eventBus, null);
	}
	
	public EditTaskPresenter(EditTaskView display, TaskServiceAsync taskService,
			HandlerManager eventBus, TaskBO taskBO) {
		this.display = display;
		this.eventBus = eventBus;
		this.taskService = taskService;
		setTask(taskBO);
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

		KeyUpHandler keyValidationHandler = new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				validate();
			}
		};
		
		display.getTitleField().addKeyUpHandler(keyValidationHandler);
		display.getDescriptionField().addKeyUpHandler(keyValidationHandler);
	}

	public void saveTask() {
		TaskBO taskBO = display.getValue();

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
		validate();
	}

	private void validate() {
		display.getConfirmationButton().setEnabled(false);
		if (display.getValue() != null) {
			List<ValidationResult<TaskBO>> result = display.getValue()
					.validate();
			if (result.isEmpty()) {
				display.getConfirmationButton().setEnabled(true);
			}
		}
	}
}
