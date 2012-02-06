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

public class PieView extends AbstractView
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3821761906525880524L;
	private Map<Class<?>, Color> colors;
	private Set<Class<?>> classes;
	private ViewOfPie viewOfPie;
	
	
	public PieView(Simulator brain) {
		super(brain);
		classes = new HashSet<Class<?>>();
        colors = new HashMap<Class<?>, Color>();
        
	}

	@Override
	public void setColor(Class<?> animalClass, Color color) {
		colors.put(animalClass, color);
        classes = colors.keySet();
		
	}

	@Override
	public void showStatus() {
		viewOfPie.update(brain.getField());
		
	}

	@Override
	public JPanel getField() {
		viewOfPie = new ViewOfPie();
		JPanel pieView = new JPanel();
		
		pieView.setLayout(new BorderLayout());
		pieView.add(viewOfPie, BorderLayout.CENTER);
		
		return pieView;
	}
	
	class ViewOfPie extends JComponent {
		
		/**
		 * 
		 */
		private static final long	serialVersionUID	= 3755418793825034588L;
		private BufferedImage graphImage;
		
		public ViewOfPie(){
			graphImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
			clearImage();
		}
		
		public void clearImage()
        {
            Graphics g = graphImage.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, graphImage.getWidth(), graphImage.getHeight());
            repaint();
        }

	
	
		public void update(Field field) {
			
			if (classes.size() >= 2) {
			Iterator<Class<?>> it = classes.iterator();
	        Class<?> class1 = it.next();
	        Class<?> class2 = it.next();
	        Class<?> class3 = it.next();
	        Class<?> class4 = it.next();
	        
	
	        brain.getFieldStats().reset();
	        int count1 = brain.getFieldStats().getCount(field, class1);
	        int count2 = brain.getFieldStats().getCount(field, class2);
	        int count3 = brain.getFieldStats().getCount(field, class3);
	        int count4 = brain.getFieldStats().getCount(field, class4);
	        
	        int total = count1 + count2 + count3 + count4;
			
	        Graphics g = graphImage.getGraphics();
			
			double angle1 = (double) count1 / total * 360;
			double angle2 = (double) count2 / total * 360;
			double angle3 = (double) count3 / total * 360;
			double angle4 = (double) count4 / total * 360;
			
			int hoek1 = (int) angle1;
			int hoek2 = (int) angle2;
			int hoek3 = (int) angle3;
			int hoek4 = (int) angle4;
			
			g.setColor(colors.get(class1));
			g.fillArc(10, 10, 180, 180, 0, hoek1);
			g.setColor(colors.get(class2));
			g.fillArc(10, 10, 180, 180, hoek1, hoek2);
			g.setColor(colors.get(class3));
			g.fillArc(10, 10, 180, 180, hoek1 + hoek2, hoek3);
			g.setColor(colors.get(class4));
			g.fillArc(10, 10, 180, 180, hoek1 + hoek2 + hoek3, hoek4);
			
			repaintNow();
		
		 }
		}
		
		 public Dimension getPreferredSize()
	        {
	            return new Dimension(graphImage.getWidth(), graphImage.getHeight());
	        }
		 public void repaintNow()
	        {
	            paintImmediately(0, 0, graphImage.getWidth(), graphImage.getHeight());
	        }

		
		 public void paintComponent(Graphics g)
	        {
	            
	            if(graphImage != null) {
	                g.drawImage(graphImage, 0, 0, null);
	            }
	        }
}
}
