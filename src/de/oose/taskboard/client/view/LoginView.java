package de.oose.taskboard.client.view;

import java.io.Serializable;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.TextBox;

import de.oose.taskboard.shared.validation.ValidationError;
import de.oose.taskboard.shared.validation.ValidationResult;

public class LoginView extends DialogBox implements HasErrors {
	private SimpleCheckBox boxCreate;
	private Button btnOK;
	private Button btnCancel;
	private final TextBox textBox = new TextBox();
	private Label lblError;

	public LoginView() {
		setAnimationEnabled(true);
		setText("Taskboard Login");
		setSize("100%", "100%");

		FlexTable flexTable = new FlexTable();
		flexTable.setBorderWidth(0);
		setWidget(flexTable);
		flexTable.setSize("100%", "100%");

		Label lblAccount = new Label("Account:");
		lblAccount.setStyleName("gwt-Label bold");
		flexTable.setWidget(0, 0, lblAccount);
		lblAccount.setWidth("50%");

		flexTable.setWidget(0, 1, textBox);
		textBox.setWidth("100%");

		boxCreate = new SimpleCheckBox();
		flexTable.setWidget(1, 0, boxCreate);
		boxCreate.setWidth("50%");

		Label lblNewLabel = new Label("create new account");
		flexTable.setWidget(1, 1, lblNewLabel);
		lblNewLabel.setWidth("50%");

		lblError = new Label("New label");
		lblError.setStyleName("serverResponseLabelError");
		flexTable.setWidget(2, 0, lblError);
		lblError.setWidth("100%");
		lblError.setVisible(false);
		btnOK = new Button("New button");
		btnOK.setText("OK");
		flexTable.setWidget(3, 0, btnOK);
		btnOK.setWidth("50%");

		btnCancel = new Button("New button");
		btnCancel.setText("Cancel");
		flexTable.setWidget(3, 1, btnCancel);
		flexTable.getFlexCellFormatter().setColSpan(2, 0, 2);
		flexTable.getFlexCellFormatter().setColSpan(1, 0, 2);
	}

	public SimpleCheckBox getBoxCreate() {
		return boxCreate;
	}

	public void setBoxCreate(SimpleCheckBox boxCreate) {
		this.boxCreate = boxCreate;
	}

	public Button getBtnOK() {
		return btnOK;
	}

	public void setBtnOK(Button btnOK) {
		this.btnOK = btnOK;
	}

	public Button getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(Button btnCancel) {
		this.btnCancel = btnCancel;
	}

	public TextBox getTextBox() {
		return textBox;
	}

	@Override
	public void displayErrors(ValidationResult<? extends Serializable> result) {
		if (result.hasErrors()) {
			lblError.setVisible(true);
			for (ValidationError<? extends Serializable> e : result) {
				lblError.setText(e.getMessage());
			}
		}
		else {
			lblError.setVisible(false);
		}

	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			
			@Override
			public void execute() {
				textBox.setFocus(true);
				
			}
		});
	}
	
}
