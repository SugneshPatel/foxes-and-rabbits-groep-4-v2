package controller;

import javax.swing.*;
import model.Simulator;

/**
 * The abstract controller that serves for all it's subclasses.
 * It initiates a brain that can be used by all the subclasses.
 * 
 * @author Marco
 * @version 2012.02.06
 */
public abstract class AbstractController extends JPanel {
	
	private static final long serialVersionUID = 6997920280234060101L;
	
	// The brain used for all the controllers
	protected Simulator brain;
	
	/**
	 * The constructor for the abstractcontroller
	 * 
	 * @param brain The brain that is used for all the controllers
	 */
	public AbstractController(Simulator brain){
		this.brain = brain;
	}
	// Method to get the button/slider panels
	public abstract JPanel getButtons();
	// Method to get the Menu
	public abstract JMenuBar getMenu();
}
