package controller;

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
	private JMenuItem quitItem, configItem, graphItem;
	
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
    	JMenu menu2 = new JMenu("Views");
    	JMenu helpMenu = new JMenu("Help");
    	
    	//quit item
    	quitItem = new JMenuItem("Afsluiten");
    	quitItem.addActionListener(this);
    	configItem = new JMenuItem("Config");
    	configItem.addActionListener(this);
    	graphItem = new JMenuItem("Graph");
    	graphItem.addActionListener(this);
    	
    	menubar.add(menu1);
    	menubar.add(menu2);
    	menubar.add(helpMenu);
    	
    	//menu items toevoegen aan menu1
    	menu1.add(quitItem);
    	menu1.add(configItem);
    	menu2.add(graphItem);
    	
    	return menubar;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==quitItem) {
			quit();
		}
		if (e.getSource()==configItem) {
			config();
		}
		if (e.getSource()==graphItem) {
			graphView();
		}
	}
	
	private void quit(){
		System.exit(0);
	}
	
	private void config(){
		JFrame config = new JFrame("Config");
		
		AbstractController configController = new ConfigController(brain);
		
		JPanel container = (JPanel)config.getContentPane();
		
		container.add(configController.getButtons());
		
		config.pack();
		config.setVisible(true);
	}
	
	private void graphView() {
		JFrame config = new JFrame("GraphView");
		
		AbstractView graphView = new GraphView(brain);
		graphView.setColor(Rabbit.class, Color.orange);
		graphView.setColor(Fox.class, Color.blue);
		graphView.setColor(Hunter.class, Color.red);
		graphView.setColor(Wolf.class, Color.black);
		
		JPanel container = (JPanel)config.getContentPane();
		
		container.add(graphView.getField());
		
		config.pack();
		config.setVisible(true);
	}
}
