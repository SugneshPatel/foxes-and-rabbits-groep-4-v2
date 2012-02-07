package main;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

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
        // vanaf hier
        AbstractView graphView = new GraphView(brain);
		graphView.setColor(Rabbit.class, Color.orange);
		graphView.setColor(Fox.class, Color.blue);
		graphView.setColor(Hunter.class, Color.red);
		graphView.setColor(Wolf.class, Color.black);
		
		AbstractView pieView = new PieView(brain);
		pieView.setColor(Rabbit.class, Color.orange);
		pieView.setColor(Fox.class, Color.blue);
		pieView.setColor(Hunter.class, Color.red);
		pieView.setColor(Wolf.class, Color.black);
        
        JPanel viewsLeft = new JPanel();
        viewsLeft.setBorder(new EmptyBorder(6, 6, 6, 6));
        viewsLeft.setLayout(new BoxLayout(viewsLeft, BoxLayout.PAGE_AXIS));
        
        viewsLeft.add(pieView.getField());
        viewsLeft.add(graphView.getField());
        
        //tot aan hier
        
        // Panel aan de linkerkant
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        
        // buttons toevoegen aan linker paneel
        leftPanel.add(buttonController.getButtons(), BorderLayout.NORTH);
        
        JPanel legendaPanel = new JPanel();
        legendaPanel.setLayout(new GridLayout(0, 2));
        
        // legenda
        // rabbit icon
        ImageIcon icon = createImageIcon("images2/bugs_bunny22.gif", "een konijntje");
        
        JLabel konijnenLabel = new JLabel("Image and Text", icon, JLabel.CENTER);
        
        legendaPanel.add(konijnenLabel);
        
        leftPanel.add(legendaPanel, BorderLayout.SOUTH);
        
        
        
        // contents.setLayout(new BorderLayout());
        container.add(viewsLeft, BorderLayout.EAST);
        container.add(simulatorview.getField(), BorderLayout.CENTER);
        container.add(leftPanel, BorderLayout.WEST);
        container.add(VERSIELABEL, BorderLayout.SOUTH);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        
        brain.reset();
	}

	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if(imgURL != null) {
			return new ImageIcon(imgURL, description);
		}
		else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
}
