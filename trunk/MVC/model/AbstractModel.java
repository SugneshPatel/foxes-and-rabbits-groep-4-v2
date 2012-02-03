package model;

import java.util.ArrayList;
import java.util.List;

import view.AbstractView;



public abstract class AbstractModel {
	private List<AbstractView> views;
	
	public AbstractModel() {
		views=new ArrayList<AbstractView>();
	}
	
	public void addView(AbstractView view) {
		views.add(view);
	}
	
	public void statusUpdate(int step, Field field) {
		for(AbstractView v: views) v.showStatus(step, field);
	}
	
	public boolean checkViable(Field field){
		
		boolean viable = false;
		for(AbstractView v: views){
			viable = v.isViable(field);
		}
		return viable;
	}
	
	public abstract void reset();
}
