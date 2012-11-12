package de.oose.taskboard.server;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import com.google.inject.AbstractModule;

import de.oose.taskboard.server.service.TaskPersistenceService;
import de.oose.taskboard.server.service.TaskPersistenceServiceImpl;
import de.oose.taskboard.server.service.UserPersistenceService;
import de.oose.taskboard.server.service.UserPersistenceServiceImpl;

public class TaskboardModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Mapper.class).to(DozerBeanMapper.class).asEagerSingleton();
		bind(TaskPersistenceService.class).to(TaskPersistenceServiceImpl.class);
		bind(UserPersistenceService.class).to(UserPersistenceServiceImpl.class);
	}

}
