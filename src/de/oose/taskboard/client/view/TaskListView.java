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

import java.util.List;

import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.oose.taskboard.client.widget.Taskboard;
import de.oose.taskboard.shared.bo.TaskBO;
import de.oose.taskboard.shared.validation.ValidationResult;

/**
 * Sample implementation of {@link TaskView}.
 */
public class TaskListView extends VerticalPanel   {
	
	private Taskboard taskboard;
	
	private Button btnTask;
	private Button btnDelete;

	public TaskListView() {
		setSpacing(5);
		setSize("800", "600");

		taskboard =  new Taskboard();
		add(taskboard);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setSpacing(5);
		add(horizontalPanel);

		btnTask = new Button("New button");
		horizontalPanel.add(btnTask);
		btnTask.setText("New Task");
		
		btnDelete = new Button("New button");
		btnDelete.setText("Delete task");
		horizontalPanel.add(btnDelete);

	}

	
	public void setTaskList(List<TaskBO> tasks) {
		taskboard.setTaskList(tasks);
	}

	
	public Button getTaskButton() {
		return btnTask;
	}
	
	
	public Button getDeleteButton() {
		return btnDelete;
	}

	
	public HasSelectionHandlers<TaskBO> getTaskboard() {
		return taskboard;
	}

}
