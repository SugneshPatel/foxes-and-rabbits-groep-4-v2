package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Simulator;

public class ConfigController extends AbstractController implements ActionListener
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5629702526729726101L;
	private JButton foxesButton, rabbitButton, wolfButton, hunterButton;
	
	public ConfigController(Simulator brain){
		super(brain);
	}

	@Override
	public JPanel getButtons() {
		foxesButton = new JButton("Foxes");
		foxesButton.setPreferredSize(new Dimension(90,25));
		foxesButton.setMinimumSize(new Dimension(90,10));
		foxesButton.setMaximumSize(new Dimension(90,25));
		foxesButton.addActionListener(this);
        
        
        rabbitButton = new JButton("Rabbits");
        rabbitButton.setPreferredSize(new Dimension(90,25));
        rabbitButton.setMinimumSize(new Dimension(90,10));
        rabbitButton.setMaximumSize(new Dimension(90,25));
        rabbitButton.addActionListener(this);
        
        wolfButton = new JButton("Wolfs");
        wolfButton.setPreferredSize(new Dimension(90,25));
        wolfButton.setMinimumSize(new Dimension(90,10));
        wolfButton.setMaximumSize(new Dimension(90,25));
        wolfButton.addActionListener(this);
        
        hunterButton = new JButton("Hunters");
        hunterButton.setPreferredSize(new Dimension(90,25));
        hunterButton.setMinimumSize(new Dimension(90,10));
        hunterButton.setMaximumSize(new Dimension(90,25));
        hunterButton.addActionListener(this);
        
        
        //nieuw panel waarin buttons komen
        JPanel buttonsTop = new JPanel();
        buttonsTop.setBorder(new EmptyBorder(6, 6, 6, 6));
        
        //buttons toevoegen aan buttonsleft panel
        buttonsTop.add(foxesButton);
        buttonsTop.add(rabbitButton);
        buttonsTop.add(wolfButton);
        buttonsTop.add(hunterButton);
        
        
        return buttonsTop;
		
	}

	@Override
	public JMenuBar getMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
