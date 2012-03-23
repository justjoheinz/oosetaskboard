package de.oose.taskboard.client.event;

import com.google.gwt.event.shared.GwtEvent;

import de.oose.taskboard.shared.bo.UserBO;

public class LoginEvent extends GwtEvent<LoginHandler> {
	
	public static Type<LoginHandler> TYPE = new Type<LoginHandler>();
	
	private UserBO account;
	
	public LoginEvent(UserBO account) {
		this.account = account;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<LoginHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(LoginHandler handler) {
		handler.onLogin(this);
	}

	public UserBO getAccount() {
		return account;
	}

}
