package view;
import java.awt.*;

import javax.swing.*;

import model.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A graphical view of the simulation grid.
 * The view displays a colored rectangle for each location 
 * representing its contents. It uses a default background color.
 * Colors for each type of species can be defined using the
 * setColor method.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class SimulatorView extends AbstractView
{
    /**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	// Colors used for empty locations.
    private static final Color EMPTY_COLOR = Color.white;

    // Color used for objects that have no defined color.
    private static final Color UNKNOWN_COLOR = Color.gray;

    private final String STEP_PREFIX = "Step: ";
    private final String POPULATION_PREFIX = "Population: ";
    private JLabel stepLabel, population;
    private FieldView fieldView;
    
    // A map for storing colors for participants in the simulation
    private Map<Class<?>, Color> colors;
   
    
    
  
    /**
     * Create a view of the given width and height.
     * @param height The simulation's height.
     * @param width  The simulation's width.
     */
    public SimulatorView(Simulator brain)
    {
    	super(brain);
    
        colors = new LinkedHashMap<Class<?>, Color>();
        fieldView = new FieldView(100, 100);
        
    }
    
    public JPanel getField(){
    	
    	stepLabel = new JLabel(STEP_PREFIX, JLabel.CENTER);
        population = new JLabel(POPULATION_PREFIX, JLabel.CENTER);
     
        
        //JPanel die de buttons bevat
        
        JPanel rightSide = new JPanel();
        rightSide.setLayout(new BorderLayout());
        rightSide.add(stepLabel, BorderLayout.NORTH);
        rightSide.add(fieldView, BorderLayout.CENTER);
        rightSide.add(population, BorderLayout.SOUTH);
        rightSide.setBorder(BorderFactory.createLineBorder(Color.black));
        
        return rightSide;
    }
    
    /**
     * Define a color to be used for a given class of animal.
     * @param animalClass The animal's Class object.
     * @param color The color to be used for the given class.
     */
    public void setColor(Class<?> animalClass, Color color)
    {
        colors.put(animalClass, color);
    }

    /**
     * @return The color to be used for a given class of animal.
     */
    private Color getColor(Class<?> animalClass)
    {
        Color col = colors.get(animalClass);
        if(col == null) {
            // no color defined for this class
            return UNKNOWN_COLOR;
        }
        else {
            return col;
        }
    }
    

    /**
     * Show the current status of the field.
     * @param step Which iteration step it is.
     * @param field The field whose status is to be displayed.
     */
    public void showStatus()
    {
        if(!isVisible()) {
            setVisible(true);
        }
        
        stepLabel.setText(STEP_PREFIX + brain.getStep());
        
        brain.getFieldStats().reset();
        
        
        fieldView.preparePaint();
        
        for(int row = 0; row < brain.getField().getDepth(); row++) {
            for(int col = 0; col < brain.getField().getWidth(); col++) {
                Object animal = brain.getField().getObjectAt(row, col);
                if(animal != null) {
                	brain.getFieldStats().incrementCount(animal.getClass());
                    fieldView.drawMark(col, row, getColor(animal.getClass()));
                }
                else {
                    fieldView.drawMark(col, row, EMPTY_COLOR);
                }
            }
        }
       
        brain.getFieldStats().countFinished();

        population.setText(POPULATION_PREFIX + brain.getFieldStats().getPopulationDetails(brain.getField()));
        fieldView.repaint();
    }

    
    /**
     * Provide a graphical view of a rectangular field. This is 
     * a nested class (a class defined inside a class) which
     * defines a custom component for the user interface. This
     * component displays the field.
     * This is rather advanced GUI stuff - you can ignore this 
     * for your project if you like.
     */
    
    
}

