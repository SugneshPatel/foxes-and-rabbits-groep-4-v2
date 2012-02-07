package controller;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import model.Config;
import model.Simulator;

public class ConfigController extends AbstractController implements ActionListener
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5629702526729726101L;
	
	public ConfigController(Simulator brain){
		super(brain);
	}

	@Override
	public JPanel getButtons() {
		return null;
		
	}
	
	public JPanel getConfigContent() {
		
		// config panel
		JPanel configPanel = new JPanel();
		configPanel.setBorder(new EmptyBorder(10,10,10,10));
		configPanel.setLayout(new GridLayout(2, 2));
		
		// fox panel
		JPanel foxPanel = new JPanel();
		TitledBorder foxTitle = BorderFactory.createTitledBorder("Foxes config");
		foxTitle.setTitleJustification(TitledBorder.CENTER);
		foxPanel.setBorder(foxTitle);
		foxPanel.setLayout(new BorderLayout());
		
		// rabbit panel
		JPanel rabbitPanel = new JPanel();
		TitledBorder rabbitTitle = BorderFactory.createTitledBorder("Rabbits config");
		rabbitTitle.setTitleJustification(TitledBorder.CENTER);
		rabbitPanel.setBorder(rabbitTitle);
		rabbitPanel.setLayout(new BorderLayout());
		
		// wolf panel
		JPanel wolfPanel = new JPanel();
		TitledBorder wolfTitle = BorderFactory.createTitledBorder("Wolfs config");
		wolfTitle.setTitleJustification(TitledBorder.CENTER);
		wolfPanel.setBorder(wolfTitle);
		wolfPanel.setLayout(new BorderLayout());
		
		// hunter panel
		JPanel hunterPanel = new JPanel();
		TitledBorder hunterTitle = BorderFactory.createTitledBorder("Hunters config");
		hunterTitle.setTitleJustification(TitledBorder.CENTER);
		hunterPanel.setBorder(hunterTitle);
		hunterPanel.setLayout(new BorderLayout());
		
		
		
		// fox config part
		JPanel foxConfigPart = new JPanel();
		foxConfigPart.setBorder(new EmptyBorder(10, 10, 10, 10));
		foxConfigPart.setLayout(new GridLayout(0, 2));
		
		// rabbit config part
		JPanel rabbitConfigPart = new JPanel();
		rabbitConfigPart.setBorder(new EmptyBorder(10, 10, 10, 10));
		rabbitConfigPart.setLayout(new GridLayout(0, 2));
		
		// wolf config part
		JPanel wolfConfigPart = new JPanel();
		wolfConfigPart.setBorder(new EmptyBorder(10, 10, 10, 10));
		wolfConfigPart.setLayout(new GridLayout(0, 2));
		
		// hunter config part
		JPanel hunterConfigPart = new JPanel();
		hunterConfigPart.setBorder(new EmptyBorder(10, 10, 10, 10));
		hunterConfigPart.setLayout(new GridLayout(0, 2));
		
		
		
		// configparts toevoegen aan panels
		foxPanel.add(foxConfigPart, BorderLayout.CENTER);
		rabbitPanel.add(rabbitConfigPart, BorderLayout.CENTER);
		wolfPanel.add(wolfConfigPart, BorderLayout.CENTER);
		hunterPanel.add(hunterConfigPart, BorderLayout.CENTER);
		
		
		
		
		//fox labels
		JLabel foxBreedingLabel = new JLabel("Breeding age:");
		JLabel foxMaxAgeLabel = new JLabel("Max age:");
		JLabel foxBreedingProbabilityLabel = new JLabel("Breeding probability:");
		JLabel foxMaxLitterSizeLabel = new JLabel("Max litter size:");
		JLabel foxRabbitFoodValueLabel = new JLabel("Rabbit food value:");
		
		int tempBreedProbFox = (int) (brain.getConfig().getFoxBreedingProbability() * 100);
		
		// fox sliders
		JSlider foxBreedingSlider = new JSlider(0, brain.getConfig().getFoxMaxAge(), brain.getConfig().getFoxBreedingAge());
		JSlider foxMaxAgeSlider = new JSlider(0, 200, brain.getConfig().getFoxMaxAge());
		JSlider foxBreedingProbabilitySlider = new JSlider(0, 100, tempBreedProbFox);
		JSlider foxRabbitFoodValueSlider = new JSlider(0, 25, brain.getConfig().getFoxRabbitFoodValue());
		JSlider foxMaxLitterSizeSlider = new JSlider(0, 20, brain.getConfig().getFoxMaxLitterSize());
		
		foxBreedingSlider.setMajorTickSpacing(25);
		foxBreedingSlider.setMinorTickSpacing(5);
		foxBreedingSlider.setPaintTicks(true);
		foxBreedingSlider.setPaintLabels(true);
		
		foxMaxAgeSlider.setMajorTickSpacing(50);
		foxMaxAgeSlider.setMinorTickSpacing(10);
		foxMaxAgeSlider.setPaintTicks(true);
		foxMaxAgeSlider.setPaintLabels(true);
		
		foxBreedingProbabilitySlider.setMajorTickSpacing(10);
		foxBreedingProbabilitySlider.setMinorTickSpacing(2);
		foxBreedingProbabilitySlider.setPaintTicks(true);
		foxBreedingProbabilitySlider.setPaintLabels(true);
		
		foxRabbitFoodValueSlider.setMajorTickSpacing(5);
		foxRabbitFoodValueSlider.setMinorTickSpacing(1);
		foxRabbitFoodValueSlider.setPaintTicks(true);
		foxRabbitFoodValueSlider.setPaintLabels(true);
		
		foxMaxLitterSizeSlider.setMajorTickSpacing(5);
		foxMaxLitterSizeSlider.setMinorTickSpacing(1);
		foxMaxLitterSizeSlider.setPaintTicks(true);
		foxMaxLitterSizeSlider.setPaintLabels(true);
		
		foxConfigPart.add(foxBreedingLabel);
		foxConfigPart.add(foxBreedingSlider);
		
		foxConfigPart.add(foxMaxAgeLabel);
		foxConfigPart.add(foxMaxAgeSlider);
		
		foxConfigPart.add(foxBreedingProbabilityLabel);
		foxConfigPart.add(foxBreedingProbabilitySlider);
		
		foxConfigPart.add(foxMaxLitterSizeLabel);
		foxConfigPart.add(foxMaxLitterSizeSlider);
		
		foxConfigPart.add(foxRabbitFoodValueLabel);
		foxConfigPart.add(foxRabbitFoodValueSlider);
		
		
		
		
		//rabbit labels
		JLabel rabbitBreedingLabel = new JLabel("Breeding age:");
		JLabel rabbitMaxAgeLabel = new JLabel("Max age:");
		JLabel rabbitBreedingProbabilityLabel = new JLabel("Breeding probability:");
		JLabel rabbitMaxLitterSizeLabel = new JLabel("Max litter size:");
		
		int tempBreedProbRabbit = (int) (brain.getConfig().getRabbitBreedingProbability() * 100);
		
		// rabbit sliders
		JSlider rabbitBreedingSlider = new JSlider(0, brain.getConfig().getRabbitMaxAge(), brain.getConfig().getRabbitBreedingAge());
		JSlider rabbitMaxAgeSlider = new JSlider(0, 200, brain.getConfig().getRabbitMaxAge());
		JSlider rabbitBreedingProbabilitySlider = new JSlider(0, 100, tempBreedProbRabbit);
		JSlider rabbitMaxLitterSizeSlider = new JSlider(0, 20, brain.getConfig().getRabbitMaxLitterSize());
		
		rabbitBreedingSlider.setMajorTickSpacing(25);
		rabbitBreedingSlider.setMinorTickSpacing(5);
		rabbitBreedingSlider.setPaintTicks(true);
		rabbitBreedingSlider.setPaintLabels(true);
		
		rabbitMaxAgeSlider.setMajorTickSpacing(50);
		rabbitMaxAgeSlider.setMinorTickSpacing(10);
		rabbitMaxAgeSlider.setPaintTicks(true);
		rabbitMaxAgeSlider.setPaintLabels(true);
		
		rabbitBreedingProbabilitySlider.setMajorTickSpacing(10);
		rabbitBreedingProbabilitySlider.setMinorTickSpacing(2);
		rabbitBreedingProbabilitySlider.setPaintTicks(true);
		rabbitBreedingProbabilitySlider.setPaintLabels(true);
		
		rabbitMaxLitterSizeSlider.setMajorTickSpacing(5);
		rabbitMaxLitterSizeSlider.setMinorTickSpacing(1);
		rabbitMaxLitterSizeSlider.setPaintTicks(true);
		rabbitMaxLitterSizeSlider.setPaintLabels(true);
		
		rabbitConfigPart.add(rabbitBreedingLabel);
		rabbitConfigPart.add(rabbitBreedingSlider);
		
		rabbitConfigPart.add(rabbitMaxAgeLabel);
		rabbitConfigPart.add(rabbitMaxAgeSlider);
		
		rabbitConfigPart.add(rabbitBreedingProbabilityLabel);
		rabbitConfigPart.add(rabbitBreedingProbabilitySlider);
		
		rabbitConfigPart.add(rabbitMaxLitterSizeLabel);
		rabbitConfigPart.add(rabbitMaxLitterSizeSlider);
				
		
		
		//wolf labels
		JLabel wolfBreedingLabel = new JLabel("Breeding age:");
		JLabel wolfMaxAgeLabel = new JLabel("Max age:");
		JLabel wolfBreedingProbabilityLabel = new JLabel("Breeding probability:");
		JLabel wolfMaxLitterSizeLabel = new JLabel("Max litter size:");
		JLabel wolfRabbitFoodValueLabel = new JLabel("Rabbit food value:");
		JLabel wolfFoxFoodValueLabel = new JLabel("Fox food value:");
		
		int tempBreedProbWolf = (int) (brain.getConfig().getWolfBreedingProbability() * 100);
		
		// rabbit sliders
		JSlider wolfBreedingSlider = new JSlider(0, brain.getConfig().getWolfMaxAge(), brain.getConfig().getWolfBreedingAge());
		JSlider wolfMaxAgeSlider = new JSlider(0, 200, brain.getConfig().getWolfMaxAge());
		JSlider wolfBreedingProbabilitySlider = new JSlider(0, 100, tempBreedProbWolf);
		JSlider wolfMaxLitterSizeSlider = new JSlider(0, 20, brain.getConfig().getWolfMaxLitterSize());
		JSlider wolfRabbitFoodValueSlider = new JSlider(0, 25, brain.getConfig().getWolfRabbitFoodValue());
		JSlider wolfFoxFoodValueSlider = new JSlider(0, 25, brain.getConfig().getWolfFoxFoodValue());
		
		wolfBreedingSlider.setMajorTickSpacing(25);
		wolfBreedingSlider.setMinorTickSpacing(5);
		wolfBreedingSlider.setPaintTicks(true);
		wolfBreedingSlider.setPaintLabels(true);
		
		wolfMaxAgeSlider.setMajorTickSpacing(50);
		wolfMaxAgeSlider.setMinorTickSpacing(10);
		wolfMaxAgeSlider.setPaintTicks(true);
		wolfMaxAgeSlider.setPaintLabels(true);
		
		wolfBreedingProbabilitySlider.setMajorTickSpacing(10);
		wolfBreedingProbabilitySlider.setMinorTickSpacing(2);
		wolfBreedingProbabilitySlider.setPaintTicks(true);
		wolfBreedingProbabilitySlider.setPaintLabels(true);
		
		wolfMaxLitterSizeSlider.setMajorTickSpacing(5);
		wolfMaxLitterSizeSlider.setMinorTickSpacing(1);
		wolfMaxLitterSizeSlider.setPaintTicks(true);
		wolfMaxLitterSizeSlider.setPaintLabels(true);
		
		wolfRabbitFoodValueSlider.setMajorTickSpacing(5);
		wolfRabbitFoodValueSlider.setMinorTickSpacing(1);
		wolfRabbitFoodValueSlider.setPaintTicks(true);
		wolfRabbitFoodValueSlider.setPaintLabels(true);
		
		wolfFoxFoodValueSlider.setMajorTickSpacing(5);
		wolfFoxFoodValueSlider.setMinorTickSpacing(1);
		wolfFoxFoodValueSlider.setPaintTicks(true);
		wolfFoxFoodValueSlider.setPaintLabels(true);
		
		wolfConfigPart.add(wolfBreedingLabel);
		wolfConfigPart.add(wolfBreedingSlider);
		
		wolfConfigPart.add(wolfMaxAgeLabel);
		wolfConfigPart.add(wolfMaxAgeSlider);
		
		wolfConfigPart.add(wolfBreedingProbabilityLabel);
		wolfConfigPart.add(wolfBreedingProbabilitySlider);
		
		wolfConfigPart.add(wolfMaxLitterSizeLabel);
		wolfConfigPart.add(wolfMaxLitterSizeSlider);
		
		wolfConfigPart.add(wolfRabbitFoodValueLabel);
		wolfConfigPart.add(wolfRabbitFoodValueSlider);
		
		wolfConfigPart.add(wolfFoxFoodValueLabel);
		wolfConfigPart.add(wolfFoxFoodValueSlider);
		
				
				
		//hunter labels
		JLabel hunterBulletLabel = new JLabel("Number of bullets:");
		
		// hunter sliders
		JSlider hunterBulletSlider = new JSlider(0, 20, brain.getConfig().getHunterBullets());
		
		hunterBulletSlider.setMajorTickSpacing(5);
		hunterBulletSlider.setMinorTickSpacing(1);
		hunterBulletSlider.setPaintTicks(true);
		hunterBulletSlider.setPaintLabels(true);
		
		hunterConfigPart.add(hunterBulletLabel);
		hunterConfigPart.add(hunterBulletSlider);
				
					
		
		configPanel.add(foxPanel);
		configPanel.add(rabbitPanel);
		configPanel.add(wolfPanel);
		configPanel.add(hunterPanel);
		
		
		return configPanel;
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
