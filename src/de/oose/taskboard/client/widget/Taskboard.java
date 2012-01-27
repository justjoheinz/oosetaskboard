package de.oose.taskboard.client.widget;

import java.util.List;

import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.oose.taskboard.shared.bo.TaskBO;

public class Taskboard extends HorizontalPanel implements HasSelectionHandlers<TaskBO> {

	
	private TaskCellList clPlanning;
	private TaskCellList clWork;
	private TaskCellList clReview;
	private TaskCellList clDone;
	private DecoratorPanel decoratorPanel;
	private DecoratorPanel decoratorPanel_1;
	private DecoratorPanel decoratorPanel_2;
	private DecoratorPanel decoratorPanel_3;
	private VerticalPanel vPPlanning;
	private Label lblNewLabel;
	private VerticalPanel vPWork;
	private VerticalPanel vPDone;
	private VerticalPanel vpReview;
	private Label lblNewLabel_1;
	private Label lblNewLabel_2;
	private Label lblNewLabel_3;
	private SingleSelectionModel<TaskBO> selectionModel;

	public Taskboard() {
		
		setSpacing(5);
		setSize("100%", "100%");
		
		selectionModel = new SingleSelectionModel<TaskBO>();

		vPPlanning = new VerticalPanel();
		add(vPPlanning);
		vPPlanning.setSize("210px", "334px");

		lblNewLabel = new Label("Planning");
		lblNewLabel.setStyleName("bigFont");
		vPPlanning.add(lblNewLabel);
		lblNewLabel.setSize("210", "18");

		decoratorPanel = new DecoratorPanel();
		vPPlanning.add(decoratorPanel);
		decoratorPanel.setSize("210px", "310px");

		clPlanning = new TaskCellList(TaskBO.PLANNING);
		clPlanning.setSelectionModel(selectionModel);

		decoratorPanel.setWidget(clPlanning);
		clPlanning.setSize("200px", "300px");

		vPWork = new VerticalPanel();
		add(vPWork);

		lblNewLabel_1 = new Label("Work");
		lblNewLabel_1.setStyleName("bigFont");
		vPWork.add(lblNewLabel_1);

		decoratorPanel_1 = new DecoratorPanel();
		vPWork.add(decoratorPanel_1);

		clWork = new TaskCellList(TaskBO.WORK);
		clWork.setSelectionModel(selectionModel);
		decoratorPanel_1.setWidget(clWork);
		clWork.setSize("200px", "300px");

		vpReview = new VerticalPanel();
		add(vpReview);

		lblNewLabel_3 = new Label("Review");
		lblNewLabel_3.setStyleName("bigFont");
		vpReview.add(lblNewLabel_3);

		decoratorPanel_3 = new DecoratorPanel();
		vpReview.add(decoratorPanel_3);

		clReview = new TaskCellList(TaskBO.REVIEW);
		clReview.setSelectionModel(selectionModel);
		decoratorPanel_3.setWidget(clReview);
		clReview.setSize("200px", "300px");

		vPDone = new VerticalPanel();
		add(vPDone);

		lblNewLabel_2 = new Label("Done");
		lblNewLabel_2.setStyleName("bigFont");
		vPDone.add(lblNewLabel_2);

		decoratorPanel_2 = new DecoratorPanel();
		vPDone.add(decoratorPanel_2);

		clDone = new TaskCellList(TaskBO.DONE);
		clDone.setSelectionModel(selectionModel);
		decoratorPanel_2.setWidget(clDone);
		clDone.setSize("200px", "300px");
		
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				TaskBO bo = selectionModel.getSelectedObject();
				Window.alert(bo.toString());
				SelectionEvent.fire(Taskboard.this, bo);
			}
		});

	}

	public void setTaskList(List<TaskBO> tasks) {
		clPlanning.setFilteredRowData(tasks);
		clWork.setFilteredRowData(tasks);
		clDone.setFilteredRowData(tasks);
		clReview.setFilteredRowData(tasks);
	}

	@Override
	public HandlerRegistration addSelectionHandler(
			SelectionHandler<TaskBO> handler) {
		 return addHandler(handler, SelectionEvent.getType());
	}

}
