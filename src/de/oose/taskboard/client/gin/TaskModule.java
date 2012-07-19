package de.oose.taskboard.client.gin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.View;

import de.oose.taskboard.client.place.LoggedUser;
import de.oose.taskboard.client.presenter.EditTaskPresenter;
import de.oose.taskboard.client.presenter.EditTaskPresenter.IEditTaskView;
import de.oose.taskboard.client.presenter.LoginPresenter;
import de.oose.taskboard.client.presenter.LoginPresenter.ILoginView;
import de.oose.taskboard.client.presenter.Presenter;
import de.oose.taskboard.client.presenter.TaskListPresenter;
import de.oose.taskboard.client.presenter.TaskListPresenter.ITaskListView;
import de.oose.taskboard.client.view.EditTaskView;
import de.oose.taskboard.client.view.LoginView;
import de.oose.taskboard.client.view.TaskListView;
import de.oose.taskboard.shared.bo.UserBO;

public class TaskModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bindPresenter(LoginPresenter.class, ILoginView.class, LoginView.class);
		bindPresenter(TaskListPresenter.class, ITaskListView.class, TaskListView.class);
		bindPresenter(EditTaskPresenter.class, IEditTaskView.class, EditTaskView.class);
		
		bind(UserBO.class).annotatedWith(LoggedUser.class).to(UserBO.class).in(Singleton.class);
	}

	private <P extends Presenter, V extends View> void bindPresenter(
			Class<P> presenterImpl, Class<V> view, Class<? extends V> viewImpl) {
		bind(presenterImpl).in(Singleton.class);
		bind(viewImpl).in(Singleton.class);
		bind(view).to(viewImpl);
	}

}
