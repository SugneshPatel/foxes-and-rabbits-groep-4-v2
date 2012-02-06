package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import model.Simulator;

public class MenuController extends AbstractController implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4698842680020675242L;
	private JMenuItem quitItem, configItem;
	
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
    	
    	JMenu menu1 = new JMenu("Menu1");
    	JMenu menu2 = new JMenu("Menu2");
    	JMenu helpMenu = new JMenu("Help");
    	
    	//quit item
    	quitItem = new JMenuItem("Afsluiten");
    	quitItem.addActionListener(this);
    	configItem = new JMenuItem("Config");
    	configItem.addActionListener(this);
    	
    	menubar.add(menu1);
    	menubar.add(menu2);
    	menubar.add(helpMenu);
    	
    	//menu items toevoegen aan menu1
    	menu1.add(quitItem);
    	menu2.add(configItem);
    	
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
		
		container.add(configController.getButtons());
		
		config.pack();
		config.setVisible(true);
		
		
		
	}
}
