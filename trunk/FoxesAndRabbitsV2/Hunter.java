import java.util.List;
import java.util.Iterator;

/**
 * A simple model of a hunter.
 * Foxes age, move, eat rabbits, and die.
 * 
 * @author Malcolm Kindermans
 * @version 2008.03.30
 */
public class Hunter implements Actor
{
	// Whether the hunter is active or not.
    private boolean alive;
    // The animal's field.
    private Field field;
    // The animal's position in the field.
    private Location location;

    /**
     * Create a hunter.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Hunter(Field field, Location location)
    {
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
     * foxes and wolfes.
     * @param field The field currently occupied.
     */
    public void act(List<Actor> newHunters)
    {
        if(isActive()) {           
            // Move towards a source of food if found.
            Location location = getLocation();
            Location newLocation = findFood(location);
            if(newLocation == null) { 
                // No pray found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(location);
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
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
     * Tell the hunter to look for foxes and wolfes adjacent to its current location.
     * Only the first live fox or wolf is shot.
     * @param location Where in the field it is located.
     * @return Where pray was found, or null if it wasn't.
     */
    public Location findFood(Location location)
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Fox) {
                Fox fox = (Fox) animal;
                if(fox.isActive()) { 
                    fox.setDead();
                    // Remove the dead rabbit from the field.
                    return where;
                }
            }
            else if(animal instanceof Wolf) {
            	Wolf wolf = (Wolf) animal;
            	if(wolf.isActive()) {
            		wolf.setDead();
            		// Remove the dead wolf from the field.
            		return where;
            	}
            }
        }
        return null;
    }
    
}

