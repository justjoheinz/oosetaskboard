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

	private String filter = null;

	public TaskCellList(String filter) {
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
		this.filter = filter;
		setEmptyListWidget(new Label("nothing"));
	}

	private List<TaskBO> filter(List<TaskBO> original) {
		if (original == null)
			return null;
		List<TaskBO> result = new ArrayList<TaskBO>();
		for (TaskBO task : original) {
			if (filter.equalsIgnoreCase(task.getStatus())) {
				result.add(task);
			}
		}
		return result;
	}

	public void setFilteredRowData(List<TaskBO> original) {
		setRowData(filter(original));
	}

}
