package view;

import java.awt.Color;
import java.awt.Component;


import javax.swing.JPanel;


import model.*;

/**
 * Abstractview is a class that contains the brain that is used in
 * all it sub views. It allows to update views and set color's.
 * 
 * @author Marco
 * @author Malcolm
 * @author Harold
 * @version 2012.02.06
 */
public abstract class AbstractView extends JPanel {
	
	private static final long serialVersionUID = -489871581883668120L;
	// brain used by all views
	protected Simulator brain;
	
	/**
	 * Creates a new AbstractView
	 * @param brain The brain it uses
	 */
	public AbstractView(Simulator brain){
		this.brain = brain;
		brain.addView(this);
	}
	
	/**
	 * Set color for the animals of the view
	 */
	public abstract void setColor(Class<?> animalClass, Color color);
	
	/**
	 * showStatus method for the views
	 */
	public abstract void showStatus();
	
	/**
	 * Get the views
	 * @return JPanel with the view
	 */
	public abstract JPanel getField();

	public Component getFieldView() {
		// TODO Auto-generated method stub
		return null;
	}	
}
