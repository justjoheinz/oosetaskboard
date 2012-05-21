package de.oose.taskboard.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.oose.taskboard.shared.bo.UserBO;
import de.oose.taskboard.shared.errors.LoginException;
import de.oose.taskboard.shared.validation.ValidationException;

@RemoteServiceRelativePath("GWT.rpc")
public interface LoginService extends RemoteService {

		public UserBO getAccount(String name, boolean create) throws LoginException;
}
