package de.oose.taskboard.client.view;

import java.util.Arrays;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
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

/**
 * The view to add new task or edit an exisiting one.
 * @author markusklink
 *
 */
public class EditTaskView extends VerticalPanel implements Display, HasValue<TaskBO> {
	private TextBox boxTitle;
	private TextArea areaDescription;
	private Button btnConfirmation;
	private Button btnCancel;
	private ValueListBox<String> boxStatus;
	private TaskBO task;
	private Label lblWindowLabel;

	public EditTaskView() {
		setWidth("800");
		task = null;

		lblWindowLabel = new Label("New Task");
		lblWindowLabel.setStyleName("bigFont");
		add(lblWindowLabel);

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

		btnConfirmation = new Button();
		horizontalPanel.add(btnConfirmation);
		btnConfirmation.setText("Add Task");

		btnCancel = new Button();
		horizontalPanel.add(btnCancel);
		btnCancel.setText("Cancel");
		
		init();
	}

	@Override
	public HasClickHandlers getConfirmationButton() {
		return btnConfirmation;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		return btnCancel;
	}

	

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<TaskBO> handler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * @return a new task based on the current UI elements.
	 */
	public TaskBO getValue() {
		task.setDescription(areaDescription.getText());
		task.setTitle(boxTitle.getText());
		task.setStatus(boxStatus.getValue());
		return task;
	}

	@Override
	/**
	 * set the active task and init the ui elements.
	 */
	public void setValue(TaskBO value) {
		task = value;
		init();
	}

	@Override
	public void setValue(TaskBO value, boolean fireEvents) {
		setValue(value);
	}
	
	/**
	 * initialize the UI elements with the default values or the values of the active task.
	 */
	private void init() {
		if (task == null) {
			task = new TaskBO();
			areaDescription.setText("");
			boxTitle.setText("");
			boxStatus.setValue("PLANNING");
			btnConfirmation.setText("New Task");
			lblWindowLabel.setText("New Task");
		}
		else {
			areaDescription.setText(task.getDescription());
			boxTitle.setText(task.getTitle());
			boxStatus.setValue(task.getStatus());
			btnConfirmation.setText("Confirm");
			lblWindowLabel.setText("Edit Task");
		}
	}

}
