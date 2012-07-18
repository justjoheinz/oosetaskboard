package de.oose.taskboard.client.presenter;


import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

import de.oose.taskboard.client.event.LoginEvent;
import de.oose.taskboard.client.service.LoginServiceAsync;
import de.oose.taskboard.client.view.LoginView;
import de.oose.taskboard.shared.bo.UserBO;
import de.oose.taskboard.shared.errors.LoginException;
import de.oose.taskboard.shared.validation.ValidationError;
import de.oose.taskboard.shared.validation.ValidationResult;

public class LoginPresenter implements Presenter {
	
	private static final String COOKIE = "taskboardUser";
	private LoginView display;
	private HandlerManager eventBus;
	private LoginServiceAsync loginService;

	@Override
	public void go(HasWidgets container) {
		container.clear();
		display.center();
	}
	
	public LoginPresenter(LoginView display, LoginServiceAsync loginService, HandlerManager eventBus) {
		this.display = display;
		this.eventBus = eventBus;
		this.loginService = loginService;
		bind();
		initCookieInformation();
	}
	
	private void initCookieInformation() {
		String userName = Cookies.getCookie(COOKIE);
		if (userName != null) {
			display.getTextBox().setText(userName);
		}
	}

	private void bind() {
		display.getBtnOK().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String account = display.getTextBox().getText();
				boolean create = display.getBoxCreate().getValue();
			    loginService.getAccount(account, create, new AsyncCallback<UserBO>() {
			    	
			    	@Override
			    	public void onFailure(Throwable caught) {
			    		if (caught instanceof LoginException) {
			    			LoginException ex = (LoginException) caught;
			    			ValidationResult<String> result = new ValidationResult<String>();
			    			result.add(new ValidationError<String>(null, null, ex.getMessage()));
			    			display.displayErrors(result);
			    		}
			    	}

					@Override
					public void onSuccess(UserBO result) {
						display.hide();
						Date now = new Date();
						long nowLong = now.getTime() + (1000 * 60 * 60 * 24 * 14); // 14 days later
						Cookies.setCookie(COOKIE, result.getName(),new Date(nowLong));
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
