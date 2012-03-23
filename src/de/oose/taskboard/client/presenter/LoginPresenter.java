package de.oose.taskboard.client.presenter;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

import de.oose.taskboard.client.event.LoginEvent;
import de.oose.taskboard.client.service.LoginServiceAsync;
import de.oose.taskboard.client.util.DefaultAsyncCallback;
import de.oose.taskboard.client.view.LoginView;
import de.oose.taskboard.shared.bo.UserBO;

public class LoginPresenter implements Presenter {
	
	private LoginView display;
	private HandlerManager eventBus;
	private LoginServiceAsync loginService;

	@Override
	public void go(HasWidgets container) {
		container.clear();
		display.center();
	}
	
	@Inject
	public LoginPresenter(LoginView display, LoginServiceAsync loginService, HandlerManager eventBus) {
		this.display = display;
		this.eventBus = eventBus;
		this.loginService = loginService;
		bind();
	}
	
	private void bind() {
		display.getBtnOK().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String account = display.getTextBox().getText();
				boolean create = display.getBoxCreate().getValue();
			    loginService.getAccount(account, create, new DefaultAsyncCallback<UserBO>() {

					@Override
					public void onSuccess(UserBO result) {
						eventBus.fireEvent(new LoginEvent(result));
					}
				});
			}
		});
		
		display.getBtnCancel().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				display.getTextBox().setText("");
				display.getBoxCreate().setValue(false);
			}
		});
	}


}
