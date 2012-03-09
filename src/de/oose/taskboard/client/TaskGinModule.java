package de.oose.taskboard.client;

import javax.inject.Singleton;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Provides;

import de.oose.taskboard.client.presenter.EditTaskPresenter;
import de.oose.taskboard.client.presenter.TaskListPresenter;
import de.oose.taskboard.client.view.EditTaskView;
import de.oose.taskboard.client.view.TaskListView;

public class TaskGinModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(AppController.class);
		bind(EditTaskPresenter.class);
		bind(TaskListPresenter.class);
	}
	
	@Provides @Singleton
	HandlerManager provideHandlerManager() {
		return new HandlerManager(null);
	}

}
