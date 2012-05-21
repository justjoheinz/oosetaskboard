package de.oose.taskboard.client.widget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
 
public class LogoutHelper extends HorizontalPanel {
	private final Button btnLogout;
	private final Label lblUser;
	
	public LogoutHelper() {
		btnLogout = new Button("New button");
	
		setSpacing(5);
		setWidth("100%");
		setCellWidth(this, "100%");

		lblUser = new Label("userlabel");
		lblUser.setStylePrimaryName("bold");
		add(lblUser);
		setCellWidth(lblUser, "20%");
		
		btnLogout.setText("Logout");
		add(btnLogout);
		setCellWidth(btnLogout, "100%");
		setCellHorizontalAlignment(btnLogout, HasHorizontalAlignment.ALIGN_RIGHT);
	}
	
	public Button getLogoutButton() { return btnLogout; }
	
	public void setUser(String user) {
		lblUser.setText(user);
	}

}
