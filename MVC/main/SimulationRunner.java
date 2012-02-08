package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.*;

import model.*;
import view.*;

/**
 * This class makes the mainwindow of the application, it adds buttons to it 
 * and the views that make me whole application.
 * 
 * @author Marco
 * @author Malcolm
 * @author Harold
 * @version 2012.02.08
 */
public class SimulationRunner extends JFrame {
	
	private static final long serialVersionUID = 6038091542956952261L;
	
	// main method
	public static void main(String[] args)
	{
		new SimulationRunner();
	}
	
	// instance variables
	private Simulator brain;
	private AbstractView simulatorview;
	private AbstractController buttonController;
	private AbstractController menuController;
	
	// JLabel version
    private static final JLabel VERSIELABEL = new JLabel("Version 0.89");
	
	public SimulationRunner() {
		
		// new brain
		brain = new Simulator();
		
		// new simulatorview
		simulatorview = new SimulatorView(brain);
		simulatorview.setColor(Rabbit.class, Color.orange);
		simulatorview.setColor(Fox.class, Color.blue);
		simulatorview.setColor(Hunter.class, Color.red);
		simulatorview.setColor(Wolf.class, Color.black);
		simulatorview.setColor(Grass.class, Color.green);
		buttonController = new ButtonController(brain);
		menuController = new MenuController(brain);
		
		// frame settings
		setTitle("Foxes and Rabbits");
		setSize(800, 800);
		setLocation(100, 50);
		
		// container
		JPanel container = (JPanel)getContentPane();
        container.setLayout(new BorderLayout());
        container.setBorder(new EmptyBorder(6,6,6,6));
        
        // add menubar to frame
        setJMenuBar(menuController.getMenu());
        
        // new graphview
        AbstractView graphView = new GraphView(brain);
		graphView.setColor(Rabbit.class, Color.orange);
		graphView.setColor(Fox.class, Color.blue);
		graphView.setColor(Hunter.class, Color.red);
		graphView.setColor(Wolf.class, Color.black);
		
		// new pieview
		AbstractView pieView = new PieView(brain);
		pieView.setColor(Rabbit.class, Color.orange);
		pieView.setColor(Fox.class, Color.blue);
		pieView.setColor(Hunter.class, Color.red);
		pieView.setColor(Wolf.class, Color.black);
		
		// new barview
		AbstractView barView = new BarView(brain);
        
		// JPanel for the views on the left
        JPanel viewsLeft = new JPanel();
        viewsLeft.setBorder(new EmptyBorder(6, 6, 6, 6));
        viewsLeft.setLayout(new BoxLayout(viewsLeft, BoxLayout.PAGE_AXIS));
        
        // add views to viewsleft
        viewsLeft.add(pieView.getField());
        viewsLeft.add(graphView.getField());
        viewsLeft.add(barView.getField());
        
        // left panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(buttonController.getPanel(), BorderLayout.NORTH);
        
        // legenda panel
        JPanel legendaPanel = new JPanel();
        legendaPanel.setLayout(new GridLayout(0, 2));
        
		ImageIcon icon = createImageIcon("/images/bugs_bunny22.gif", "a pretty but meaningless splat");
		JLabel label1 = new JLabel("Image and Text", icon, JLabel.CENTER);
		legendaPanel.add(label1);
        
        leftPanel.add(legendaPanel, BorderLayout.SOUTH);
        
        // add panels to container
        container.add(viewsLeft, BorderLayout.EAST);
        container.add(simulatorview.getField(), BorderLayout.CENTER);
        container.add(leftPanel, BorderLayout.WEST);
        container.add(VERSIELABEL, BorderLayout.SOUTH);
        pack();
        // close application on window close
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        
        // reset simulation for a clean start point
        brain.reset();
	}
	
	/**
	 * Creates an ImageIcon of the given file and description
	 * @param path File location
	 * @param description Description of the file
	 * @return ImageIcon
	 */
	private ImageIcon createImageIcon(String path, String description) {
		// TODO Auto-generated method stub
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
