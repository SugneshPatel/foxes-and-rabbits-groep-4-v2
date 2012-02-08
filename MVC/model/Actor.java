package model;
import java.util.List;

/**
 * Abstractmodel that can add views to the brain and update
 * all it views.
 * 
 * @author Marco
 * @author Malcolm
 * @author Harold
 * @version 2012.02.03
 */
public interface Actor
{
	/**
	 * Make the actor Act
	 * @param newActors List with all new actors
	 */
	void act(List<Actor> newActors);
	
	/**
	 * Method to determine of an actor is active
	 * @return boolean If the actor is active or not
	 */
	boolean isActive();
}
