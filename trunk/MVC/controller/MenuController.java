package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


import model.Simulator;

/**
 * This menucontroller makes a JMenu for the main window of the application
 * It contains the navigation of the application as well as functions
 * to close or open a new window with options/information
 * 
 * @author Marco
 * @author Malcolm
 * @author Harold
 * @version 2012.02.07
 */
public class MenuController extends AbstractController implements ActionListener {
	
	private static final long serialVersionUID = 4698842680020675242L;
	
	// instance variables
	private JMenuItem quitItem, configItem;
	
	/**
	 * The constructor for the controller.
	 * @param brain Used for the super constructor
	 */
	public MenuController(Simulator brain){
		super(brain);
	}
	
	/**
	 * Unused implemented method
	 */
	@Override
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns a JMenuBar that can be used in the main window
	 * @return JMenuBar
	 */
	@Override
	public JMenuBar getMenu() {
		
		// menubar
		JMenuBar menubar = new JMenuBar();
    	
		// menu's
    	JMenu menu1 = new JMenu("Options");
    	JMenu helpMenu = new JMenu("Help");
    	
    	// items
    	configItem = new JMenuItem("Config");
    	configItem.addActionListener(this);
    	quitItem = new JMenuItem("Quit");
    	quitItem.addActionListener(this);
    	
    	// add items to a menu
    	menu1.add(configItem);
    	menu1.add(quitItem);
    	
    	// add menu's to the menubar
    	menubar.add(menu1);
    	menubar.add(helpMenu);
    	
    	// return the complete menubar
    	return menubar;
	}
	/**
	 * Process the actionevents corresponding to there names
	 * @param e An actionevent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// close the application
		if (e.getSource()==quitItem) {
			quit();
		}
		
		// open the config window
		if (e.getSource()==configItem) {
			config();
		}
	}
	
	/**
	 * Method that close the application
	 */
	private void quit(){
		System.exit(0);
	}
	
	/**
	 * Method that makes a new frame and put's the JPanel 
	 * that is made by the config controller in it's contentpane
	 */
	private void config(){
		
		// new frame
		JFrame config = new JFrame("Config");
		
		// new config controller
		AbstractController configController = new ConfigController(brain);
		
		// container
		JPanel container = (JPanel)config.getContentPane();
		container.setLayout(new BorderLayout());
		// add JPanel created in configcontroller
		container.add(configController.getPanel(), BorderLayout.CENTER);
		config.pack();
		config.setVisible(true);
	}


}
