package de.oose.taskboard.server;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.dozer.Mapper;

import com.google.inject.persist.Transactional;

import de.oose.taskboard.client.service.LoginService;
import de.oose.taskboard.server.entity.User;
import de.oose.taskboard.shared.bo.UserBO;
import de.oose.taskboard.shared.errors.LoginException;

public class LoginServiceImpl implements LoginService {

	@Inject
	private EntityManager em;

	@Inject
	private Mapper mapper;

	@Override
	@Transactional
	public UserBO getAccount(String name, boolean create) {
		if (StringUtils.isEmpty(name)) {
			throw new RuntimeException("The account name cannot be empty.");
		}
		Query query = em.createQuery("from User u where u.name = '" + name
				+ "'", User.class);
		List<User> userList = (List<User>)query.getResultList();
		if (create) {
			if (userList.isEmpty()) {

				User newAccount = new User();
				newAccount.setName(name);
				em.persist(newAccount);
				UserBO userBO = mapper.map(newAccount, UserBO.class);
				return userBO;
			} else {
				throw new LoginException("User already exists.");
			}
		} else {
			if (userList.isEmpty()) {
				throw new LoginException("User unknown");
			}
			User loginAccount = userList.get(0);
			UserBO userBO = mapper.map(loginAccount, UserBO.class);
			return userBO;
		}
	}

}
