package model;

import java.util.List;

/**
 * This makes a steady object on a location. In this case water
 * which can do nothing.
 * 
 * @author Marco
 * @author Malcolm
 * @author Harold
 * @version 2012.02.07
 */
public class Water implements Actor
{
	private Field field;
    // The water's position in the field.
    private Location location;
    // The brain
    protected Simulator brain;
	
	public Water(Field field, Location location, Simulator brain){
		this.brain = brain;
		this.field = field;
		setLocation(location);
		
	}
	
	/**
     * Place the water at the new location in the given field.
     * @param newLocation The water's new location.
     */
    public void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
	
	@Override
	public void act(List<Actor> newActors) {
		
	}

	@Override
	public boolean isActive() {
		return true;
	}
	
	 /**
     * Return the water's location.
     * @return The water's location.
     */
    public Location getLocation()
    {
        return location;
    }
    
    /**
     * Return the water's field.
     * @return The water's field.
     */
    public Field getField()
    {
        return field;
    }

}
