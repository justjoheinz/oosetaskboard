package de.oose.taskboard.client;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(TaskGinModule.class)
public interface WidgetInjector extends Ginjector {

	AppController getAppController();
}
