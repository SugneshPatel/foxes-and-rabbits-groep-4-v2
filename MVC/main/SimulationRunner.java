package main;


import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.*;

import model.*;
import view.*;

public class SimulationRunner extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6038091542956952261L;

	public static void main(String[] args)
	{
		new SimulationRunner();
	}
	
	
	private Simulator brain;
	private AbstractView simulatorview;
	private AbstractController buttonController;
	private AbstractController menuController;
	
	// JLabel met versie nummer
    private static final JLabel VERSIELABEL = new JLabel("Version 0.77");
	
	public SimulationRunner() {
		
		brain = new Simulator();
		simulatorview = new SimulatorView(brain);
		simulatorview.setColor(Rabbit.class, Color.orange);
		simulatorview.setColor(Fox.class, Color.blue);
		simulatorview.setColor(Hunter.class, Color.red);
		simulatorview.setColor(Wolf.class, Color.black);
		buttonController = new ButtonController(brain);
		menuController = new MenuController(brain);
		
		
		setTitle("Foxes and Rabbits");
		setSize(800, 800);
		setLocation(100, 50);
		
		JPanel container = (JPanel)getContentPane();
        container.setLayout(new BorderLayout());
        container.setBorder(new EmptyBorder(6,6,6,6));
        
        
        setJMenuBar(menuController.getMenu());
        
        
        
        // contents.setLayout(new BorderLayout());
        container.add(simulatorview.getField(), BorderLayout.CENTER);
        container.add(buttonController.getButtons(), BorderLayout.WEST);
        container.add(VERSIELABEL, BorderLayout.SOUTH);
        pack();
        setVisible(true);
        
        brain.reset();
	}
	
}
