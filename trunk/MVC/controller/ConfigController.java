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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Simulator;

public class ConfigController extends AbstractController implements ActionListener, ChangeListener
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5629702526729726101L;
	
	// fox labels
	private JLabel foxBreedingLabel, foxMaxAgeLabel, foxBreedingProbabilityLabel, foxMaxLitterSizeLabel, foxRabbitFoodValueLabel;
	// rabbit labels
	private JLabel rabbitBreedingLabel, rabbitMaxAgeLabel, rabbitBreedingProbabilityLabel, rabbitMaxLitterSizeLabel;
	// wolf labels
	private JLabel wolfBreedingLabel, wolfMaxAgeLabel, wolfBreedingProbabilityLabel, wolfMaxLitterSizeLabel, wolfRabbitFoodValueLabel, wolfFoxFoodValueLabel;
	// hunter labels
	private JLabel hunterBulletLabel;
	// fox sliders
	private JSlider foxBreedingSlider, foxMaxAgeSlider, foxBreedingProbabilitySlider, foxRabbitFoodValueSlider, foxMaxLitterSizeSlider;
	// rabbit sliders
	private JSlider rabbitBreedingSlider, rabbitMaxAgeSlider, rabbitBreedingProbabilitySlider, rabbitMaxLitterSizeSlider;
	// wolf sliders
	private JSlider wolfBreedingSlider, wolfMaxAgeSlider, wolfBreedingProbabilitySlider, wolfMaxLitterSizeSlider, wolfRabbitFoodValueSlider, wolfFoxFoodValueSlider;
	// hunter sliders
	private JSlider hunterBulletSlider;
	
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
		
		
		
		int tempBreedProbFox = (int) (brain.getConfig().getFoxBreedingProbability() * 100);
		double tempBreedProbFox2 = (double) (brain.getConfig().getFoxBreedingProbability());
		
		//fox labels
		foxBreedingLabel = new JLabel("Breeding age (" + brain.getConfig().getFoxBreedingAge() + "):");
		foxMaxAgeLabel = new JLabel("Max age (" + brain.getConfig().getFoxMaxAge() + "):");
		foxBreedingProbabilityLabel = new JLabel("Breeding probability (" + tempBreedProbFox2 + "):");
		foxMaxLitterSizeLabel = new JLabel("Max litter size (" + brain.getConfig().getFoxMaxLitterSize() + "):");
		foxRabbitFoodValueLabel = new JLabel("Rabbit food value (" + brain.getConfig().getFoxRabbitFoodValue() + "):");
		
		
		
		// fox sliders
		foxBreedingSlider = new JSlider(0, brain.getConfig().getFoxMaxAge(), brain.getConfig().getFoxBreedingAge());
		foxMaxAgeSlider = new JSlider(0, 200, brain.getConfig().getFoxMaxAge());
		foxBreedingProbabilitySlider = new JSlider(0, 100, tempBreedProbFox);
		foxRabbitFoodValueSlider = new JSlider(0, 25, brain.getConfig().getFoxRabbitFoodValue());
		foxMaxLitterSizeSlider = new JSlider(0, 20, brain.getConfig().getFoxMaxLitterSize());
		
		foxBreedingSlider.setMajorTickSpacing(25);
		foxBreedingSlider.setMinorTickSpacing(5);
		foxBreedingSlider.setPaintTicks(true);
		foxBreedingSlider.setPaintLabels(true);
		foxBreedingSlider.addChangeListener(this);
		
		foxMaxAgeSlider.setMajorTickSpacing(50);
		foxMaxAgeSlider.setMinorTickSpacing(10);
		foxMaxAgeSlider.setPaintTicks(true);
		foxMaxAgeSlider.setPaintLabels(true);
		foxMaxAgeSlider.addChangeListener(this);
		
		foxBreedingProbabilitySlider.setMajorTickSpacing(10);
		foxBreedingProbabilitySlider.setMinorTickSpacing(2);
		foxBreedingProbabilitySlider.setPaintTicks(true);
		foxBreedingProbabilitySlider.setPaintLabels(true);
		foxBreedingProbabilitySlider.addChangeListener(this);
		
		foxRabbitFoodValueSlider.setMajorTickSpacing(5);
		foxRabbitFoodValueSlider.setMinorTickSpacing(1);
		foxRabbitFoodValueSlider.setPaintTicks(true);
		foxRabbitFoodValueSlider.setPaintLabels(true);
		foxRabbitFoodValueSlider.addChangeListener(this);
		
		foxMaxLitterSizeSlider.setMajorTickSpacing(5);
		foxMaxLitterSizeSlider.setMinorTickSpacing(1);
		foxMaxLitterSizeSlider.setPaintTicks(true);
		foxMaxLitterSizeSlider.setPaintLabels(true);
		foxMaxLitterSizeSlider.addChangeListener(this);
		
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
		
		
		int tempBreedProbRabbit = (int) (brain.getConfig().getRabbitBreedingProbability() * 100);
		double tempBreedProbRabbit2 = (double) (brain.getConfig().getRabbitBreedingProbability());
		
		//rabbit labels
		rabbitBreedingLabel = new JLabel("Breeding age (" + brain.getConfig().getRabbitBreedingAge() + "):");
		rabbitMaxAgeLabel = new JLabel("Max age (" + brain.getConfig().getRabbitMaxAge() + "):");
		rabbitBreedingProbabilityLabel = new JLabel("Breeding probability (" + tempBreedProbRabbit2 + "):");
		rabbitMaxLitterSizeLabel = new JLabel("Max litter size (" + brain.getConfig().getRabbitMaxLitterSize() + "):");
		
		
		// rabbit sliders
		rabbitBreedingSlider = new JSlider(0, brain.getConfig().getRabbitMaxAge(), brain.getConfig().getRabbitBreedingAge());
		rabbitMaxAgeSlider = new JSlider(0, 200, brain.getConfig().getRabbitMaxAge());
		rabbitBreedingProbabilitySlider = new JSlider(0, 100, tempBreedProbRabbit);
		rabbitMaxLitterSizeSlider = new JSlider(0, 20, brain.getConfig().getRabbitMaxLitterSize());
		
		rabbitBreedingSlider.setMajorTickSpacing(25);
		rabbitBreedingSlider.setMinorTickSpacing(5);
		rabbitBreedingSlider.setPaintTicks(true);
		rabbitBreedingSlider.setPaintLabels(true);
		rabbitBreedingSlider.addChangeListener(this);
		
		rabbitMaxAgeSlider.setMajorTickSpacing(50);
		rabbitMaxAgeSlider.setMinorTickSpacing(10);
		rabbitMaxAgeSlider.setPaintTicks(true);
		rabbitMaxAgeSlider.setPaintLabels(true);
		rabbitMaxAgeSlider.addChangeListener(this);
		
		rabbitBreedingProbabilitySlider.setMajorTickSpacing(10);
		rabbitBreedingProbabilitySlider.setMinorTickSpacing(2);
		rabbitBreedingProbabilitySlider.setPaintTicks(true);
		rabbitBreedingProbabilitySlider.setPaintLabels(true);
		rabbitBreedingProbabilitySlider.addChangeListener(this);
		
		rabbitMaxLitterSizeSlider.setMajorTickSpacing(5);
		rabbitMaxLitterSizeSlider.setMinorTickSpacing(1);
		rabbitMaxLitterSizeSlider.setPaintTicks(true);
		rabbitMaxLitterSizeSlider.setPaintLabels(true);
		rabbitMaxLitterSizeSlider.addChangeListener(this);
		
		rabbitConfigPart.add(rabbitBreedingLabel);
		rabbitConfigPart.add(rabbitBreedingSlider);
		
		rabbitConfigPart.add(rabbitMaxAgeLabel);
		rabbitConfigPart.add(rabbitMaxAgeSlider);
		
		rabbitConfigPart.add(rabbitBreedingProbabilityLabel);
		rabbitConfigPart.add(rabbitBreedingProbabilitySlider);
		
		rabbitConfigPart.add(rabbitMaxLitterSizeLabel);
		rabbitConfigPart.add(rabbitMaxLitterSizeSlider);
				
		
		int tempBreedProbWolf = (int) (brain.getConfig().getWolfBreedingProbability() * 100);
		double tempBreedProbWolf2 = (double) (brain.getConfig().getWolfBreedingProbability());
		
		//wolf labels
		wolfBreedingLabel = new JLabel("Breeding age (" + brain.getConfig().getWolfBreedingAge() + "):");
		wolfMaxAgeLabel = new JLabel("Max age (" + brain.getConfig().getWolfMaxAge() + "):");
		wolfBreedingProbabilityLabel = new JLabel("Breeding probability (" + tempBreedProbWolf2 + "):");
		wolfMaxLitterSizeLabel = new JLabel("Max litter size (" + brain.getConfig().getWolfMaxLitterSize() + "):");
		wolfRabbitFoodValueLabel = new JLabel("Rabbit food value (" + brain.getConfig().getWolfRabbitFoodValue() + "):");
		wolfFoxFoodValueLabel = new JLabel("Fox food value (" + brain.getConfig().getWolfFoxFoodValue() + "):");
		
		
		
		// rabbit sliders
		wolfBreedingSlider = new JSlider(0, brain.getConfig().getWolfMaxAge(), brain.getConfig().getWolfBreedingAge());
		wolfMaxAgeSlider = new JSlider(0, 200, brain.getConfig().getWolfMaxAge());
		wolfBreedingProbabilitySlider = new JSlider(0, 100, tempBreedProbWolf);
		wolfMaxLitterSizeSlider = new JSlider(0, 20, brain.getConfig().getWolfMaxLitterSize());
		wolfRabbitFoodValueSlider = new JSlider(0, 25, brain.getConfig().getWolfRabbitFoodValue());
		wolfFoxFoodValueSlider = new JSlider(0, 25, brain.getConfig().getWolfFoxFoodValue());
		
		wolfBreedingSlider.setMajorTickSpacing(25);
		wolfBreedingSlider.setMinorTickSpacing(5);
		wolfBreedingSlider.setPaintTicks(true);
		wolfBreedingSlider.setPaintLabels(true);
		wolfBreedingSlider.addChangeListener(this);
		
		wolfMaxAgeSlider.setMajorTickSpacing(50);
		wolfMaxAgeSlider.setMinorTickSpacing(10);
		wolfMaxAgeSlider.setPaintTicks(true);
		wolfMaxAgeSlider.setPaintLabels(true);
		wolfMaxAgeSlider.addChangeListener(this);
		
		wolfBreedingProbabilitySlider.setMajorTickSpacing(10);
		wolfBreedingProbabilitySlider.setMinorTickSpacing(2);
		wolfBreedingProbabilitySlider.setPaintTicks(true);
		wolfBreedingProbabilitySlider.setPaintLabels(true);
		wolfBreedingProbabilitySlider.addChangeListener(this);
		
		wolfMaxLitterSizeSlider.setMajorTickSpacing(5);
		wolfMaxLitterSizeSlider.setMinorTickSpacing(1);
		wolfMaxLitterSizeSlider.setPaintTicks(true);
		wolfMaxLitterSizeSlider.setPaintLabels(true);
		wolfMaxLitterSizeSlider.addChangeListener(this);
		
		wolfRabbitFoodValueSlider.setMajorTickSpacing(5);
		wolfRabbitFoodValueSlider.setMinorTickSpacing(1);
		wolfRabbitFoodValueSlider.setPaintTicks(true);
		wolfRabbitFoodValueSlider.setPaintLabels(true);
		wolfRabbitFoodValueSlider.addChangeListener(this);
		
		wolfFoxFoodValueSlider.setMajorTickSpacing(5);
		wolfFoxFoodValueSlider.setMinorTickSpacing(1);
		wolfFoxFoodValueSlider.setPaintTicks(true);
		wolfFoxFoodValueSlider.setPaintLabels(true);
		wolfFoxFoodValueSlider.addChangeListener(this);
		
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
		hunterBulletLabel = new JLabel("Number of bullets (" + brain.getConfig().getHunterBullets() + "):");
		
		// hunter sliders
		hunterBulletSlider = new JSlider(0, 20, brain.getConfig().getHunterBullets());
		
		hunterBulletSlider.setMajorTickSpacing(5);
		hunterBulletSlider.setMinorTickSpacing(1);
		hunterBulletSlider.setPaintTicks(true);
		hunterBulletSlider.setPaintLabels(true);
		hunterBulletSlider.addChangeListener(this);
		
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
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if(source==foxBreedingSlider) {
			if (source.getValueIsAdjusting()) {
				int fps = (int)source.getValue();
				String temp = Integer.toString(fps);
				foxBreedingLabel.setText("Breeding age (" + temp + "):");
			}
			else {
				int fps = (int)source.getValue();
				brain.getConfig().setFoxBreedingAge(fps);
			}
		}
		else if(source==foxMaxAgeSlider) {
			if (source.getValueIsAdjusting()) {
				int fps = (int)source.getValue();
				String temp = Integer.toString(fps);
				foxMaxAgeLabel.setText("Max age (" + temp + "):");
			}
			else {
				int fps = (int)source.getValue();
				brain.getConfig().setFoxMaxAge(fps);
			}
		}
		else if(source==foxBreedingProbabilitySlider) {
			if (source.getValueIsAdjusting()) {
				double fps = (double)source.getValue();
				double temp1 = fps / 100;
				String temp = Double.toString(temp1);
				foxBreedingProbabilityLabel.setText("Breeding probability (" + temp + "):");
			}
			else {
				double fps = (double)source.getValue();
				double temp = fps / 100;
				brain.getConfig().setFoxBreedingProbability(temp);
			}
		}
		else if(source==foxRabbitFoodValueSlider) {
			if (source.getValueIsAdjusting()) {
				int fps = (int)source.getValue();
				String temp = Integer.toString(fps);
				foxRabbitFoodValueLabel.setText("Rabbit food value (" + temp + "):");
			}
			else {
				int fps = (int)source.getValue();
				brain.getConfig().setFoxRabbitFoodValue(fps);
			}
		}
		else if(source==foxMaxLitterSizeSlider) {
			if (source.getValueIsAdjusting()) {
				int fps = (int)source.getValue();
				String temp = Integer.toString(fps);
				foxMaxLitterSizeLabel.setText("Max litter size (" + temp + "):");
			}
			else {
				int fps = (int)source.getValue();
				brain.getConfig().setFoxMaxLitterSize(fps);
			}
		}
		else if(source==rabbitBreedingSlider) {
			if (source.getValueIsAdjusting()) {
				int fps = (int)source.getValue();
				String temp = Integer.toString(fps);
				rabbitBreedingLabel.setText("Breeding age (" + temp + "):");
			}
			else {
				int fps = (int)source.getValue();
				brain.getConfig().setRabbitBreedingAge(fps);
			}
		}
		else if(source==rabbitMaxAgeSlider) {
			if (source.getValueIsAdjusting()) {
				int fps = (int)source.getValue();
				String temp = Integer.toString(fps);
				rabbitMaxAgeLabel.setText("Max age (" + temp + "):");
			}
			else {
				int fps = (int)source.getValue();
				brain.getConfig().setRabbitMaxAge(fps);
			}
		}
		else if(source==rabbitBreedingProbabilitySlider) {
			if (source.getValueIsAdjusting()) {
				double fps = (double)source.getValue();
				double temp1 = fps / 100;
				String temp = Double.toString(temp1);
				rabbitBreedingProbabilityLabel.setText("Breeding probability (" + temp + "):");
			}
			else {
				double fps = (double)source.getValue();
				double temp = fps / 100;
				brain.getConfig().setRabbitBreedingProbability(temp);
			}
		}
		else if(source==rabbitMaxLitterSizeSlider) {
			if (source.getValueIsAdjusting()) {
				int fps = (int)source.getValue();
				String temp = Integer.toString(fps);
				rabbitMaxLitterSizeLabel.setText("Max litter size (" + temp + "):");
			}
			else {
				int fps = (int)source.getValue();
				brain.getConfig().setRabbitMaxLitterSize(fps);
			}
		}
		else if(source==wolfBreedingSlider) {
			if (source.getValueIsAdjusting()) {
				int fps = (int)source.getValue();
				String temp = Integer.toString(fps);
				wolfBreedingLabel.setText("Breeding age (" + temp + "):");
			}
			else {
				int fps = (int)source.getValue();
				brain.getConfig().setWolfBreedingAge(fps);
			}
		}
		else if(source==wolfMaxAgeSlider) {
			if (source.getValueIsAdjusting()) {
				int fps = (int)source.getValue();
				String temp = Integer.toString(fps);
				wolfMaxAgeLabel.setText("Max age (" + temp + "):");
			}
			else {
				int fps = (int)source.getValue();
				brain.getConfig().setWolfMaxAge(fps);
			}
		}
		else if(source==wolfBreedingProbabilitySlider) {
			if (source.getValueIsAdjusting()) {
				double fps = (double)source.getValue();
				double temp1 = fps / 100;
				String temp = Double.toString(temp1);
				wolfBreedingProbabilityLabel.setText("Breeding probability (" + temp + "):");
			}
			else {
				double fps = (double)source.getValue();
				double temp = fps / 100;
				brain.getConfig().setWolfBreedingProbability(temp);
			}
		}
		else if(source==wolfRabbitFoodValueSlider) {
			if (source.getValueIsAdjusting()) {
				int fps = (int)source.getValue();
				String temp = Integer.toString(fps);
				wolfRabbitFoodValueLabel.setText("Rabbit food value (" + temp + "):");
			}
			else {
				int fps = (int)source.getValue();
				brain.getConfig().setWolfRabbitFoodValue(fps);
			}
		}
		else if(source==wolfMaxLitterSizeSlider) {
			if (source.getValueIsAdjusting()) {
				int fps = (int)source.getValue();
				String temp = Integer.toString(fps);
				wolfMaxLitterSizeLabel.setText("Max litter size (" + temp + "):");
			}
			else {
				int fps = (int)source.getValue();
				brain.getConfig().setWolfMaxLitterSize(fps);
			}
		}
		else if(source==wolfFoxFoodValueSlider) {
			if (source.getValueIsAdjusting()) {
				int fps = (int)source.getValue();
				String temp = Integer.toString(fps);
				wolfFoxFoodValueLabel.setText("Fox food value (" + temp + "):");
			}
			else {
				int fps = (int)source.getValue();
				brain.getConfig().setWolfFoxFoodValue(fps);
			}
		}
		else if(source==hunterBulletSlider) {
			if (source.getValueIsAdjusting()) {
				int fps = (int)source.getValue();
				String temp = Integer.toString(fps);
				hunterBulletLabel.setText("Number of bullets (" + temp + "):");
			}
			else {
				int fps = (int)source.getValue();
				brain.getConfig().setHunterBullets(fps);
			}
		}
	}
}
