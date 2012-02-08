package model;

import java.util.List;

/**
 * This makes a steady object on a location. In this case a rock
 * which can do nothing.
 * 
 * @author Marco
 * @author Malcolm
 * @author Harold
 * @version 2012.02.07
 */
public class Rock implements Actor
{
	private Field field;
    // The rock's position in the field.
    private Location location;
    // The brain
    protected Simulator brain;
	
	public Rock(Field field, Location location, Simulator brain){
		this.brain = brain;
		this.field = field;
		setLocation(location);
		
	}
	
	/**
     * Place the rock at the new location in the given field.
     * @param newLocation The rock's new location.
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isActive() {
		return true;
	}
	
	 /**
     * Return the rock's location.
     * @return The rock's location.
     */
    public Location getLocation()
    {
        return location;
    }
    
    /**
     * Return the rock's field.
     * @return The rock's field.
     */
    public Field getField()
    {
        return field;
    }

}
