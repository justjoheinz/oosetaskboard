package de.oose.taskboard.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

import de.oose.taskboard.shared.bo.UserBO;

public class LoginEvent extends GwtEvent<LoginEvent.LoginHandler> {

	public static Type<LoginHandler> TYPE = new Type<LoginHandler>();
	private UserBO account;

	public interface LoginHandler extends EventHandler {
		void onLogin(LoginEvent event);
	}

	public interface LoginHasHandlers extends HasHandlers {
		HandlerRegistration addLoginHandler(LoginHandler handler);
	}

	public LoginEvent(UserBO account) {
		this.account = account;
	}

	public UserBO getAccount() {
		return account;
	}

	@Override
	protected void dispatch(LoginHandler handler) {
		handler.onLogin(this);
	}

	@Override
	public Type<LoginHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<LoginHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, UserBO account) {
		source.fireEvent(new LoginEvent(account));
	}
}
