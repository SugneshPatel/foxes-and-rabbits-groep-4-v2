package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.JPanel;
import model.*;

/**
 * Makes a pie view of the current population of the simulation.
 * It puts it in a JPanel so it can be used.
 * 
 * @author Marco
 * @author Malcolm
 * @author Harold
 * @version 2012.02.07
 */
public class PieView extends AbstractView
{
	private static final long	serialVersionUID	= 3821761906525880524L;
	// colors that belong to an actor
	private Map<Class<?>, Color> colors;
	// set of classes
	private Set<Class<?>> classes;
	// the pieview
	private ViewOfPie viewOfPie;
	
	/**
	 * Makes a new pieview
	 * @param brain The brain it uses
	 */
	public PieView(Simulator brain) {
		super(brain);
		classes = new HashSet<Class<?>>();
        colors = new HashMap<Class<?>, Color>();   
	}
	
	/**
	 * Set colors according to animals
	 */
	@Override
	public void setColor(Class<?> animalClass, Color color) {
		colors.put(animalClass, color);
        classes = colors.keySet();		
	}
	/**
	 * Updates the view
	 */
	@Override
	public void showStatus() {
		viewOfPie.update(brain.getField());	
	}
	
	/**
	 * Puts the view into a JPanel
	 */
	@Override
	public JPanel getField() {
		viewOfPie = new ViewOfPie();
		JPanel pieView = new JPanel();
		pieView.add(viewOfPie);
		
		return pieView;
	}
	
	class ViewOfPie extends JComponent {
		
		private static final long	serialVersionUID	= 3755418793825034588L;
		// the view itself
		private BufferedImage pieImage;
		
		/**
		 * Make a new viewofpie
		 */
		public ViewOfPie(){
			pieImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
			clearImage();
		}
		
		/**
		 * Clears the image
		 */
		public void clearImage()
        {
            Graphics g = pieImage.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, pieImage.getWidth(), pieImage.getHeight());
            repaint();
        }

		/**
		 * Updates the view according to the field
		 * @param field
		 */
		public void update(Field field) {
			
			if (classes.size() >= 2) {
			Iterator<Class<?>> it = classes.iterator();
	        Class<?> class1 = it.next();
	        Class<?> class2 = it.next();
	        Class<?> class3 = it.next();
	        Class<?> class4 = it.next();
	        
	
	        brain.getFieldStats().reset();
	        // get stats from classes on the field
	        int count1 = brain.getFieldStats().getCount(field, class1);
	        int count2 = brain.getFieldStats().getCount(field, class2);
	        int count3 = brain.getFieldStats().getCount(field, class3);
	        int count4 = brain.getFieldStats().getCount(field, class4);
	        
	        int total = count1 + count2 + count3 + count4;
			
	        Graphics g = pieImage.getGraphics();
			
			double angle1 = (double) count1 / total * 360;
			double angle2 = (double) count2 / total * 360;
			double angle3 = (double) count3 / total * 360;
			double angle4 = (double) count4 / total * 360;
			
			// angles of the count
			int hoek1 = (int) angle1;
			int hoek2 = (int) angle2;
			int hoek3 = (int) angle3;
			int hoek4 = (int) angle4;
			
			// makes the arcs of the counts
			g.setColor(colors.get(class1));
			g.fillArc(10, 10, 180, 180, 0, hoek1);
			g.setColor(colors.get(class2));
			g.fillArc(10, 10, 180, 180, hoek1, hoek2);
			g.setColor(colors.get(class3));
			g.fillArc(10, 10, 180, 180, hoek1 + hoek2, hoek3);
			g.setColor(colors.get(class4));
			g.fillArc(10, 10, 180, 180, hoek1 + hoek2 + hoek3, hoek4);
			
			repaint();
		
		 }
		}
		
		@Override
		 public Dimension getPreferredSize()
	        {
	            return new Dimension(pieImage.getWidth(), pieImage.getHeight());
	        }
		 
		/**
		 * repaint now
		 */
		 public void repaintNow()
	        {
	            paintImmediately(0, 0, pieImage.getWidth(), pieImage.getHeight());
	        }

		/**
		 * Paints the pieview
		 */
		 public void paintComponent(Graphics g)
	        {
	            
	            if(pieImage != null) {
	                g.drawImage(pieImage, 0, 0, null);
	            }
	        }
}
}
