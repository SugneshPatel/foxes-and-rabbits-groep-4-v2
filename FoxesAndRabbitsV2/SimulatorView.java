import java.awt.*;
import javax.swing.border.*;

import java.awt.event.*;
import javax.swing.*;

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
public class SimulatorView extends JFrame
{
    // Colors used for empty locations.
    private static final Color EMPTY_COLOR = Color.white;

    // Color used for objects that have no defined color.
    private static final Color UNKNOWN_COLOR = Color.gray;

    private final String STEP_PREFIX = "Step: ";
    private final String POPULATION_PREFIX = "Population: ";
    private JLabel stepLabel, population;
    private FieldView fieldView;
    
    // A map for storing colors for participants in the simulation
    private Map<Class, Color> colors;
    // A statistics object computing and storing simulation information
    private FieldStats stats;
    
    // JButton voor het runnen van de simulatie voor 1 stap
    private JButton stepOneButton;
    
    // JButton voor het runnen van de simulatie voor 100 stappen
    private JButton stepHundredButton;
    
    // JLabel met versie nummer
    private static final JLabel VERSIELABEL = new JLabel("Version 0.77");
    
    /**
     * het maken van de menubalk
     */
    public void makeMenuBar()
    {
    	JMenuBar menubar = new JMenuBar();
    	
    	setJMenuBar(menubar);
    	
    	JMenu menu1 = new JMenu("Menu1");
    	JMenu menu2 = new JMenu("Menu2");
    	JMenu helpMenu = new JMenu("Help");
    	
    	//quit item
    	JMenuItem quitItem = new JMenuItem("Afsluiten");
    		quitItem.addActionListener(new ActionListener() {
    							public void actionPerformed(ActionEvent e) { quit(); }
    		});
    	
    	menubar.add(menu1);
    	menubar.add(menu2);
    	menubar.add(helpMenu);
    	
    	//menu items toevoegen aan menu1
    	menu1.add(quitItem);
    	
    }

    /**
     * Create a view of the given width and height.
     * @param height The simulation's height.
     * @param width  The simulation's width.
     */
    public SimulatorView(int height, int width)
    {
    	stats = new FieldStats();
        colors = new LinkedHashMap<Class, Color>();

        setTitle("Fox and Rabbit Simulation");
        stepLabel = new JLabel(STEP_PREFIX, JLabel.CENTER);
        population = new JLabel(POPULATION_PREFIX, JLabel.CENTER);
        
        setLocation(100, 50);
        
        fieldView = new FieldView(height, width);
        
        
        //buttons
        stepOneButton = new JButton("Step 1");
        stepOneButton.setPreferredSize(new Dimension(90,25));
        stepOneButton.setMinimumSize(new Dimension(90,10));
        stepOneButton.setMaximumSize(new Dimension(90,25));
        
        
        stepHundredButton = new JButton("Step 100");
        stepHundredButton.setPreferredSize(new Dimension(90,25));
        stepHundredButton.setMinimumSize(new Dimension(90,10));
        stepHundredButton.setMaximumSize(new Dimension(90,25));
        
        
        //nieuw panel waarin buttons komen
        JPanel buttonsLeft = new JPanel();
        buttonsLeft.setBorder(new EmptyBorder(6, 6, 6, 6));
        buttonsLeft.setLayout(new BoxLayout(buttonsLeft, BoxLayout.PAGE_AXIS));
        
        //buttons toevoegen aan buttonsleft panel
        buttonsLeft.add(stepOneButton);
        buttonsLeft.add(stepHundredButton);
        
        JPanel container = (JPanel)getContentPane();
        container.setLayout(new BorderLayout());
        container.setBorder(new EmptyBorder(6,6,6,6));
        
        JPanel contents = new JPanel();
        
        container.add(contents, BorderLayout.CENTER);
        
        contents.setBorder(new EmptyBorder(6, 6, 6, 6));
        
        makeMenuBar();
        
        //JPanel die de buttons bevat
        
        JPanel rightSide = new JPanel();
        rightSide.setLayout(new BorderLayout());
        rightSide.add(stepLabel, BorderLayout.NORTH);
        rightSide.add(fieldView, BorderLayout.CENTER);
        rightSide.add(population, BorderLayout.SOUTH);
        rightSide.setBorder(BorderFactory.createLineBorder(Color.black));
        
        
        contents.setLayout(new BorderLayout());
        container.add(rightSide, BorderLayout.EAST);
        container.add(buttonsLeft, BorderLayout.WEST);
        container.add(VERSIELABEL, BorderLayout.SOUTH);
        pack();
        setVisible(true);
        
    }
    
