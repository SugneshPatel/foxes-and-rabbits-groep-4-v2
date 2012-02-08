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
 * The abstract controller that serves for all it's subclasses.
 * It initiates a brain that can be used by all the subclasses.
 * 
 * @author Marco
 * @version 2012.02.06
 */
public class ButtonController extends AbstractController implements ActionListener {
	
	private static final long serialVersionUID = -4557571779645953349L;
	
	
	private JButton stepOneButton, stepHundredButton, runButton, stopButton, resetButton, killAllButton;
	
	
	public ButtonController(Simulator brain){
		super(brain);
	}
	
	public JPanel getPanel(){
		stepOneButton = new JButton("Step 1");
        stepOneButton.setPreferredSize(new Dimension(90,25));
        stepOneButton.setMinimumSize(new Dimension(90,10));
        stepOneButton.setMaximumSize(new Dimension(90,25));
        stepOneButton.addActionListener(this);
        
        
        stepHundredButton = new JButton("Step 100");
        stepHundredButton.setPreferredSize(new Dimension(90,25));
        stepHundredButton.setMinimumSize(new Dimension(90,10));
        stepHundredButton.setMaximumSize(new Dimension(90,25));
        stepHundredButton.addActionListener(this);
        
        runButton = new JButton("Run");
        runButton.setPreferredSize(new Dimension(90,25));
        runButton.setMinimumSize(new Dimension(90,10));
        runButton.setMaximumSize(new Dimension(90,25));
        runButton.addActionListener(this);
        
        stopButton = new JButton("Stop");
        stopButton.setPreferredSize(new Dimension(90,25));
        stopButton.setMinimumSize(new Dimension(90,10));
        stopButton.setMaximumSize(new Dimension(90,25));
        stopButton.addActionListener(this);
        
        resetButton = new JButton("Reset");
        resetButton.setPreferredSize(new Dimension(90,25));
        resetButton.setMinimumSize(new Dimension(90,10));
        resetButton.setMaximumSize(new Dimension(90,25));
        resetButton.addActionListener(this);
        
        killAllButton= new JButton("Kill all");
        killAllButton.setPreferredSize(new Dimension(90,25));
        killAllButton.setMinimumSize(new Dimension(90,10));
        killAllButton.setMaximumSize(new Dimension(90,25));
        killAllButton.addActionListener(this);
        
        
        //nieuw panel waarin buttons komen
        JPanel buttonsLeft = new JPanel();
        buttonsLeft.setBorder(new EmptyBorder(6, 6, 6, 6));
        buttonsLeft.setLayout(new BoxLayout(buttonsLeft, BoxLayout.PAGE_AXIS));
        
        //buttons toevoegen aan buttonsleft panel
        buttonsLeft.add(stepOneButton);
        buttonsLeft.add(stepHundredButton);
        buttonsLeft.add(runButton);
        buttonsLeft.add(stopButton);
        buttonsLeft.add(resetButton);
        buttonsLeft.add(killAllButton);
        
        return buttonsLeft;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==stepOneButton) {
			brain.simulate(1);
		}
		if (e.getSource()==stepHundredButton) {
			brain.simulate(100);
		}
		if (e.getSource()==runButton) {
			brain.start();
		}
		if (e.getSource()==stopButton) {
			brain.stop();
		}
		if (e.getSource()==resetButton) {
			brain.reset();
		}
		if (e.getSource()==killAllButton) {
			brain.killAll();
		}
	}

	@Override
	public JMenuBar getMenu() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
