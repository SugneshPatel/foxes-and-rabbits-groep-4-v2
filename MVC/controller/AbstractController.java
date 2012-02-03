package controller;

import javax.swing.*;

import model.Simulator;

public abstract class AbstractController extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6997920280234060101L;
	protected Simulator brain;
	
	public AbstractController(Simulator brain){
		this.brain = brain;
	}
	
	public abstract JPanel getButtons();
	public abstract JMenuBar getMenu();
	
}
