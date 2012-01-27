package de.oose.taskboard.server;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import com.google.inject.AbstractModule;

public class DozerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Mapper.class).to(DozerBeanMapper.class).asEagerSingleton();
	}

}
