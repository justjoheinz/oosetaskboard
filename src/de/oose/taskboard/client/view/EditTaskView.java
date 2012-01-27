package de.oose.taskboard.client.view;

import java.util.Arrays;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.oose.taskboard.client.presenter.EditTaskPresenter.Display;
import de.oose.taskboard.shared.bo.TaskBO;

public class EditTaskView extends VerticalPanel implements Display {
	private TextBox boxTitle;
	private TextArea areaDescription;
	private Button btnAddTask;
	private ValueListBox<String> boxStatus;

	public EditTaskView() {
		setWidth("800");

		Label lblNewLabel = new Label("New Task");
		lblNewLabel.setStyleName("bigFont");
		add(lblNewLabel);

		DecoratorPanel decoratorPanel = new DecoratorPanel();
		add(decoratorPanel);

		FlexTable flexTable = new FlexTable();
		flexTable.setCellSpacing(5);
		flexTable.setCellPadding(5);
		decoratorPanel.setWidget(flexTable);
		flexTable.setWidth("100%");

		Label lblNewLabel_1 = new Label("Title");
		flexTable.setWidget(0, 0, lblNewLabel_1);

		boxTitle = new TextBox();
		flexTable.setWidget(0, 1, boxTitle);
		
		Label lblTheTitleOf = new Label("The title of the task");
		lblTheTitleOf.setStyleName("small-font");
		flexTable.setWidget(0, 2, lblTheTitleOf);

		Label lblNewLabel_2 = new Label("Description");
		flexTable.setWidget(1, 0, lblNewLabel_2);

		areaDescription = new TextArea();
		flexTable.setWidget(1, 1, areaDescription);
		
		Label lblNewLabel_3 = new Label("A short description of this task");
		lblNewLabel_3.setStyleName("small-font");
		flexTable.setWidget(1, 2, lblNewLabel_3);

		Label lblStatus = new Label("Status");
		flexTable.setWidget(2, 0, lblStatus);
		flexTable.getRowFormatter().setVerticalAlign(1,
				HasVerticalAlignment.ALIGN_TOP);

		boxStatus = new ValueListBox<String>(new AbstractRenderer<String>() {

			@Override
			public String render(String object) {
				return object;
			}
		});
		boxStatus.setValue(TaskBO.PLANNING);
		boxStatus.setAcceptableValues(Arrays.asList(new String[] {
				TaskBO.PLANNING, TaskBO.WORK, TaskBO.REVIEW, TaskBO.DONE }));
		
		flexTable.setWidget(2, 1, boxStatus);
		
		Label lblNewLabel_4 = new Label("The current state of this task");
		lblNewLabel_4.setStyleName("small-font");
		flexTable.setWidget(2, 2, lblNewLabel_4);

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setSpacing(5);
		add(horizontalPanel);

		btnAddTask = new Button("New button");
		horizontalPanel.add(btnAddTask);
		btnAddTask.setText("Add Task");

		Button btnNewButton = new Button("New button");
		horizontalPanel.add(btnNewButton);
		btnNewButton.setText("Cancel");
	}

	@Override
	public HasValue<String> getDescription() {
		return areaDescription;
	}

	@Override
	public HasValue<String> getStatus() {
		return boxStatus;
	}

	@Override
	public HasClickHandlers getAddButton() {
		return btnAddTask;

	}

	@Override
	public HasValue<String> getTaskTitle() {
		return boxTitle;
	}

}
