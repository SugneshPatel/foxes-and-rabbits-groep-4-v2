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
 * The abstract controller that serves for all it's subclasses.
 * It initiates a brain that can be used by all the subclasses.
 * 
 * @author Marco
 * @version 2012.02.06
 */
public class ButtonController extends AbstractController implements ActionListener {
	
	private static final long serialVersionUID = -4557571779645953349L;
	
	
	private JButton stepOneButton, stepHundredButton, runButton, stopButton, resetButton, killAllButton;
	
	public ButtonController(Simulator brain){
		super(brain);
	}
	
	public JPanel getPanel(){
		stepOneButton = new JButton("Step 1");
        stepOneButton.setPreferredSize(new Dimension(90,25));
        stepOneButton.setMinimumSize(new Dimension(90,10));
        stepOneButton.setMaximumSize(new Dimension(90,25));
        stepOneButton.addActionListener(this);
        
        
        stepHundredButton = new JButton("Step 100");
        stepHundredButton.setPreferredSize(new Dimension(90,25));
        stepHundredButton.setMinimumSize(new Dimension(90,10));
        stepHundredButton.setMaximumSize(new Dimension(90,25));
        stepHundredButton.addActionListener(this);
        
        runButton = new JButton("Run");
        runButton.setPreferredSize(new Dimension(90,25));
        runButton.setMinimumSize(new Dimension(90,10));
        runButton.setMaximumSize(new Dimension(90,25));
        runButton.addActionListener(this);
        
        stopButton = new JButton("Stop");
        stopButton.setPreferredSize(new Dimension(90,25));
        stopButton.setMinimumSize(new Dimension(90,10));
        stopButton.setMaximumSize(new Dimension(90,25));
        stopButton.addActionListener(this);
        
        resetButton = new JButton("Reset");
        resetButton.setPreferredSize(new Dimension(90,25));
        resetButton.setMinimumSize(new Dimension(90,10));
        resetButton.setMaximumSize(new Dimension(90,25));
        resetButton.addActionListener(this);
        
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
        
        return buttonsLeft;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==stepOneButton) {
			brain.simulate(1);
			
			
		}
		if (e.getSource()==stepHundredButton) {
			brain.simulate(100);
			
			
		}
		if (e.getSource()==runButton) {
			brain.start();
			
			
		}
		if (e.getSource()==stopButton) {
			brain.stop();
			
			
		}
		if (e.getSource()==resetButton) {
			brain.reset();
			
			
		}
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
		// TODO Auto-generated method stub
		return null;
	}
	
	public void killAllSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioInputStream audioInput = AudioSystem.getAudioInputStream(ButtonController.class.getResource("/images/explosion-01.wav"));
		buttonClickSound = AudioSystem.getClip();
		buttonClickSound.open(audioInput);
		buttonClickSound.start();
	}
	
	private static Clip buttonClickSound;
	
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
	
	public void makeSkull(){
		
		JFrame skull = new JFrame("Death to all");
		skull.setLocation(400, 200);
		JPanel skullContainer = (JPanel)skull.getContentPane();
		ImageIcon skullIcon = createImageIcon("/images/skull1_100x100.gif", "Death to all");
		JLabel skullLabel = new JLabel(skullIcon);
		skullContainer.add(skullLabel);
		skull.pack();
		skull.setVisible(true);
		
	}
	
	
	
	
}
