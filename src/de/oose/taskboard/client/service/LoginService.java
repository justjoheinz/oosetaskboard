package de.oose.taskboard.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.oose.taskboard.shared.bo.UserBO;

@RemoteServiceRelativePath("GWT.rpc")
public interface LoginService extends RemoteService {

		public UserBO getAccount(String name, boolean create);
}
