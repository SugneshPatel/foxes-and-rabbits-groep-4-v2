package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Simulator;

/**
 * This buttoncontroller makes a JPanel with the buttons used
 * on the main window of the application. It has 5 buttons:
 * The first does 1 step of the simulation
 * The second does 100 steps of the simulation
 * The third runs the simulation continues
 * The fourth stops the simulation
 * The fifth resets the simulation
 * 
 * @author Marco
 * @author Malcolm
 * @author Harold
 * @version 2012.02.06
 */
public class ButtonController extends AbstractController implements ActionListener {
	
	private static final long serialVersionUID = -4557571779645953349L;
	
	// instance variables 
	private JButton stepOneButton, stepHundredButton, runButton, stopButton, resetButton;
	
	/**
	 * The constructor for the controller.
	 * @param brain Used for the super constructor
	 */
	public ButtonController(Simulator brain) {
		super(brain);
	}
	
	/**
	 * The implemented method to return a JPanel that can be used
	 * in the layout of the required window.
	 * @return JPanel
	 */
	@Override
	public JPanel getPanel() {
		
		// the button that does 1 step
		stepOneButton = new JButton("Step 1");
        stepOneButton.setPreferredSize(new Dimension(90,25));
        stepOneButton.setMinimumSize(new Dimension(90,10));
        stepOneButton.setMaximumSize(new Dimension(90,25));
        stepOneButton.addActionListener(this);
        
        // the button that does 100 steps
        stepHundredButton = new JButton("Step 100");
        stepHundredButton.setPreferredSize(new Dimension(90,25));
        stepHundredButton.setMinimumSize(new Dimension(90,10));
        stepHundredButton.setMaximumSize(new Dimension(90,25));
        stepHundredButton.addActionListener(this);
        
        // the button that does a continues run
        runButton = new JButton("Run");
        runButton.setPreferredSize(new Dimension(90,25));
        runButton.setMinimumSize(new Dimension(90,10));
        runButton.setMaximumSize(new Dimension(90,25));
        runButton.addActionListener(this);
        
        // the button that stops the continues run
        stopButton = new JButton("Stop");
        stopButton.setPreferredSize(new Dimension(90,25));
        stopButton.setMinimumSize(new Dimension(90,10));
        stopButton.setMaximumSize(new Dimension(90,25));
        stopButton.addActionListener(this);
        
        // the button that resets the simulation
        resetButton = new JButton("Reset");
        resetButton.setPreferredSize(new Dimension(90,25));
        resetButton.setMinimumSize(new Dimension(90,10));
        resetButton.setMaximumSize(new Dimension(90,25));
        resetButton.addActionListener(this);
        
        // a JPanel that contains the buttons
        JPanel buttonsLeft = new JPanel();
        buttonsLeft.setBorder(new EmptyBorder(6, 6, 6, 6));
        buttonsLeft.setLayout(new BoxLayout(buttonsLeft, BoxLayout.PAGE_AXIS));
        
        // a the buttons to the JPanel 
        buttonsLeft.add(stepOneButton);
        buttonsLeft.add(stepHundredButton);
        buttonsLeft.add(runButton);
        buttonsLeft.add(stopButton);
        buttonsLeft.add(resetButton);
        
        // return the whole panel with all the buttons
        return buttonsLeft;
	}
	
	/**
	 * The handler for the action events
	 */
	public void actionPerformed(ActionEvent e) {
		
		// does 1 step
		if (e.getSource()==stepOneButton) {
			brain.simulate(1);
		}
		
		// does 100 steps
		if (e.getSource()==stepHundredButton) {
			brain.simulate(100);
		}
		
		// continues run
		if (e.getSource()==runButton) {
			brain.start();
		}
		
		// stop continues run
		if (e.getSource()==stopButton) {
			brain.stop();
		}
		
		// reset simulation
		if (e.getSource()==resetButton) {
			brain.reset();
		}
	}
	
	/**
	 * Unimplemented method
	 */
	@Override
	public JMenuBar getMenu() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
