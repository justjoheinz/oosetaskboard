package de.oose.taskboard.client;


import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

import de.oose.taskboard.client.event.DeleteTaskEvent;
import de.oose.taskboard.client.event.DeleteTaskEvent.DeleteTaskHandler;
import de.oose.taskboard.client.event.EditTaskCancelledEvent;
import de.oose.taskboard.client.event.EditTaskCancelledEvent.EditTaskCancelledHandler;
import de.oose.taskboard.client.event.EditTaskEvent;
import de.oose.taskboard.client.event.EditTaskEvent.EditTaskHandler;
import de.oose.taskboard.client.event.LoginEvent;
import de.oose.taskboard.client.event.LoginEvent.LoginHandler;
import de.oose.taskboard.client.event.LogoutEvent;
import de.oose.taskboard.client.event.LogoutEvent.LogoutHandler;
import de.oose.taskboard.client.event.UpdateTaskEvent;
import de.oose.taskboard.client.event.UpdateTaskEvent.UpdateTaskHandler;
import de.oose.taskboard.client.place.LoggedUser;
import de.oose.taskboard.client.presenter.EditTaskPresenter;
import de.oose.taskboard.client.presenter.LoginPresenter;
import de.oose.taskboard.client.presenter.Presenter;
import de.oose.taskboard.client.presenter.TaskListPresenter;
import de.oose.taskboard.shared.bo.TaskBO;
import de.oose.taskboard.shared.bo.UserBO;

/**
 * The main class of the application, responsible for reacting to events and
 * dispatching to the correct presenters, which coordinate the view.
 * 
 * @author markusklink
 * 
 */
public class AppController implements Presenter, ValueChangeHandler<String>,
		LogoutHandler, LoginHandler, DeleteTaskHandler, UpdateTaskHandler,
		EditTaskCancelledHandler, EditTaskHandler {

	private static final String HISTORY_EDIT = "edit";
	private static final String HISTORY_TASKLIST = "taskList";
	private static final String HISTORY_LOGIN = "login";

	private final EventBus eventBus;

	private HasWidgets container;

	private final EditTaskPresenter editTaskPresenter;

	private final LoginPresenter loginPresenter;

	private final TaskListPresenter tasklistPresenter;

	private UserBO loggedUser;

	@Inject
	public AppController(LoginPresenter loginPresenter, TaskListPresenter tasklistPresenter,
			EditTaskPresenter editTaskPresenter, @LoggedUser UserBO loggedUser,
			EventBus eventBus) {
		this.eventBus = eventBus;
		this.loginPresenter = loginPresenter;
		this.tasklistPresenter = tasklistPresenter;
		this.editTaskPresenter = editTaskPresenter;
		this.loggedUser = loggedUser;
		
		initHandler();
		History.addValueChangeHandler(this);
	}
	
	private void initHandler() {
		eventBus.addHandler(LogoutEvent.getType(), this);
		eventBus.addHandler(LoginEvent.getType(), this);
		eventBus.addHandler(DeleteTaskEvent.getType(), this);
		eventBus.addHandler(UpdateTaskEvent.getType(), this);
		eventBus.addHandler(EditTaskEvent.getType(), this);
		eventBus.addHandler(EditTaskCancelledEvent.getType(), this);
	}

	// Methode die aufgerufen wird, wenn im Browser "Back", "Forward" geklickt
	// werden
	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		final String token = event.getValue();
		
		if (token != null) {
			if (token.equals(HISTORY_LOGIN) ) {
				loginPresenter.go(container);
				return;
			}
			
			if (token.equals(HISTORY_TASKLIST)) {
     			tasklistPresenter.setLoggedUser(loggedUser);
				tasklistPresenter.go(container);
				return;
			}

			if (token.equals(HISTORY_EDIT)) {
				editTaskPresenter.go(container);
				return;
			}
		}
	}

	// setzen des Initialzustandes für die History. Damit wird die Startview
	// gesetzt
	// oder, falls ein Historyeintrag gesetzt ist, entsprechend an die richtige
	// View
	// weitergeleitet
	@Override
	public void go(HasWidgets container) {
		this.container = container;

		if ("".equals(History.getToken()) || loggedUser == null || loggedUser.getName() == null) {
			History.newItem(HISTORY_LOGIN);
		} else {
			History.fireCurrentHistoryState();
		}

	}

	@Override
	public void fireEvent(GwtEvent<?> event) {
		eventBus.fireEventFromSource(event, this);
	}

	@Override
	public void onLogout(LogoutEvent event) {
		loggedUser = null;
		History.newItem(HISTORY_LOGIN);
	}

	@Override
	public void onLogin(LoginEvent event) {
		loggedUser = event.getAccount();
		History.newItem(HISTORY_TASKLIST);
	}

	@Override
	public void onDeleteTask(DeleteTaskEvent event) {
		History.newItem(HISTORY_TASKLIST);
	}

	@Override
	public void onUpdateTask(UpdateTaskEvent event) {
		History.newItem(HISTORY_TASKLIST);
	}

	@Override
	public void onEditTaskCancelled(EditTaskCancelledEvent event) {
		History.newItem(HISTORY_TASKLIST);
	}

	@Override
	public void onEditTask(EditTaskEvent event) {
		TaskBO task = event.getTaskBO();
		editTaskPresenter.setUserBO(loggedUser);
		editTaskPresenter.setTask(task);
		History.newItem(HISTORY_EDIT);
	}

}
