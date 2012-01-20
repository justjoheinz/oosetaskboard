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
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.oose.taskboard.client.presenter.TaskPresenter.Display;
import de.oose.taskboard.shared.bo.TaskBO;

/**
 * Sample implementation of {@link TaskView}.
 */
public class TaskViewImpl extends VerticalPanel implements Display {
	private CellList<TaskBO> clPlanung;
	private CellList<TaskBO> clArbeit;
	private CellList<TaskBO> clAbnahme;
	private CellList<TaskBO> clFertig;

	public TaskViewImpl() {
		setSize("800", "600");

		

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		add(horizontalPanel);
		horizontalPanel.setSize("100%", "100%");

		clPlanung = new CellList<TaskBO>(
				new AbstractCell<TaskBO>() {
					@Override
					public void render(Context context, TaskBO value,
							SafeHtmlBuilder sb) {
						sb.append(SafeHtmlUtils.fromString(value.getTitel()));
					}
				});
		horizontalPanel.add(clPlanung);
		clPlanung.setWidth("25%");

		clArbeit = new CellList<TaskBO>(
				new AbstractCell<TaskBO>() {
					@Override
					public void render(Context context, TaskBO value,
							SafeHtmlBuilder sb) {
						sb.append(SafeHtmlUtils.fromString(value.getTitel()));
					}
				});
		horizontalPanel.add(clArbeit);
		clArbeit.setWidth("25%");

		clAbnahme = new CellList<TaskBO>(
				new AbstractCell<TaskBO>() {
					@Override
					public void render(Context context, TaskBO value,
							SafeHtmlBuilder sb) {
						sb.append(SafeHtmlUtils.fromString(value.getTitel()));
					}
				});
		horizontalPanel.add(clAbnahme);
		clAbnahme.setWidth("25%");

		clFertig = new CellList<TaskBO>(
				new AbstractCell<TaskBO>() {
					@Override
					public void render(Context context, TaskBO value,
							SafeHtmlBuilder sb) {
						sb.append(SafeHtmlUtils.fromString(value.getTitel()));
					}
				});
		horizontalPanel.add(clFertig);
		clFertig.setWidth("25%");
		
		Button btnNewTask = new Button("New button");
		btnNewTask.setText("New Task");
		add(btnNewTask);

	}

	@Override
	public void setTaskList(List<TaskBO> tasks) {
		clPlanung.setRowData(filter(tasks,"planung"));
		clArbeit.setRowData(filter(tasks, "arbeit"));
		clAbnahme.setRowData(filter(tasks, "abnahme"));
		clFertig.setRowData(filter(tasks, "fertig"));
	}
	
	private List<TaskBO> filter(List<TaskBO> original, String status) {
		List<TaskBO> result = new ArrayList<TaskBO>();
		for (TaskBO task : original) {
			if (status.equalsIgnoreCase(task.getStatus())) {
				result.add(task);
			}
		}
		return result;
	}

}
