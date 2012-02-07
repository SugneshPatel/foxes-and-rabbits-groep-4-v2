package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import view.AbstractView;
import view.GraphView;
import view.PieView;

import model.Fox;
import model.Hunter;
import model.Rabbit;
import model.Simulator;
import model.Wolf;

public class MenuController extends AbstractController implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4698842680020675242L;
	private JMenuItem quitItem, configItem, graphItem, pieView;
	
	public MenuController(Simulator brain){
		super(brain);
	}

	@Override
	public JPanel getButtons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JMenuBar getMenu() {
		JMenuBar menubar = new JMenuBar();
    	
    	JMenu menu1 = new JMenu("Opties");
    	JMenu helpMenu = new JMenu("Help");
    	
    	//quit item
    	quitItem = new JMenuItem("Afsluiten");
    	quitItem.addActionListener(this);
    	configItem = new JMenuItem("Config");
    	configItem.addActionListener(this);
 
    	
    	menubar.add(menu1);
    	menubar.add(helpMenu);
    	
    	//menu items toevoegen aan menu1
    	menu1.add(quitItem);
    	menu1.add(configItem);
    
    	return menubar;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==quitItem) {
			quit();
		}
		if (e.getSource()==configItem) {
			config();
		}
	}
	
	private void quit(){
		System.exit(0);
	}
	
	private void config(){
		JFrame config = new JFrame("Config");
		
		AbstractController configController = new ConfigController(brain);
		
		JPanel container = (JPanel)config.getContentPane();
		
		container.setLayout(new BorderLayout());
		container.add(configController.getConfigContent(), BorderLayout.CENTER);
		
		config.pack();
		config.setVisible(true);
	}
}
