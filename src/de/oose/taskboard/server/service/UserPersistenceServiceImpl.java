package de.oose.taskboard.server.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;

import com.google.inject.persist.Transactional;

import de.oose.taskboard.server.entity.User;
import de.oose.taskboard.shared.errors.LoginException;

public class UserPersistenceServiceImpl implements UserPersistenceService {
	
	@Inject
	private EntityManager em;

	@Override
	@Transactional
	public User getAccount(String name, boolean create) {
		if (StringUtils.isEmpty(name)) {
			throw new LoginException("The account name cannot be empty.");
		}
		TypedQuery<User> query = em.createQuery("from User u where u.name = '" + name
				+ "'", User.class);
		List<User> userList = (List<User>)query.getResultList();
		if (create) {
			if (userList.isEmpty()) {

				User newAccount = new User();
				newAccount.setName(name);
				em.persist(newAccount);
				
				return newAccount;
			} else {
				throw new LoginException("User already exists.");
//				ValidationResult<String> result = new ValidationResult<String>();
//				result.add(new ValidationError<String>(null, null, "User already exists."));
//				throw new ValidationException(result);
			}
		} else {
			if (userList.isEmpty()) {
				throw new LoginException("User unknown");
			}
			User loginAccount = userList.get(0);
			return loginAccount;
		}
	}

}
