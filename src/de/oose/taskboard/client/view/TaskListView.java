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

import java.util.Map;

import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.oose.taskboard.client.widget.TaskCellList;
import de.oose.taskboard.client.widget.Taskboard;
import de.oose.taskboard.shared.bo.TaskBO;
import com.google.gwt.user.client.ui.Label;

/**
 * Sample implementation of {@link TaskView}.
 */
public class TaskListView extends VerticalPanel {

	private Taskboard taskboard;

	private Button btnTask;
	private Label lblUser;

	public String getUser() {
		return lblUser.getText();
	}

	public void setUser(String lblUser) {
		this.lblUser.setText(lblUser);
		
	}

	public TaskListView() {
		setSpacing(5);
		setSize("800", "600");

		lblUser = new Label("New label");
		add(lblUser);

		taskboard = new Taskboard();
		add(taskboard);

		btnTask = new Button("New button");
		add(btnTask);
		btnTask.setText("New Task");

	}

	public Button getTaskButton() {
		return btnTask;
	}

	public HasSelectionHandlers<TaskBO> getTaskboard() {
		return taskboard;
	}

	public Map<String, TaskCellList> getFilteredCellLists() {
		return taskboard.getFilteredCellLists();
	}

}
