/*
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.oose.taskboard.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.oose.taskboard.client.presenter.TaskListPresenter.Display;
import de.oose.taskboard.client.widget.TaskCellList;
import de.oose.taskboard.shared.bo.TaskBO;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * Sample implementation of {@link TaskView}.
 */
public class TaskListView extends VerticalPanel implements Display {
	private TaskCellList clPlanning;
	private TaskCellList clWork;
	private TaskCellList clReview;
	private TaskCellList clDone;
	private DecoratorPanel decoratorPanel;
	private DecoratorPanel decoratorPanel_1;
	private DecoratorPanel decoratorPanel_2;
	private DecoratorPanel decoratorPanel_3;
	private VerticalPanel vPPlanning;
	private Label lblNewLabel;
	private VerticalPanel vPWork;
	private VerticalPanel vPDone;
	private VerticalPanel vpReview;
	private Label lblNewLabel_1;
	private Label lblNewLabel_2;
	private Label lblNewLabel_3;
	private Button btnTask;

	public TaskListView() {
		setSpacing(5);
		setSize("800", "600");

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setSpacing(5);
		add(horizontalPanel);
		horizontalPanel.setSize("100%", "100%");

		vPPlanning = new VerticalPanel();
		horizontalPanel.add(vPPlanning);

		lblNewLabel = new Label("Planning");
		lblNewLabel.setStyleName("bigFont");
		vPPlanning.add(lblNewLabel);
		lblNewLabel.setSize("210", "18");

		decoratorPanel = new DecoratorPanel();
		vPPlanning.add(decoratorPanel);

		clPlanning = new TaskCellList(TaskBO.PLANNING);
		decoratorPanel.setWidget(clPlanning);
		clPlanning.setSize("200px", "300px");

		vPWork = new VerticalPanel();
		horizontalPanel.add(vPWork);

		lblNewLabel_1 = new Label("Work");
		lblNewLabel_1.setStyleName("bigFont");
		vPWork.add(lblNewLabel_1);

		decoratorPanel_1 = new DecoratorPanel();
		vPWork.add(decoratorPanel_1);

		clWork = new TaskCellList(TaskBO.WORK);
		decoratorPanel_1.setWidget(clWork);
		clWork.setSize("200px", "300px");

		vpReview = new VerticalPanel();
		horizontalPanel.add(vpReview);

		lblNewLabel_3 = new Label("Review");
		lblNewLabel_3.setStyleName("bigFont");
		vpReview.add(lblNewLabel_3);

		decoratorPanel_3 = new DecoratorPanel();
		vpReview.add(decoratorPanel_3);

		clReview = new TaskCellList(TaskBO.REVIEW);
		decoratorPanel_3.setWidget(clReview);
		clReview.setSize("200px", "300px");

		vPDone = new VerticalPanel();
		horizontalPanel.add(vPDone);

		lblNewLabel_2 = new Label("Done");
		lblNewLabel_2.setStyleName("bigFont");
		vPDone.add(lblNewLabel_2);

		decoratorPanel_2 = new DecoratorPanel();
		vPDone.add(decoratorPanel_2);

		clDone = new TaskCellList(TaskBO.DONE);
		decoratorPanel_2.setWidget(clDone);
		clDone.setSize("200px", "300px");

		btnTask = new Button("New button");
		btnTask.setText("New Task");
		add(btnTask);

	}

	@Override
	public void setTaskList(List<TaskBO> tasks) {
		clPlanning.setFilteredRowData(tasks);
		clWork.setFilteredRowData(tasks);
		clReview.setFilteredRowData(tasks);
		clDone.setFilteredRowData(tasks);
	}

	
	@Override
	public HasClickHandlers getTaskButton() {
		return btnTask;
	}

}
