package de.oose.taskboard.client.view;

import java.util.Arrays;

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

import de.oose.taskboard.shared.bo.TaskBO;
import de.oose.taskboard.shared.validation.ValidationError;
import de.oose.taskboard.shared.validation.ValidationResult;

/**
 * The view to add new task or edit an exisiting one.
 * 
 * @author markusklink
 * 
 */
public class EditTaskView extends VerticalPanel implements HasValue<TaskBO>,
		HasErrors<TaskBO> {
	private static final String ERROR_STYLE = "serverResponseLabelError";
	private static final String DESC_TITLE = "The title of the task";
	private static final String DESC_DESCRIPTION = "A short description of this task";
	private static final String DESC_STATUS = "The current state of this task";
	private TextBox boxTitle;
	private TextArea areaDescription;
	private Button btnConfirmation;
	private Button btnCancel;
	private ValueListBox<String> boxStatus;
	private TaskBO task;
	private Label lblWindowLabel;
	private Label lblDescriptionMsg;
	private Label lblTitleMsg;
	private Button btnDelete;
	private String state;
	private Label lblStatusMsg;

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

		lblTitleMsg = new Label(DESC_TITLE);
		lblTitleMsg.setStyleName("small-font");
		flexTable.setWidget(0, 2, lblTitleMsg);

		Label lblNewLabel_2 = new Label("Description");
		flexTable.setWidget(1, 0, lblNewLabel_2);

		areaDescription = new TextArea();
		flexTable.setWidget(1, 1, areaDescription);

		lblDescriptionMsg = new Label(DESC_DESCRIPTION);
		lblDescriptionMsg.setStyleName("small-font");
		flexTable.setWidget(1, 2, lblDescriptionMsg);

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

		lblStatusMsg = new Label(DESC_STATUS);
		lblStatusMsg.setStyleName("small-font");
		flexTable.setWidget(2, 2, lblStatusMsg);

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setSpacing(5);
		add(horizontalPanel);
		horizontalPanel.setWidth("");

		btnConfirmation = new Button();
		horizontalPanel.add(btnConfirmation);
		btnConfirmation.setText("Add Task");

		btnCancel = new Button();
		horizontalPanel.add(btnCancel);
		btnCancel.setText("Cancel");

		btnDelete = new Button("");
		btnDelete.setText("Delete");
		horizontalPanel.add(btnDelete);

		init();
	}

	public Button getConfirmationButton() {
		return btnConfirmation;
	}

	public Button getCancelButton() {
		return btnCancel;
	}

	public Button getDeleteButton() {
		return btnDelete;
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
	 * initialize the UI elements with the default values or the values of the
	 * active task.
	 */
	private void init() {
		if (task == null) {
			task = new TaskBO();
			areaDescription.setText("");
			boxTitle.setText("");
			boxStatus.setValue("PLANNING");
			btnConfirmation.setText("New Task");
			lblWindowLabel.setText("New Task");
			state = "New";
		} else {
			areaDescription.setText(task.getDescription());
			boxTitle.setText(task.getTitle());
			boxStatus.setValue(task.getStatus());
			btnConfirmation.setText("Confirm");
			lblWindowLabel.setText("Edit Task");
			state = "Edit";
		}
	}

	public TextBox getTitleField() {
		return boxTitle;
	}

	public TextArea getDescriptionField() {
		return areaDescription;
	}

	@Override
	public void displayErrors(ValidationResult<TaskBO> result) {
		lblDescriptionMsg.setText(DESC_DESCRIPTION);
		lblTitleMsg.setText(DESC_TITLE);
		lblStatusMsg.setText(DESC_STATUS);
		lblDescriptionMsg.removeStyleName(ERROR_STYLE);
		lblTitleMsg.removeStyleName(ERROR_STYLE);
		lblStatusMsg.removeStyleName(ERROR_STYLE);

		if (result.isOk())
			return;
		for (ValidationError<TaskBO> v : result) {
			if ("title".equals(v.getField())) {
				lblTitleMsg.setText(v.getMessage());
				lblTitleMsg.setStyleName(ERROR_STYLE, true);
			}
			if ("description".equals(v.getField())) {
				lblDescriptionMsg.setText(v.getMessage());
				lblDescriptionMsg.setStyleName(ERROR_STYLE, true);
			}
			if ("status".equals(v.getField())) {
				lblDescriptionMsg.setText(v.getMessage());
				lblDescriptionMsg.setStyleName(ERROR_STYLE, true);
			}
		}
	}

	public String getState() {
		return state;
	}
}
