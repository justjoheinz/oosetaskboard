package de.oose.taskboard.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

import de.oose.taskboard.client.presenter.Presenter;

public class AppController implements Presenter, ValueChangeHandler<String>{

	private final HandlerManager eventBus;
	private HasWidgets container;
	
	
	

	public AppController(HandlerManager eventBus) {
		this.eventBus = eventBus;
		bind();
	}

	//f�r die eventbehandlung. F�gt dem Eventbus die Handler hinzu
	private void bind(){
		//AppController f�r events �ber den eventbus registrieren
		History.addValueChangeHandler(this);
	}

	//Methode die aufgerufen wird, wenn im Browser "Back", "Forward" geklickt werden
	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		
		if(token != null){
			Presenter presenter = null;
			
			if(token.equals("taskList")){
				//den Presenter f�r den Initialzustand aufrufen
			}
			
			if(presenter != null){
				presenter.go(container);
			}
		}
		
	}

	//setzen des Initialzustandes f�r die History. Damit wird die Startview gesetzt
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
