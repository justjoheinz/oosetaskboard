package de.oose.taskboard.client.widget;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Label;

import de.oose.taskboard.shared.bo.TaskBO;

public class TaskCellList extends CellList<TaskBO> {

	public TaskCellList() {
		super(new AbstractCell<TaskBO>() {
			@Override
			public void render(Context context, TaskBO value, SafeHtmlBuilder sb) {
				if (value == null)
					return;
				SafeHtml title = SafeHtmlUtils.fromString(value.getTitle());
				SafeHtml desc = SafeHtmlUtils
						.fromString(value.getDescription());
				sb.appendHtmlConstant("<div><p><b>");
				sb.append(title);
				sb.appendHtmlConstant("</b></p><p>");
				sb.append(desc);
				sb.appendHtmlConstant("</p></div>");
			}
		});
		setEmptyListWidget(new Label("nothing"));
	}
}
