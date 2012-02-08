package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

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
        container.setBorder(new EmptyBorder(10, 10, 10, 10));
        
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
        leftPanel.setBorder(new EmptyBorder(10, 0, 10, 10));
        leftPanel.add(buttonController.getButtons(), BorderLayout.NORTH);
        leftPanel.add(buttonController.getPanel(), BorderLayout.NORTH);
        
        //legenda panel
        // legenda panel
        JPanel legendaPanel = new JPanel();
        
        TitledBorder legendaTitle = BorderFactory.createTitledBorder("Legenda");
		legendaTitle.setTitleJustification(TitledBorder.CENTER);
		legendaPanel.setBorder(legendaTitle);
		
        legendaPanel.setLayout(new GridLayout(0, 1));
        
        // rabbit icon
		ImageIcon rabbitIcon = createImageIcon("/images/leg_rabbit_5x5.gif", "This is the color of a rabbit");
		JLabel legendaRabbitLabel = new JLabel("Rabbit", rabbitIcon, JLabel.LEFT);
		
		// fox icon
		ImageIcon foxIcon = createImageIcon("/images/leg_fox_5x5.gif", "This is the color of a fox");
		JLabel legendaFoxLabel = new JLabel("Fox", foxIcon, JLabel.LEFT);
		
		// wolf icon
		ImageIcon wolfIcon = createImageIcon("/images/leg_wolf_5x5.gif", "This is the color of a wolf");
		JLabel legendaWolfLabel = new JLabel("Wolf", wolfIcon, JLabel.LEFT);
		
		// hunter icon
		ImageIcon hunterIcon = createImageIcon("/images/leg_hunter_5x5.gif", "This is the color of a hunter");
		JLabel legendaHunterLabel = new JLabel("Hunter", hunterIcon, JLabel.LEFT);
		
		// grass icon
		ImageIcon grassIcon = createImageIcon("/images/leg_grass_5x5.gif", "This is the color of some grass");
		JLabel legendaGrassLabel = new JLabel("Grass", grassIcon, JLabel.LEFT);
		
		
		// icons toevoegen aan legenda
		legendaPanel.add(legendaRabbitLabel);
		legendaPanel.add(legendaFoxLabel);
		legendaPanel.add(legendaWolfLabel);
		legendaPanel.add(legendaHunterLabel);
		legendaPanel.add(legendaGrassLabel);
		
		
		// legendaPanel surrounding empty border
		JPanel legendaSurroundingPanel = new JPanel();
		legendaSurroundingPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		legendaSurroundingPanel.setLayout(new BorderLayout());
		
		
		// legenda toevoegen aan surroundingPanel
		legendaSurroundingPanel.add(legendaPanel, BorderLayout.CENTER);
				
        
        leftPanel.add(legendaSurroundingPanel, BorderLayout.SOUTH);
        
        // contents.setLayout(new BorderLayout());
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
