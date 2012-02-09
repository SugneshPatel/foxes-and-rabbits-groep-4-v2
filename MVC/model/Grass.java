package model;

import java.util.List;
import java.util.Random;

/**
 * This class make grass and allows it to grow
 * and die if it has been eaten
 * 
 * @author Marco
 * @author Malcolm
 * @author Harold
 * @version 2012.02.07
 */
public class Grass implements Actor {
	// Whether the grass is active or not.
    private boolean alive;
    // The grass' field.
    private Field field;
    // The grass' position in the field.
    private Location location;
    // The simulation brain
    protected Simulator brain;
    // randomizer
    private static final Random rand = Randomizer.getRandom();
    
    /**
     * Constructor to make a new piece of grass
     * @param field The field it's on
     * @param location The location on the field
     * @param brain The used brain
     */
	public Grass(Field field, Location location, Simulator brain) {
		this.brain = brain;
    	alive = true;
        this.field = field;
        setLocation(location);
	}
	
	/**
	 * If the grass is active (alive)
	 */
	@Override
	public boolean isActive() {
		return alive;
	}
	
	/**
     * Place the grass at the new location in the given field.
     * @param newLocation The grass' new location.
     */
    public void setLocation(Location newLocation) {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    
    /**
     * Return the grass' location.
     * @return The grass' location.
     */
    public Location getLocation() {
        return location;
    }
    
    /**
     * Return the grass' field.
     * @return The grass' field.
     */
    public Field getField() {
        return field;
    }
    
    /**
     * Makes the grass act, it grows new grass
     * @param List of actors with new grass
     */
    public void act(List<Actor> newGrass) {
        if(isActive()) {           
            grow(newGrass);
        }
    }
    
    /**
     * Calculates the amount of grass there will grow
     * @return Amount of grass
     */
    public int growGrass() {
        int grown = 0;
        if(rand.nextDouble() <= brain.getConfig().getGrassBreedingProbability()) {
            grown = rand.nextInt((brain.getConfig().getGrassMaxLitterSize()==0 ? brain.getConfig().getGrassMaxLitterSize() +1 : brain.getConfig().getGrassMaxLitterSize()));
        }
        return grown;
    }
    
    /**
     * Grows new grass on the free locations
     * @param newGrass The list of new grass
     */
    public void grow(List<Actor> newGrass){
    	Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        
        for(int b = 0; b < growGrass() && free.size() > 0; b++) {
            Location loc = free.remove(0);
            newGrass.add(new Grass(field, loc, brain));
        }
    }
    
    /**
     * Kills the grass
     */
    public void setDead() {
    	alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }
}
