package de.oose.taskboard.client.presenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

import de.oose.taskboard.client.event.EditTaskEvent;
import de.oose.taskboard.client.service.TaskService;
import de.oose.taskboard.client.service.TaskServiceAsync;
import de.oose.taskboard.client.view.TaskListView;
import de.oose.taskboard.client.widget.TaskCellList;
import de.oose.taskboard.shared.bo.TaskBO;
import com.google.gwt.view.client.Range;

@Singleton
public class TaskListPresenter implements Presenter {

	private final TaskListView display;
	private final HandlerManager eventBus;
	private final TaskServiceAsync taskService;
	private Map<String, TaskListProvider> taskListProviders = new HashMap<String, TaskListProvider>();
	
	
	@Inject
	public TaskListPresenter(TaskListView display, TaskServiceAsync taskServie, HandlerManager eventBus) {
		this.display = display;
		this.eventBus = eventBus;
		this.taskService = taskServie; 
		bind();
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

	public void bind() {
		display.getTaskButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EditTaskEvent(null));	
			}
		});
		
		display.getTaskboard().addSelectionHandler(new SelectionHandler<TaskBO>() {
			
			@Override
			public void onSelection(SelectionEvent<TaskBO> event) {
				eventBus.fireEvent(new EditTaskEvent(event.getSelectedItem()));
			}
		});
		
		 Map<String, TaskCellList> cellListMap = display.getFilteredCellLists();
		 for (Map.Entry<String, TaskCellList> cellListEntry:cellListMap.entrySet()) {
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
			   taskService.getTasks(statusFilter, from, length, new AsyncCallback<List<TaskBO>>() {
					
					@Override
					public void onSuccess(List<TaskBO> result) {
						updateRowData(from, result);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}
				});
			
		}
	}
}
