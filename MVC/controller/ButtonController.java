package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Simulator;

/**
 * The controller that contains the button to affect the simulation
 * like run it, do 1 step, reset.
 * 
 * @author Marco
 * @author Malcolm
 * @author Harold
 * @version 2012.02.07
 */
public class ButtonController extends AbstractController implements ActionListener {
	
	private static final long serialVersionUID = -4557571779645953349L;
	// instance variables
	private JButton stepOneButton, stepHundredButton, runButton, stopButton, resetButton, killAllButton;
	
	/**
	 * The constructor for the controller.
	 * @param brain Used for the super constructor
	 */
	public ButtonController(Simulator brain){
		super(brain);
	}
	
	/**
	 * The implemented method to return a JPanel that can be used
	 * in the layout of the required window.
	 * @return JPanel
	 */
	public JPanel getPanel() {
		
		// button to simulate 1 step
		stepOneButton = new JButton("Step 1");
        stepOneButton.setPreferredSize(new Dimension(90,25));
        stepOneButton.setMinimumSize(new Dimension(90,10));
        stepOneButton.setMaximumSize(new Dimension(90,25));
        stepOneButton.addActionListener(this);
        
        // button to simulate 100 steps
        stepHundredButton = new JButton("Step 100");
        stepHundredButton.setPreferredSize(new Dimension(90,25));
        stepHundredButton.setMinimumSize(new Dimension(90,10));
        stepHundredButton.setMaximumSize(new Dimension(90,25));
        stepHundredButton.addActionListener(this);
        
        // button to run simulation continues
        runButton = new JButton("Run");
        runButton.setPreferredSize(new Dimension(90,25));
        runButton.setMinimumSize(new Dimension(90,10));
        runButton.setMaximumSize(new Dimension(90,25));
        runButton.addActionListener(this);
        
        // button to stop simulation
        stopButton = new JButton("Stop");
        stopButton.setPreferredSize(new Dimension(90,25));
        stopButton.setMinimumSize(new Dimension(90,10));
        stopButton.setMaximumSize(new Dimension(90,25));
        stopButton.addActionListener(this);
        
        // button to reset the simulation
        resetButton = new JButton("Reset");
        resetButton.setPreferredSize(new Dimension(90,25));
        resetButton.setMinimumSize(new Dimension(90,10));
        resetButton.setMaximumSize(new Dimension(90,25));
        resetButton.addActionListener(this);
        
        // button to kill everything on the field
        killAllButton= new JButton("Kill all");
        killAllButton.setPreferredSize(new Dimension(90,25));
        killAllButton.setMinimumSize(new Dimension(90,10));
        killAllButton.setMaximumSize(new Dimension(90,25));
        killAllButton.addActionListener(this);
        
        //nieuw panel waarin buttons komen
        JPanel buttonsLeft = new JPanel();
        buttonsLeft.setBorder(new EmptyBorder(6, 6, 6, 6));
        buttonsLeft.setLayout(new BoxLayout(buttonsLeft, BoxLayout.PAGE_AXIS));
        
        //buttons toevoegen aan buttonsleft panel
        buttonsLeft.add(stepOneButton);
        buttonsLeft.add(stepHundredButton);
        buttonsLeft.add(runButton);
        buttonsLeft.add(stopButton);
        buttonsLeft.add(resetButton);
        buttonsLeft.add(killAllButton);
        
        // return the whole panel
        return buttonsLeft;
	}
	
	/**
	 * Process the actionevents corresponding to there names
	 * @param e An actionevent
	 */
	public void actionPerformed(ActionEvent e) {
		// simulate 1 step
		if (e.getSource()==stepOneButton) {
			brain.simulate(1);	
		}
		// simulate 100 steps
		if (e.getSource()==stepHundredButton) {
			brain.simulate(100);	
		}
		// run simulation continuously
		if (e.getSource()==runButton) {
			brain.start();			
		}
		// stop simulation
		if (e.getSource()==stopButton) {
			brain.stop();		
		}
		//reset simulation
		if (e.getSource()==resetButton) {
			brain.reset();		
		}
		// kill everything on field
		if (e.getSource()==killAllButton) {
			brain.killAll();
			makeSkull();
			try {
				killAllSound();
			}
			catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public JMenuBar getMenu() {
		return null;
	}
	
	/**
	 * Generates an explosion sound.
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public void killAllSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioInputStream audioInput = AudioSystem.getAudioInputStream(ButtonController.class.getResource("/images/explosion-01.wav"));
		buttonClickSound = AudioSystem.getClip();
		buttonClickSound.open(audioInput);
		buttonClickSound.start();
	}
	
	private static Clip buttonClickSound;
	
	/**
	 * Creates an image
	 * @param path Path of the file
	 * @param description	It's description
	 * @return
	 */
	private ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if(imgURL != null) {
			return new ImageIcon(imgURL, description);
		}
		else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	/**
	 * Makes the frame for the skull image
	 */
	public void makeSkull(){
		JFrame skull = new JFrame("Death to all");
		skull.setLocation(100, 50);
		JPanel skullContainer = (JPanel)skull.getContentPane();
		ImageIcon skullIcon = createImageIcon("/images/skull1_600x600.gif", "Death to all");
		JLabel skullLabel = new JLabel(skullIcon);
		skullContainer.add(skullLabel);
		skull.pack();
		skull.setVisible(true);	
	}	
}
