package model;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of a wolf.
 * Wolf age, move, eat rabbits and foxes, and die.
 * 
 * @author Marco
 * @author Malcolm
 * @author Harold
 * @version 2012.02.07
 */
public class Wolf extends Animal
{
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    // The wolf's food level, which is increased by eating rabbits and foxes.
    private int foodLevel;

    /**
     * Create a wolf. A wolf can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the wolf will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Wolf(boolean randomAge, Field field, Location location, Simulator brain)
    {
        super(field, location, brain);
        if(randomAge) {
            setAge(rand.nextInt(brain.getConfig().getWolfMaxAge()));
            foodLevel = rand.nextInt(brain.getConfig().getWolfFoxFoodValue());
        }
        else {
            setAge(0);
            foodLevel = brain.getConfig().getWolfFoxFoodValue();
        }
    }
    
    /**
     * Create a wolf
     * @param randomAge If true, the wolf wil have random age and hunger level.
     * @param fiel The field currently occupied
     * @param location The location within the field.
     * @param foodLevel The starting foodLevel of a wolf.
     */
    public Wolf(boolean randomAge, Field field, Location location, int foodLevel, Simulator brain)
    {
    	super(field, location, brain);
    	if(randomAge) {
    		setAge(rand.nextInt(brain.getConfig().getWolfMaxAge()));
    		this.foodLevel = rand.nextInt(brain.getConfig().getWolfFoxFoodValue());
    	}
    	else {
    		setAge(0);
    		this.foodLevel = foodLevel;
    	}
    }
    
    /**
     * This is what the wolf does most of the time: it hunts for
     * rabbits and foxes. In the process, it might breed, die of hunger,
     * or die of old age.
     * @param field The field currently occupied.
     * @param newWolfes A list to add newly born wolfs to.
     */
    public void act(List<Actor> newWolfes)
    {
        incrementAge();
        incrementHunger();
        if(isActive()) {
            giveBirth(newWolfes);            
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
     * Get wolf's max age
     * @return Wolf's max age
     */
    public int getMaxAge()
    {
    	return brain.getConfig().getWolfMaxAge();
    }
    
    /**
     * Make this wolf more hungry. This could result in the wolf's death.
     */
    public void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
            brain.getConfig().incrementDeathByStarvation();
        }
    }
    
    /**
     * Tell the wolf to look for rabbits or foxes adjacent to its current location.
     * Only the first live rabbit or fox is eaten.
     * @param location Where in the field it is located.
     * @return Where food was found, or null if it wasn't.
     */
    public Location findFood(Location location)
    {
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
                    foodLevel = brain.getConfig().getWolfRabbitFoodValue();
                    // Remove the dead rabbit from the field.
                    return where;
                }
            }
            else if(animal instanceof Fox) {
            	Fox fox = (Fox) animal;
            	if(fox.isActive()) {
            		fox.setDead();
            		brain.getConfig().incrementDeathByEaten();
            		foodLevel = brain.getConfig().getWolfFoxFoodValue();
            		// Remove the dead fox from the field
            		return where;
            	}
            }
        }
        return null;
    }
    
    /**
     * Check whether or not this wolf is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newWolfes A list to add newly born wolfs to.
     */
    private void giveBirth(List<Actor> newWolfes)
    {
        // New wolfes are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Wolf young = new Wolf(false, field, loc, brain);
            newWolfes.add(young);
        }
    }
    
    /**
     * Get wolf's foodlevel
     * @return Wolf's foodlevel
     */
    public int getFoodLevel()
    {
    	return foodLevel;
    }
    
    /**
     * Get wolf's breeding age
     * @return Wolf's breeding age
     */
    public int getBreedingAge()
    {
    	return brain.getConfig().getWolfBreedingAge();
    }
    
    /**
     * Get wolf's breeding probability
     * @return Wolf's breeding probability
     */
    public double getBreedingProbability()
    {
    	return brain.getConfig().getWolfBreedingProbability();
    }
    
    /**
     * Get wolf's max litter size
     * @return Wolf's max litter size
     */
    public int getMaxLitterSize()
    {
    	return brain.getConfig().getWolfMaxLitterSize();
    }
    
}

