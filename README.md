Taskboard
=========


Installation
------------


- Clone or fork the github repository, e.g. `git clone git://github.com/justjoheinz/oosetaskboard.git`
- Install the GWT eclipse plugin from `https://developers.google.com/eclipse/docs/download`
- Download the GWT SDK libraries seperately, e.g. `https://developers.google.com/web-toolkit/download`
- Install the HSQLDB plugin for eclipse from https://eclipse-plugin-hsqldb-server.googlecode.com/svn/trunk/updatesite`

In the `build` directory, create a file `local.properties`:

    ivy.home = /Users/foo/Documents/Tools/ivy
    gwt.home = /Users/foo/Projects/gwt-2.5.0/

and point the variables to the directories where you want your project libraries downloaded and where you have installed GWT.

In eclipse, go to the Preferences and add your local GWT installation to the set of GWT installations at
    
    Google > Web Toolkit

Go to the project settings and select your GWT SDK as the preferred SDK:

    Google > Web Toolkit > Use specific SDK

Open `build/ivy-build.xml` and execute the task `download`

As a result many lib files should have been copied to your `war/WEB-INF/lib` directory.
Add the libs from this directory to your build path in case Eclipse is nagging about unsatisfied dependencies.

Configure the HSQLDB plugin according to the setting in the `src/META-INF/persistence.xml` file:

    <persistence-unit name="taskboardHsql"
		transaction-type="RESOURCE_LOCAL">
		<properties>
			<property name="hibernate.archive.autodetection" value="class" />
			<property name="hibernate.format_sql" value="true" />
			<property name="hibernate.show_sql" value="true" />
			<property name="hibernate.hbm2ddl.auto" value="create-drop" />
			<property name="hibernate.dialect" value="org.hibernate.dialect.HSQLDialect" />
			<property name="hibernate.connection.driver_class" value="org.hsqldb.jdbcDriver" />
			<property name="hibernate.connection.username" value="sa" />
			<property name="hibernate.connection.password" value="" />
			<property name="hibernate.connection.url" value="jdbc:hsqldb:hsql://localhost/taskdb" />
		</properties>
	</persistence-unit>



Running and Debugging
---------------------

Getting changes
---------------

