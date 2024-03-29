package model;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of a fox.
 * Foxes age, move, eat rabbits, and die.
 * 
 * @author David J. Barnes and Michael Kolling
 * @author Marco
 * @author Malcolm
 * @author Harold
 * @version 2012.02.07
 */
public class Fox extends Animal {
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    // The fox's food level, which is increased by eating rabbits.
    private int foodLevel;

    /**
     * Create a fox. A fox can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the fox will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Fox(boolean randomAge, Field field, Location location, Simulator brain) {
        super(field, location, brain);
        if(randomAge) {
            setAge(rand.nextInt(brain.getConfig().getFoxMaxAge()));
            foodLevel = rand.nextInt(brain.getConfig().getFoxRabbitFoodValue());
        }
        else {
            setAge(0);
            foodLevel = brain.getConfig().getFoxRabbitFoodValue();
        }
    }
    
    /**
     * Constructor for a fox
     * @param randomAge If true, the fox wil have random age and hunger level.
     * @param fiel The field currently occupied
     * @param location The location within the field.
     * @param foodLevel The starting foodLevel of a fox.
     */
    public Fox(boolean randomAge, Field field, Location location, int foodLevel, Simulator brain) {
    	super(field, location, brain);
    	if(randomAge) {
    		setAge(rand.nextInt(brain.getConfig().getFoxMaxAge()));
    		this.foodLevel = rand.nextInt(brain.getConfig().getFoxRabbitFoodValue());
    	}
    	else {
    		setAge(0);
    		this.foodLevel = foodLevel;
    	}
    }
    
    /**
     * This is what the fox does most of the time: it hunts for
     * rabbits. In the process, it might breed, die of hunger,
     * or die of old age.
     * @param field The field currently occupied.
     * @param newFoxes A list to add newly born foxes to.
     */
    public void act(List<Actor> newFoxes) {
        incrementAge();
        incrementHunger();
        if(isActive()) {
            giveBirth(newFoxes);            
            // Move towards a source of food if found.
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
     * Get the max age of the fox
     * @return int Max age of the fox
     */
    public int getMaxAge() {
    	return brain.getConfig().getFoxMaxAge();
    }
    
    /**
     * Make this fox more hungry. This could result in the fox's death.
     */
    public void incrementHunger() {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
            brain.getConfig().incrementDeathByStarvation();
        }
    }
    
    /**
     * Tell the fox to look for rabbits adjacent to its current location.
     * Only the first live rabbit is eaten.
     * @param location Where in the field it is located.
     * @return Where food was found, or null if it wasn't.
     */
    public Location findFood(Location location) {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if(rabbit.isActive()) { 
                    rabbit.setDead();
                    brain.getConfig().incrementDeathByEaten();
                    foodLevel = brain.getConfig().getFoxRabbitFoodValue();
                    // Remove the dead rabbit from the field.
                    return where;
                }
            }
        }
        return null;
    }
    
    /**
     * Check whether or not this fox is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newFoxes A list to add newly born foxes to.
     */
    private void giveBirth(List<Actor> newFoxes) {
        // New foxes are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Fox young = new Fox(false, field, loc, brain);
            newFoxes.add(young);
        }
    }
    
    /**
     * Get amount of food a fox has
     * @return foodlevel The amount of food
     */
    public int getFoodLevel() {
    	return foodLevel;
    }
    
    /**
     * Get breeding age
     * @return Breeding age
     */
    public int getBreedingAge() {
    	return brain.getConfig().getFoxBreedingAge();
    }
    
    /**
     * Get breeding probability
     * @return Breeding probability
     */
    public double getBreedingProbability() {
    	return brain.getConfig().getFoxBreedingProbability();
    }
    
    /**
     * Get max litter size
     * @return Max litter size
     */
    public int getMaxLitterSize() {
    	return brain.getConfig().getFoxMaxLitterSize();
    }
  }

