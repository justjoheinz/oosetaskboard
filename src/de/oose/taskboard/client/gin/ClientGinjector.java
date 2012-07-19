package de.oose.taskboard.client.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import de.oose.taskboard.client.AppController;
import de.oose.taskboard.client.service.LoginServiceAsync;
import de.oose.taskboard.client.service.TaskServiceAsync;

@GinModules({ DispatchAsyncModule.class, ClientModule.class, TaskModule.class})
public interface ClientGinjector extends Ginjector {

	EventBus getEventBus();

	PlaceManager getPlaceManager();

	AppController getAppController();
	
	TaskServiceAsync getTaskServiceAsync();
	
	LoginServiceAsync getLoginServiceAsync();
	
}
