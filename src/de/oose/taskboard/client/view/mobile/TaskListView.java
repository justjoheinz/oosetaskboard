package de.oose.taskboard.client.view.mobile;

import java.util.List;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.oose.taskboard.client.presenter.TaskListPresenter.Display;
import de.oose.taskboard.shared.bo.TaskBO;

public class TaskListView extends VerticalPanel implements Display, HasSelectionHandlers<TaskBO> {
	private Button btnAddTask;
	private CellTable<TaskBO> ctTasks;
	private SingleSelectionModel<TaskBO> selectionModel;
	public TaskListView() {
		
		DecoratorPanel decoratorPanel = new DecoratorPanel();
		add(decoratorPanel);
		
		ctTasks = new CellTable<TaskBO>();
		selectionModel = new SingleSelectionModel<TaskBO>();
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				
				SelectionEvent.fire(TaskListView.this, selectionModel.getSelectedObject());
				
			}
		});
		ctTasks.setSelectionModel(selectionModel);
		
		decoratorPanel.setWidget(ctTasks);
		
		TextColumn<TaskBO> colTitle = new TextColumn<TaskBO>() {
			@Override
			public String getValue(TaskBO object) {
				return object.getTitle();
			}
		};
		ctTasks.addColumn(colTitle, "Title");
		
		TextColumn<TaskBO> colDescription = new TextColumn<TaskBO>() {
			@Override
			public String getValue(TaskBO object) {
				return object.getDescription();
			}
		};
		ctTasks.addColumn(colDescription, "Description");
		
		TextColumn<TaskBO> colStatus = new TextColumn<TaskBO>() {
			@Override
			public String getValue(TaskBO object) {
				return object.getStatus();
			}
		};
		ctTasks.addColumn(colStatus, "Status");
		
		btnAddTask = new Button("New button");
		btnAddTask.setText("Add Task");
		add(btnAddTask);
		
	}

	@Override
	public void setTaskList(List<TaskBO> tasks) {
		ctTasks.setRowData(tasks);
	}

	@Override
	public HasClickHandlers getTaskButton() {
		return btnAddTask;
	}

	@Override
	public HasSelectionHandlers<TaskBO> getTaskboard() {
		return this;
	}

	@Override
	public HandlerRegistration addSelectionHandler(
			SelectionHandler<TaskBO> handler) {
		return addHandler(handler, SelectionEvent.getType());
	}

}
