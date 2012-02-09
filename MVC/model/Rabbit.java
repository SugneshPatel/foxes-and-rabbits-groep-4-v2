package model;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * A simple model of a rabbit.
 * Rabbits age, move, breed, eat, and die.
 * 
 * @author David J. Barnes and Michael Kolling
 * @author Marco
 * @author Malcolm
 * @author Harold
 * @version 2012.02.07
 */
public class Rabbit extends Animal {
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    // foodlevel of the rabbit
    private int foodLevel;
    

    /**
     * Create a new rabbit. A rabbit may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the rabbit will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Rabbit(boolean randomAge, Field field, Location location, Simulator brain) {
        super(field, location, brain);
        setAge(0);
        foodLevel = brain.getConfig().getRabbitGrassFoodValue();
        if(randomAge) {
            setAge(rand.nextInt(brain.getConfig().getRabbitMaxAge()));
            foodLevel = rand.nextInt(brain.getConfig().getRabbitGrassFoodValue());
        }
    }
    
    /**
     * This is what the rabbit does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param newRabbits A list to add newly born rabbits to.
     */
    public void act(List<Actor> newRabbits) {
        incrementAge();
        incrementHunger();
        if(isActive()) {
            giveBirth(newRabbits);            
            // Try to move into a free location.
            Location location = getLocation();
            Location newLocation = findFood(location);
            if(newLocation == null) { 
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(location);
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
                brain.getConfig().incrementDeathByCrowd();
            }
        }
    }
    
    /**
     * Get rabbit's max age
     * @return Rabbit's max age
     */
    public int getMaxAge() {
    	return brain.getConfig().getRabbitMaxAge();
    }
    
    /**
     * Check whether or not this rabbit is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newRabbits A list to add newly born rabbits to.
     */
    private void giveBirth(List<Actor> newRabbits) {
        // New rabbits are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Rabbit young = new Rabbit(false, field, loc, brain);
            newRabbits.add(young);
        }
    }
    
    /**
     * Lets the rabbit find food. If he finds grass his foodlevel will be
     * boosted
     * @param location Location of the rabbit
     * @return
     */
    public Location findFood(Location location) {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object actor = field.getObjectAt(where);
            if(actor instanceof Grass) {
                Grass grass = (Grass) actor;
                if(grass.isActive()) { 
                    grass.setDead();
                    foodLevel = brain.getConfig().getRabbitGrassFoodValue();                                   
                }
                return where;
            }
        }
        return null;
    }
    
    /**
     * Decreases foodlevel. If foodlevel is 0 or below
     * it kills the rabbit
     */
    public void incrementHunger() {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
            brain.getConfig().incrementDeathByStarvation();
        }
    }
    
    /**
     * Get rabbit's breeding age
     * @return Rabbit's breeding age
     */
    public int getBreedingAge() {
    	return brain.getConfig().getRabbitBreedingAge();
    }
    
    /**
     * Get rabbit's breeding probability
     * @return Rabbit's breeding probability
     */
    public double getBreedingProbability() {
    	return brain.getConfig().getRabbitBreedingProbability();
    }
    
    /**
     * Get rabbit's max litter size
     * @return Rabbit's max litter size
     */
    public int getMaxLitterSize() {
    	return brain.getConfig().getRabbitMaxLitterSize();
    }      
}

