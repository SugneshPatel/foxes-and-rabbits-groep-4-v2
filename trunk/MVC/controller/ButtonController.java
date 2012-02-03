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

public class ButtonController extends AbstractController implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4557571779645953349L;
	private JButton stepOneButton, stepHundredButton;
	
	
	public ButtonController(Simulator brain){
		super(brain);
	}
	
	public JPanel getButtons(){
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
        
        
        //nieuw panel waarin buttons komen
        JPanel buttonsLeft = new JPanel();
        buttonsLeft.setBorder(new EmptyBorder(6, 6, 6, 6));
        buttonsLeft.setLayout(new BoxLayout(buttonsLeft, BoxLayout.PAGE_AXIS));
        
        //buttons toevoegen aan buttonsleft panel
        buttonsLeft.add(stepOneButton);
        buttonsLeft.add(stepHundredButton);
        
        return buttonsLeft;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==stepOneButton) {
			brain.simulate(1);
		}
		if (e.getSource()==stepHundredButton) {
			brain.simulate(100);
		}
	}

	@Override
	public JMenuBar getMenu() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
