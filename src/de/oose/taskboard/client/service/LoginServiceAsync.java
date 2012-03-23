package de.oose.taskboard.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.oose.taskboard.shared.bo.UserBO;

public interface LoginServiceAsync {

	void getAccount(String name, boolean create, AsyncCallback<UserBO> callback);

}
