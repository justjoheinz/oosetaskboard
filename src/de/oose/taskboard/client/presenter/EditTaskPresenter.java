package de.oose.taskboard.client.presenter;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.View;

import de.oose.taskboard.client.event.DeleteTaskEvent;
import de.oose.taskboard.client.event.EditTaskCancelledEvent;
import de.oose.taskboard.client.event.LoginEvent;
import de.oose.taskboard.client.event.UpdateTaskEvent;
import de.oose.taskboard.client.place.LoggedUser;
import de.oose.taskboard.client.service.TaskServiceAsync;
import de.oose.taskboard.client.util.DefaultAsyncCallback;
import de.oose.taskboard.client.view.HasErrors;
import de.oose.taskboard.client.view.Logoutable;
import de.oose.taskboard.shared.bo.TaskBO;
import de.oose.taskboard.shared.bo.UserBO;
import de.oose.taskboard.shared.enums.TaskState;
import de.oose.taskboard.shared.validation.ValidationResult;

public class EditTaskPresenter implements Presenter {

	public interface IEditTaskView extends View, HasValue<TaskBO>, Logoutable,
			HasErrors {

		public Button getBtnLogout();

		public TaskBO getValue();

		public Button getDeleteButton();

		public Button getCancelButton();

		public Button getConfirmationButton();

		public TaskState getState();

		public TextArea getDescriptionField();

		public TextBox getTitleField();

	}

	private final IEditTaskView display;
	private final EventBus eventBus;
	private final TaskServiceAsync taskService;

	private UserBO userBO;

	@Inject
	public EditTaskPresenter(IEditTaskView display,
			TaskServiceAsync taskService, EventBus eventBus,
			@LoggedUser UserBO user) {

		this.display = display;
		this.eventBus = eventBus;
		this.taskService = taskService;
		setUserBO(user);
		display.getDeleteButton().setEnabled(false);
		bind();
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		setUserBO(userBO);
	}

	private void bind() {
		display.getConfirmationButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (TaskState.NEW.equals(display.getState())) {
					saveTask();
				} else {
					updateTask();
				}
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

		display.getDeleteButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				deleteTask();

			}
		});

		display.getBtnLogout().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LoginEvent(null));

			}
		});
	}

	public void deleteTask() {
		TaskBO taskBO = display.getValue();
		taskService.deleteTask(taskBO, new DefaultAsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				eventBus.fireEvent(new DeleteTaskEvent());
			}
		});
	}

	public void saveTask() {
		TaskBO taskBO = display.getValue();

		taskService.addTask(userBO, taskBO, new DefaultAsyncCallback<TaskBO>() {

			@Override
			public void onSuccess(TaskBO result) {
				eventBus.fireEvent(new UpdateTaskEvent(result));
			}
		});
	}

	public void updateTask() {
		TaskBO taskBO = display.getValue();

		taskService.updateTask(taskBO, new DefaultAsyncCallback<TaskBO>() {
			@Override
			public void onSuccess(TaskBO result) {
				eventBus.fireEvent(new UpdateTaskEvent(result));

			}
		});
	}

	public void setTask(TaskBO task) {
		display.setValue(task);
		if (task == null)
			display.getDeleteButton().setEnabled(false);
		else
			display.getDeleteButton().setEnabled(true);
		validate();
	}

	private void validate() {
		display.getConfirmationButton().setEnabled(false);
		if (display.getValue() != null) {
			ValidationResult<TaskBO> result = display.getValue().validate();
			display.displayErrors(result);
			if (result.isEmpty()) {
				display.getConfirmationButton().setEnabled(true);
			}
		}
	}

	public UserBO getUserBO() {
		return userBO;
	}

	public void setUserBO(UserBO userBO) {
		this.userBO = userBO;
		display.setUser(userBO.getName());
	}

}
