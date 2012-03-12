package de.oose.taskboard.client.widget;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.oose.taskboard.shared.bo.TaskBO;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class Taskboard extends HorizontalPanel implements HasSelectionHandlers<TaskBO> {

	
	private TaskCellList clPlanning;
	private TaskCellList clWork;
	private TaskCellList clReview;
	private TaskCellList clDone;
	private DecoratorPanel decoratorPanel_2;
	private VerticalPanel vPPlanning;
	private VerticalPanel vPWork;
	private VerticalPanel vPDone;
	private VerticalPanel vpReview;
	private SingleSelectionModel<TaskBO> selectionModel;
	private Map<String,TaskCellList> filteredCellLists = new HashMap<String, TaskCellList>();
	private SimplePager workPager;
	private SimplePager reviewPager;
	private SimplePager donePager;

	public Taskboard() {
		
		setSpacing(5);
		setSize("100%", "100%");
		
		selectionModel = new SingleSelectionModel<TaskBO>();

		vPPlanning = new VerticalPanel();
		add(vPPlanning);
		vPPlanning.setSize("210px", "334px");

		Label lblNewLabel = new Label("Planning");
		lblNewLabel.setStyleName("bigFont");
		vPPlanning.add(lblNewLabel);
		lblNewLabel.setSize("210", "18");

		DecoratorPanel decoratorPanel = new DecoratorPanel();
		vPPlanning.add(decoratorPanel);
		decoratorPanel.setSize("210px", "310px");

		clPlanning = new TaskCellList();
		filteredCellLists.put(TaskBO.PLANNING,clPlanning);
		clPlanning.setSelectionModel(selectionModel);

		decoratorPanel.setWidget(clPlanning);
		clPlanning.setSize("200px", "300px");
		// Create paging controls.
		SimplePager planningPager = new SimplePager();
		clPlanning.setPageSize(5);
		planningPager.setDisplay(clPlanning);
		vPPlanning.add(planningPager);
		vPPlanning.setCellHorizontalAlignment(planningPager, HasHorizontalAlignment.ALIGN_CENTER);


		vPWork = new VerticalPanel();
		add(vPWork);

		Label lblNewLabel_1 = new Label("Work");
		lblNewLabel_1.setStyleName("bigFont");
		vPWork.add(lblNewLabel_1);

		DecoratorPanel decoratorPanel_1 = new DecoratorPanel();
		vPWork.add(decoratorPanel_1);

		clWork = new TaskCellList();
		clWork.setPageSize(5);
		filteredCellLists.put(TaskBO.WORK,clWork);
		clWork.setSelectionModel(selectionModel);
		decoratorPanel_1.setWidget(clWork);
		clWork.setSize("200px", "300px");
		
		workPager = new SimplePager();
		workPager.setDisplay(clWork);
		vPWork.add(workPager);
		vPWork.setCellHorizontalAlignment(workPager, HasHorizontalAlignment.ALIGN_CENTER);

		vpReview = new VerticalPanel();
		add(vpReview);

		Label lblNewLabel_3 = new Label("Review");
		lblNewLabel_3.setStyleName("bigFont");
		vpReview.add(lblNewLabel_3);

		DecoratorPanel decoratorPanel_3 = new DecoratorPanel();
		vpReview.add(decoratorPanel_3);

		clReview = new TaskCellList();
		clReview.setPageSize(5);
		filteredCellLists.put(TaskBO.REVIEW,clReview);
		clReview.setSelectionModel(selectionModel);
		decoratorPanel_3.setWidget(clReview);
		clReview.setSize("200px", "300px");
		
		reviewPager = new SimplePager();
		reviewPager.setDisplay(clReview);
		vpReview.add(reviewPager);
		reviewPager.setDisplay(clReview);
		vpReview.setCellHorizontalAlignment(reviewPager, HasHorizontalAlignment.ALIGN_CENTER);

		vPDone = new VerticalPanel();
		add(vPDone);

		Label lblNewLabel_2 = new Label("Done");
		lblNewLabel_2.setStyleName("bigFont");
		vPDone.add(lblNewLabel_2);

		decoratorPanel_2 = new DecoratorPanel();
		vPDone.add(decoratorPanel_2);

		clDone = new TaskCellList();
		clDone.setPageSize(5);
		filteredCellLists.put(TaskBO.DONE,clDone);
		clDone.setSelectionModel(selectionModel);
		decoratorPanel_2.setWidget(clDone);
		clDone.setSize("200px", "300px");
		
		donePager = new SimplePager();
		donePager.setDisplay(clDone);
		vPDone.add(donePager);
		donePager.setDisplay(clDone);
		vPDone.setCellHorizontalAlignment(donePager, HasHorizontalAlignment.ALIGN_CENTER);
		
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				TaskBO bo = selectionModel.getSelectedObject();
				SelectionEvent.fire(Taskboard.this, bo);
			}
		});

	}

	@Override
	public HandlerRegistration addSelectionHandler(
			SelectionHandler<TaskBO> handler) {
		 return addHandler(handler, SelectionEvent.getType());
	}

	public Map<String, TaskCellList> getFilteredCellLists() {
		return Collections.unmodifiableMap(filteredCellLists);
	}

}
