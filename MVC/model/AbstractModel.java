package model;

import java.util.ArrayList;
import java.util.List;


import view.AbstractView;

/**
 * Abstractmodel that can add views to the brain and update
 * all it views.
 * 
 * @author Marco
 * @author Malcolm
 * @author Harold
 * @version 2012.02.06
 */
public abstract class AbstractModel {
	
	// instance variables
	private List<AbstractView> views;
	
	/**
	 * Constructor for a new abstractmodel.
	 * Initiates the views
	 */
	public AbstractModel() {
		views=new ArrayList<AbstractView>();
	}
	
	/**
	 * Add new views to the Arraylist
	 * @param view
	 */
	public void addView(AbstractView view) {
		views.add(view);
	}
	
	/**
	 * Method that calls the methode showStatus for
	 * every view the abstractmodel has.
	 */
	public void statusUpdate() {
		for(AbstractView v: views) v.showStatus();
	}
	
	/**
	 * Method to reset all subclasses.
	 */
	public abstract void reset();
}
