package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


import javax.swing.JComponent;
import javax.swing.JPanel;

import model.Simulator;

/**
 * The bar view make a view of the deathcount of which animals die
 * This view is put into a JPanel.
 * 
 * @author Marco
 * @author Malcolm
 * @author Harold
 * @version 2012.02.08
 */
public class BarView extends AbstractView
{
	private static final long	serialVersionUID	= 106388220619050207L;
	// instance variables
	private BarPanel barPanel;
	
	/**
	 * Creates new barview
	 * @param brain The brain for the super
	 */
	public BarView(Simulator brain) {
		super(brain);		
	}
	
	/**
	 * Unimplemented
	 */
	@Override
	public void setColor(Class<?> animalClass, Color color) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Updates the views to the according step
	 */
	@Override
	public void showStatus() {
		barPanel.update(brain.getStep());
		
	}
	
	/**
	 * Returns a JPanel with the view
	 */
	@Override
	public JPanel getField() {
		barPanel = new BarPanel(200, 200, 500);				
		JPanel barView = new JPanel();
		// graphView.setLayout(new BorderLayout());
		barView.add(barPanel);

		return barView;
	}
	
	/**
     * Nested class: a component to display the bars.
     */
    class BarPanel extends JComponent
    {
		private static final long	serialVersionUID	= -93224324839379620L;

		private static final double SCALE_FACTOR = 0.8;

        // An internal image buffer that is used for painting. For
        // actual display, this image buffer is then copied to screen.
        private BufferedImage barImage;
        private int lastVal1, lastVal2, lastVal3, lastVal4;
        private int yMax;

        /**
         * Create a new, empty BarPanel.
         */
        public BarPanel(int width, int height, int startMax)
        {
        	barImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            clearImage();
            lastVal1 = height;
            lastVal2 = height;
            lastVal3 = height;
            lastVal4 = height;
            yMax = startMax;
        }

        /**
         * Indicate a new simulation run on this panel.
         */
        public void newRun()
        {
            int height = barImage.getHeight();
            int width = barImage.getWidth();

            Graphics g = barImage.getGraphics();
            g.copyArea(4, 0, width-4, height, -4, 0);            
          
            lastVal1 = height;
            lastVal2 = height;
            lastVal3 = height;
            lastVal4 = height;
            
            repaint();
        }

        /**
         * Dispay a new point of data.
         */
        public void update(int step)
        {	
        		if(step == 0){
        			clearImage();
        		}
        		
                Graphics g = barImage.getGraphics();

                int height = barImage.getHeight();
               
                // get deathcount numbers
                int deathCrowd = brain.getConfig().getDeathByCrowd();
                int deathStarvation = brain.getConfig().getDeathByStarvation();
                int deathBullet = brain.getConfig().getDeathByBullet();
                int deathEaten = brain.getConfig().getDeathByEaten();
                           
                // make bar 1
                int y = height - ((height * deathCrowd) / yMax) - 1;
                while (y<0) {
                    scaleDown();
                    y = height - ((height * deathCrowd) / yMax) - 1;
                }
                g.setColor(Color.cyan);
                g.fillRect(5, y, 40, 200);
                lastVal1 = y;
                
                // make bar 2
                y = height - ((height * deathStarvation) / yMax) - 1;
                while (y<0) {
                    scaleDown();
                    y = height - ((height * deathStarvation) / yMax) - 1;
                }
                g.setColor(Color.magenta);
                g.fillRect(55, y, 40, 200);
                lastVal2 = y;
                
                // make bar 3
                y = height - ((height * deathBullet) / yMax) - 1;
                while (y<0) {
                    scaleDown();
                    y = height - ((height * deathBullet) / yMax) - 1;
                }
                g.setColor(Color.gray);
                g.fillRect(105, y, 40, 200);
                lastVal3 = y;
                
                // make bar 4
                y = height - ((height * deathEaten) / yMax) - 1;
                while (y<0) {
                    scaleDown();
                    y = height - ((height * deathEaten) / yMax) - 1;
                }
                g.setColor(Color.pink);
                g.fillRect(155, y, 40, 200);
                lastVal4 = y;
                
                repaint();                
        }

        /**
         * Scale the current bars down vertically to make more room at the top.
         */
        public void scaleDown()
        {
            Graphics g = barImage.getGraphics();
            int height = barImage.getHeight();
            int width = barImage.getWidth();

            BufferedImage tmpImage = new BufferedImage(width, (int)(height*SCALE_FACTOR), 
                                                       BufferedImage.TYPE_INT_RGB);
            Graphics2D gtmp = (Graphics2D) tmpImage.getGraphics();

            gtmp.scale(1.0, SCALE_FACTOR);
            gtmp.drawImage(barImage, 0, 0, null);

            int oldTop = (int) (height * (1.0-SCALE_FACTOR));

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, width, oldTop);
            g.drawImage(tmpImage, 0, oldTop, null);

            yMax = (int) (yMax / SCALE_FACTOR);
            lastVal1 = oldTop + (int) (lastVal1 * SCALE_FACTOR);
            lastVal2 = oldTop + (int) (lastVal2 * SCALE_FACTOR);
            lastVal3 = oldTop + (int) (lastVal3 * SCALE_FACTOR);
            lastVal4 = oldTop + (int) (lastVal4 * SCALE_FACTOR);

            repaint();
        }

        /**
         * Cause immediate update of the panel.
         */
        public void repaintNow()
        {
            paintImmediately(0, 0, barImage.getWidth(), barImage.getHeight());
        }

        /**
         * Clear the image on this panel.
         */
        public void clearImage()
        {
            Graphics g = barImage.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, barImage.getWidth(), barImage.getHeight());
            repaint();
        }

        // The following methods are redefinitions of methods
        // inherited from superclasses.

        /**
         * Tell the layout manager how big we would like to be.
         * (This method gets called by layout managers for placing
         * the components.)
         * 
         * @return The preferred dimension for this component.
         */
        public Dimension getPreferredSize()
        {
            return new Dimension(barImage.getWidth(), barImage.getHeight());
        }

        /**
         * This component is opaque.
         */
        public boolean isOpaque()
        {
            return true;
        }

        /**
         * This component needs to be redisplayed. Copy the internal image 
         * to screen. (This method gets called by the Swing screen painter 
         * every time it want this component displayed.)
         * 
         * @param g The graphics context that can be used to draw on this component.
         */
        public void paintComponent(Graphics g)
        {
            
            if(barImage != null) {
                g.drawImage(barImage, 0, 0, null);
            }
        }
    
}
}



