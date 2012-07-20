package de.oose.taskboard.client.presenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.View;

import de.oose.taskboard.client.event.EditTaskEvent;
import de.oose.taskboard.client.event.LogoutEvent;
import de.oose.taskboard.client.place.LoggedUser;
import de.oose.taskboard.client.service.TaskServiceAsync;
import de.oose.taskboard.client.util.DefaultAsyncCallback;
import de.oose.taskboard.client.view.Logoutable;
import de.oose.taskboard.client.widget.TaskCellList;
import de.oose.taskboard.shared.bo.TaskBO;
import de.oose.taskboard.shared.bo.UserBO;

public class TaskListPresenter implements Presenter {

	public interface ITaskListView extends View, Logoutable {

		public Map<String, TaskCellList> getFilteredCellLists();

		public HasSelectionHandlers<TaskBO> getTaskboard();

		public Button getBtnLogout();

		public Button getTaskButton();

		public void setUser(String lblUser);

	}

	private final ITaskListView display;
	private final EventBus eventBus;
	private final TaskServiceAsync taskService;
	private Map<String, TaskListProvider> taskListProviders = new HashMap<String, TaskListProvider>();
	private UserBO user;

	@Inject
	public TaskListPresenter(ITaskListView display,
			TaskServiceAsync taskService, EventBus eventBus,
			@LoggedUser UserBO user) {
		this.display = display;
		this.eventBus = eventBus;
		this.taskService = taskService;
		this.user = user;
		bind();
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		// update the data in the cells
		Map<String, TaskCellList> cellListMap = display.getFilteredCellLists();
		for (Map.Entry<String, TaskListProvider> entry : taskListProviders
				.entrySet()) {
			TaskCellList cellList = cellListMap.get(entry.getKey());
			entry.getValue().onRangeChanged(cellList); // TODO this is very
														// effective but somehow
														// looks quite brute
														// force
		}
		display.setUser(user.getName());
	}

	private void bind() {
		display.getTaskButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				EditTaskEvent.fire(TaskListPresenter.this, null);
			}
		});

		display.getTaskboard().addSelectionHandler(
				new SelectionHandler<TaskBO>() {

					@Override
					public void onSelection(SelectionEvent<TaskBO> event) {
						EditTaskEvent.fire(TaskListPresenter.this, event.getSelectedItem());
					}
				});

		display.getBtnLogout().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				LogoutEvent.fire(TaskListPresenter.this);
			}
		});

		Map<String, TaskCellList> cellListMap = display.getFilteredCellLists();
		for (Map.Entry<String, TaskCellList> cellListEntry : cellListMap
				.entrySet()) {
			String filter = cellListEntry.getKey();
			TaskCellList taskCellList = cellListEntry.getValue();
			TaskListProvider provider = new TaskListProvider(filter);
			taskListProviders.put(filter, provider);
			provider.addDataDisplay(taskCellList);
		}
	}

	private class TaskListProvider extends AsyncDataProvider<TaskBO> {
		private String statusFilter;

		public TaskListProvider(String statusFilter) {
			super();
			this.statusFilter = statusFilter;
		}

		@Override
		protected void onRangeChanged(HasData<TaskBO> display) {
			final Range range = display.getVisibleRange();
			final int from = range.getStart();
			final int length = range.getLength();
			taskService.getTasks(user, statusFilter, from, length,
					new DefaultAsyncCallback<List<TaskBO>>() {

						@Override
						public void onSuccess(List<TaskBO> result) {
							updateRowData(from, result);
						}
					});
			taskService.getTaskCount(user, statusFilter,
					new DefaultAsyncCallback<Integer>() {

						@Override
						public void onSuccess(Integer result) {
							updateRowCount(result, true);
						}
					});
		}
	}

	public void setLoggedUser(UserBO loggedUser) {
		user = loggedUser;
	}


	@Override
	public void fireEvent(GwtEvent<?> event) {
		eventBus.fireEventFromSource(event, this);
	}
}
