package de.oose.taskboard.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtplatform.mvp.client.DelayedBindRegistry;

import de.oose.taskboard.client.gin.ClientGinjector;

public class OoseTaskboard implements EntryPoint {

	
	private final ClientGinjector ginjector = GWT.create(ClientGinjector.class);
	
	private  AppController appController;

	@Override
	public void onModuleLoad() {
		// This is required for Gwt-Platform proxy's generator
		DelayedBindRegistry.bind(ginjector);
		//ginjector.getPlaceManager().revealCurrentPlace();
		
		appController = ginjector.getAppController();
		RootPanel rootPanel = RootPanel.get("container");
		appController.go(rootPanel);
	}
}
