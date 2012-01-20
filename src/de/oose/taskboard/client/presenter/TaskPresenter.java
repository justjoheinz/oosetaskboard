package de.oose.taskboard.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.oose.taskboard.shared.bo.TaskBO;

public class TaskPresenter implements Presenter {

	private final Display display;
	private  List<TaskBO> tasks;

	public interface Display {
		public void setTaskList(List<TaskBO> tasks);

		public Widget asWidget();
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		fetchTasks();
		display.setTaskList(tasks);
	}

	private void fetchTasks() {
		tasks = new ArrayList<TaskBO>();
		tasks.add(new TaskBO(1, "Task1", "Ein Task", "planung"));
		tasks.add(new TaskBO(2, "Task2", "Ein Task", "arbeit"));
		tasks.add(new TaskBO(3, "Task3", "Ein Task", "fertig"));
		tasks.add(new TaskBO(4, "Task4", "Ein Task", "planung"));
		
		
	}

	public TaskPresenter(Display display) {
		this.display = display;
		this.tasks = null;
	}

}
