package de.oose.taskboard.server;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.dozer.Mapper;

import com.google.inject.persist.Transactional;

import de.oose.taskboard.client.service.LoginService;
import de.oose.taskboard.server.entity.User;
import de.oose.taskboard.server.service.UserPersistenceService;
import de.oose.taskboard.shared.bo.UserBO;
import de.oose.taskboard.shared.errors.LoginException;

public class LoginServiceImpl implements LoginService {

	@Inject
	private Mapper mapper;

	@Inject
	private UserPersistenceService userService;

	@Override
	@Transactional
	public UserBO getAccount(String name, boolean create) {
		if (StringUtils.isEmpty(name)) {
			throw new LoginException("The account name cannot be empty.");
		}
		User loginAccount = userService.getAccount(name, create);

		UserBO userBO = mapper.map(loginAccount, UserBO.class);
		return userBO;

	}
}
