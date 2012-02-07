package model;

import java.util.List;
import java.util.Random;

public class Grass implements Actor
{
	// Whether the hunter is active or not.
    private boolean alive;
    // The animal's field.
    private Field field;
    // The animal's position in the field.
    private Location location;
    // het simulatie brein
    protected Simulator brain;
    
    private static final Random rand = Randomizer.getRandom();
    
    
	
	public Grass(Field field, Location location, Simulator brain) {
		this.brain = brain;
    	alive = true;
        this.field = field;
        setLocation(location);
	}
	

	@Override
	public boolean isActive() {
		return alive;
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
    public void act(List<Actor> newGrass)
    {
        if(isActive()) {           
            grow(newGrass);
        }
    }
    
    protected int growGrass()
    {
        int grown = 0;
        if(rand.nextDouble() <= brain.getConfig().getGrassBreedingProbability()) {
            grown = rand.nextInt((brain.getConfig().getGrassMaxLitterSize()==0 ? brain.getConfig().getGrassMaxLitterSize() +1 : brain.getConfig().getGrassMaxLitterSize()));
        }
        return grown;
    }
    
    public void grow(List<Actor> newGrass){
    	Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        
        for(int b = 0; b < growGrass() && free.size() > 0; b++) {
            Location loc = free.remove(0);
            newGrass.add(new Grass(field, loc, brain));
        }
    }
    
    public void setDead(){
    	alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

}
