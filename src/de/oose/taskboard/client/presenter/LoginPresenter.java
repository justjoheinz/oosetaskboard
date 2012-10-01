package de.oose.taskboard.client.presenter;

import java.util.Date;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.View;

import de.oose.taskboard.client.event.LoginEvent;
import de.oose.taskboard.client.service.LoginServiceAsync;
import de.oose.taskboard.client.view.HasErrors;
import de.oose.taskboard.shared.bo.UserBO;
import de.oose.taskboard.shared.errors.LoginException;
import de.oose.taskboard.shared.validation.ValidationError;
import de.oose.taskboard.shared.validation.ValidationResult;

public class LoginPresenter implements Presenter, LoginEvent.LoginHandler {

	public interface ILoginView extends View, HasErrors {

		public TextBox getTextBox();

		public Button getBtnOK();

		public SimpleCheckBox getBoxCreate();

		public Button getBtnCancel();

		public void hide();

		public void center();

	}

	private static final String COOKIE = "taskboardUser";
	private ILoginView display;
	private EventBus eventBus;
	private LoginServiceAsync loginService;

	@Override
	public void go(HasWidgets container) {
		container.clear();
		display.center();
	}

	@Inject
	public LoginPresenter(ILoginView display, LoginServiceAsync loginService,
			EventBus eventBus) {
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
				loginService.getAccount(account, create,
						new AsyncCallback<UserBO>() {

							@Override
							public void onFailure(Throwable caught) {
								if (caught instanceof LoginException) {
									LoginException ex = (LoginException) caught;
									ValidationResult<String> result = new ValidationResult<String>();
									result.add(new ValidationError<String>(
											null, null, ex.getMessage()));
									display.displayErrors(result);
								}
							}

							@Override
							public void onSuccess(UserBO result) {
								display.hide();
								Date now = new Date();
								long nowLong = now.getTime()
										+ (1000 * 60 * 60 * 24 * 14); // 14 days
																		// later
								Cookies.setCookie(COOKIE, result.getName(),
										new Date(nowLong));
								onLogin(new LoginEvent(result));
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

	@Override
	public void fireEvent(GwtEvent<?> event) {
		eventBus.fireEvent(event);

	}

	@Override
	public void onLogin(LoginEvent event) {
		fireEvent(event);
	}
}