    /**
     * Define a color to be used for a given class of animal.
     * @param animalClass The animal's Class object.
     * @param color The color to be used for the given class.
     */
    public void setColor(Class animalClass, Color color)
    {
        colors.put(animalClass, color);
    }

    /**
     * @return The color to be used for a given class of animal.
     */
    private Color getColor(Class animalClass)
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
    public void showStatus(int step, Field field)
    {
        if(!isVisible()) {
            setVisible(true);
        }
            
        stepLabel.setText(STEP_PREFIX + step);
        stats.reset();
        
        fieldView.preparePaint();

        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                Object animal = field.getObjectAt(row, col);
                if(animal != null) {
                    stats.incrementCount(animal.getClass());
                    fieldView.drawMark(col, row, getColor(animal.getClass()));
                }
                else {
                    fieldView.drawMark(col, row, EMPTY_COLOR);
                }
            }
        }
        stats.countFinished();

        population.setText(POPULATION_PREFIX + stats.getPopulationDetails(field));
        fieldView.repaint();
    }

    /**
     * Determine whether the simulation should continue to run.
     * @return true If there is more than one species alive.
     */
    public boolean isViable(Field field)
    {
        return stats.isViable(field);
    }
    
    /**
     * Provide a graphical view of a rectangular field. This is 
     * a nested class (a class defined inside a class) which
     * defines a custom component for the user interface. This
     * component displays the field.
     * This is rather advanced GUI stuff - you can ignore this 
     * for your project if you like.
     */
    private class FieldView extends JPanel
    {
        private final int GRID_VIEW_SCALING_FACTOR = 6;

        private int gridWidth, gridHeight;
        private int xScale, yScale;
        Dimension size;
        private Graphics g;
        private Image fieldImage;

        /**
         * Create a new FieldView component.
         */
        public FieldView(int height, int width)
        {
            gridHeight = height;
            gridWidth = width;
            size = new Dimension(0, 0);
        }

        /**
         * Tell the GUI manager how big we would like to be.
         */
        public Dimension getPreferredSize()
        {
            return new Dimension(gridWidth * GRID_VIEW_SCALING_FACTOR,
                                 gridHeight * GRID_VIEW_SCALING_FACTOR);
        }

        /**
         * Prepare for a new round of painting. Since the component
         * may be resized, compute the scaling factor again.
         */
        public void preparePaint()
        {
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
        public void drawMark(int x, int y, Color color)
        {
            g.setColor(color);
            g.fillRect(x * xScale, y * yScale, xScale-1, yScale-1);
        }

        /**
         * The field view component needs to be redisplayed. Copy the
         * internal image to screen.
         */
        public void paintComponent(Graphics g)
        {
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
    
    //aantal getters voor de JButtons
    /**
     * Getter voor de stepOne JButton
     * @return JButton stepOne
     */
    public JButton getStepOneButton()
    {
    	return stepOneButton;
    }
    
    /**
     * Getter de stepHundredButton
     * @return JButton stepHundredButton
     */
    public JButton getStepHundredButton()
    {
    	return stepHundredButton;
    }
    
    /**
     * Quit functie
     * sluit het programma af
     */
    private void quit()
    {
    	System.exit(0);
    }
}

