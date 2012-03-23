package de.oose.taskboard.server;

import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;

import de.oose.taskboard.client.service.LoginService;
import de.oose.taskboard.client.service.TaskService;

public class ServletModule extends com.google.inject.servlet.ServletModule {
    @Override
    protected void configureServlets() {
        
        install(new JpaPersistModule("taskboardHsql")); 
        filter("/*").through(PersistFilter.class);
        
        serve("/oosetaskboard/GWT.rpc").with(GuiceRemoteServiceServlet.class);

        // cannot use @ImplementedBy
        bind(TaskService.class).to(TaskServiceImpl.class);
        bind(LoginService.class).to(LoginServiceImpl.class);
    }
}