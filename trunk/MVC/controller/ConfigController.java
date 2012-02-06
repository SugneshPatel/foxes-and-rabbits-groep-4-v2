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

public class ConfigController extends AbstractController implements ActionListener
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5629702526729726101L;
	private JButton addButton, removeButton;
	
	public ConfigController(Simulator brain){
		super(brain);
	}

	@Override
	public JPanel getButtons() {
		addButton = new JButton("Add");
		addButton.setPreferredSize(new Dimension(90,25));
		addButton.setMinimumSize(new Dimension(90,10));
		addButton.setMaximumSize(new Dimension(90,25));
		addButton.addActionListener(this);
        
        
        removeButton = new JButton("Remove");
        removeButton.setPreferredSize(new Dimension(90,25));
        removeButton.setMinimumSize(new Dimension(90,10));
        removeButton.setMaximumSize(new Dimension(90,25));
        removeButton.addActionListener(this);
        
        
        //nieuw panel waarin buttons komen
        JPanel buttonsLeft = new JPanel();
        buttonsLeft.setBorder(new EmptyBorder(6, 6, 6, 6));
        buttonsLeft.setLayout(new BoxLayout(buttonsLeft, BoxLayout.PAGE_AXIS));
        
        //buttons toevoegen aan buttonsleft panel
        buttonsLeft.add(addButton);
        buttonsLeft.add(removeButton);
        
        return buttonsLeft;
		
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
