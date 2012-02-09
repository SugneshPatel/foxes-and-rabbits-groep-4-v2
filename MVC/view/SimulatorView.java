package view;
import java.awt.*;

import javax.swing.*;

import model.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class that makes the field view of the simulation. It puts it
 * in a panel so it can be used and updated.
 * 
 * @author Marco
 * @author Malcolm
 * @author Harold
 * @version 2012.02.07
 */
public class SimulatorView extends AbstractView {
	private static final long	serialVersionUID	= 1L;

	// Colors used for empty locations.
    private static final Color EMPTY_COLOR = Color.white;
    // Color used for objects that have no defined color.
    private static final Color UNKNOWN_COLOR = Color.gray;
    // Label for the amount of steps
    private final String STEP_PREFIX = "Step: ";
    // Label for the current population
    private final String POPULATION_PREFIX = "Population: ";
    // Labels
    private JLabel stepLabel, population;
    // Used view
    private FieldView fieldView;
    
    // A map for storing colors for participants in the simulation
    private Map<Class<?>, Color> colors;
  
    /**
     * Create a simulator view
     */
    public SimulatorView(Simulator brain) {
    	super(brain);
        colors = new LinkedHashMap<Class<?>, Color>();
        fieldView = new FieldView(100, 100);      
    }
    
    public JPanel getField(){
    	
    	stepLabel = new JLabel(STEP_PREFIX, JLabel.CENTER);
        population = new JLabel(POPULATION_PREFIX, JLabel.CENTER);
        
        // add labels and views to the panel
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BorderLayout());
        fieldPanel.add(stepLabel, BorderLayout.NORTH);
        fieldPanel.add(fieldView, BorderLayout.CENTER);
        fieldPanel.add(population, BorderLayout.SOUTH);
        fieldPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        return fieldPanel;
    }
    
    /**
     * Define a color to be used for a given class of animal.
     * @param animalClass The animal's Class object.
     * @param color The color to be used for the given class.
     */
    public void setColor(Class<?> animalClass, Color color) {
        colors.put(animalClass, color);
    }

    /**
     * @return The color to be used for a given class of animal.
     */
    private Color getColor(Class<?> animalClass) {
        Color col = colors.get(animalClass);
        if(col == null) {
            // no color defined for this class
            return UNKNOWN_COLOR;
        }
        else {
            return col;
        }
    }
    
    public FieldView getFieldView() {
    	return fieldView;
    }
    

    /**
     * Show the current status of the field.
     */
    public void showStatus() {
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
    private class FieldView extends JPanel {
       
		private static final long	serialVersionUID	= 1L;

		private final int GRID_VIEW_SCALING_FACTOR = 6;

        private int gridWidth, gridHeight;
        private int xScale, yScale;
        Dimension size;
        private Graphics g;
        private Image fieldImage;

        /**
         * Create a new FieldView component.
         */
        public FieldView(int height, int width) {
            gridHeight = height;
            gridWidth = width;
            size = new Dimension(0, 0);
        }

        /**
         * Tell the GUI manager how big we would like to be.
         */
        public Dimension getPreferredSize() {
            return new Dimension(gridWidth * GRID_VIEW_SCALING_FACTOR,
                                 gridHeight * GRID_VIEW_SCALING_FACTOR);
        }

        /**
         * Prepare for a new round of painting. Since the component
         * may be resized, compute the scaling factor again.
         */
        public void preparePaint() {
            if(! size.equals(getSize())) {  // if the size has changed...
                size = getSize();
                fieldImage = fieldView.createImage(size.width, size.height);
                g = fieldImage.getGraphics();

                xScale = size.width / gridWidth;
                if(xScale < 1) {
                    xScale = GRID_VIEW_SCALING_FACTOR;
                }
                yScale = size.height / gridHeight;
                if(yScale < 1) {
                    yScale = GRID_VIEW_SCALING_FACTOR;
                }
            }
        }
        
        /**
         * Paint on grid location on this field in a given color.
         */
        public void drawMark(int x, int y, Color color) {
            g.setColor(color);
            g.fillRect(x * xScale, y * yScale, xScale-1, yScale-1);
        }

        /**
         * The field view component needs to be redisplayed. Copy the
         * internal image to screen.
         */
        public void paintComponent(Graphics g) {
            if(fieldImage != null) {
                Dimension currentSize = getSize();
                if(size.equals(currentSize)) {
                    g.drawImage(fieldImage, 0, 0, null);
                }
                else {
                    // Rescale the previous image.
                    g.drawImage(fieldImage, 0, 0, currentSize.width, currentSize.height, null);
                }
            }
        }
    }      
}

