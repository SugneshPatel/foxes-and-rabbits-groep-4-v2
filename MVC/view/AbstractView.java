package view;

import java.awt.Color;

import javax.swing.JPanel;


import model.*;

public abstract class AbstractView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -489871581883668120L;
	protected Simulator brain;
	
	public AbstractView(Simulator brain){
		this.brain = brain;
		brain.addView(this);
	}
	
	public abstract void setColor(Class<?> animalClass, Color color);
	
	public abstract void showStatus(int step, Field field);
	
	public abstract boolean isViable(Field field);
	
	public abstract JPanel getField();
}
