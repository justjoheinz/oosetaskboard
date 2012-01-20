package de.oose.taskboard.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

import de.oose.taskboard.client.presenter.Presenter;
import de.oose.taskboard.client.presenter.TaskPresenter;
import de.oose.taskboard.client.view.TaskViewImpl;

public class AppController implements Presenter, ValueChangeHandler<String>{

	private final HandlerManager eventBus;
	private HasWidgets container;
	

	public AppController(HandlerManager eventBus) {
		this.eventBus = eventBus;
		bind();
	}

	//für die eventbehandlung. Fügt dem Eventbus die Handler hinzu
	private void bind(){
		//AppController für events über den eventbus registrieren
		History.addValueChangeHandler(this);
	}

	//Methode die aufgerufen wird, wenn im Browser "Back", "Forward" geklickt werden
	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		
		if(token != null){
			Presenter presenter = null;
			
			if(token.equals("taskList")){
				presenter = new TaskPresenter(new TaskViewImpl());
			}
			
			if(presenter != null){
				presenter.go(container);
			}
		}
		
	}

	//setzen des Initialzustandes für die History. Damit wird die Startview gesetzt
	//oder, falls ein Historyeintrag gesetzt ist, entsprechend an die richtige View 
	//weitergeleitet
	@Override
	public void go(HasWidgets container) {
		this.container = container;
		
		if("".equals(History.getToken())){
			History.newItem("taskList");
		}
		else{
			History.fireCurrentHistoryState();
		}
		
	}

}
