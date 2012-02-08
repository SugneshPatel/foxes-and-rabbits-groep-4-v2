package model;
import java.util.List;
import java.util.Iterator;

/**
 * A simple model of a hunter.
 * He can walk and shoot rabbits and foxes
 * 
 * @author Marco
 * @author Malcolm
 * @author Harold
 * @version 2012.02.07
 */
public class Hunter implements Actor
{
	// Whether the hunter is active or not.
    private boolean alive;
    // The animal's field.
    private Field field;
    // The animal's position in the field.
    private Location location;
    // The brain
    protected Simulator brain;

    /**
     * Create a hunter.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Hunter(Field field, Location location, Simulator brain)
    {
    	this.brain = brain;
    	alive = true;
        this.field = field;
        setLocation(location);
    }
    
    /**
     * Place the hunter at the new location in the given field.
     * @param newLocation The hunter's new location.
     */
    public void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    
    /**
     * Return the hunter's location.
     * @return The hunter's location.
     */
    public Location getLocation()
    {
        return location;
    }
    
    /**
     * Return the hunter's field.
     * @return The hunter's field.
     */
    public Field getField()
    {
        return field;
    }
    
    /**
     * This is what the hunter does most of the time: it hunts for
     * foxes and rabbits.
     * @param newHunters The list of hunters
     */
    public void act(List<Actor> newHunters)
    {
        if(isActive()) {           
            // Move towards a source of food if found.
            Location location = getLocation();
            shoot(brain.getConfig().getHunterBullets(), location);
            Location newLocation = getField().freeAdjacentLocation(location);
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                setLocation(location);
            }
        }
    }
    
    /**
     * Indicate that the hunter is no longer active.
     * It is removed from the field.
     */
    public void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }
    
    /**
     * Check whether the hunter is alive or not.
     * @return true if the hunter is still alive.
     */
    public boolean isActive()
    {
        return alive;
    }
    
    /**
     * Makes the hunter shoot an animal
     * @param bullets How many times he can shoot
     * @param location Location of the hunter
     */
    public void shoot(int bullets, Location location)
    {
    	Field field = getField();
    	List<Location> randomLocations = field.getRandomLocations(bullets, getLocation());
    	Iterator<Location> it = randomLocations.iterator();
    	int shotsFired = 0;
    	while(it.hasNext() && shotsFired <= bullets) {
    		Location where = it.next();
    		Object animal = field.getObjectAt(where);
    		if(animal instanceof Rabbit) {
    			Rabbit rabbit = (Rabbit) animal;
    			if(rabbit.isActive()) {
    				rabbit.setDead();
    				shotsFired++;
    				brain.getConfig().incrementDeathByBullet();
    			}
    		}
    		else if(animal instanceof Fox) {
    			Fox fox = (Fox) animal;
    			if(fox.isActive()) {
    				fox.setDead();
    				shotsFired++;
    				brain.getConfig().incrementDeathByBullet();
    			}
    		}
    	}
    }   
}

