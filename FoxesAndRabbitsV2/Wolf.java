import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of a wolf.
 * Wolf age, move, eat rabbits and foxes, and die.
 * 
 * @author Malcolm Kindermans
 * @version 2012.01.29
 */
public class Wolf extends Animal
{
    // Characteristics shared by all wolfs (static fields).
    
    // The age at which a wolf can start to breed.
    private static final int BREEDING_AGE = 10;
    // The age to which a wolf can live.
    private static final int MAX_AGE = 150;
    // The likelihood of a wolf breeding.
    private static final double BREEDING_PROBABILITY = 0.01;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 5;
    // The food value of a single rabbit.
    private static final int RABBIT_FOOD_VALUE = 7;
    // The food value of a single fox. 
    private static final int FOX_FOOD_VALUE = 20;
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
    public Wolf(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        if(randomAge) {
            setAge(rand.nextInt(MAX_AGE));
            foodLevel = rand.nextInt(FOX_FOOD_VALUE);
        }
        else {
            setAge(0);
            foodLevel = FOX_FOOD_VALUE;
        }
    }
    
    /**
     * Maakt een wolf.
     * @param randomAge If true, the wolf wil have random age and hunger level.
     * @param fiel The field currently occupied
     * @param location The location within the field.
     * @param foodLevel The starting foodLevel of a wolf.
     */
    public Wolf(boolean randomAge, Field field, Location location, int foodLevel)
    {
    	super(field, location);
    	if(randomAge) {
    		setAge(rand.nextInt(MAX_AGE));
    		this.foodLevel = rand.nextInt(FOX_FOOD_VALUE);
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
            }
        }
    }
    
    /**
     * Retourneer de maximale leeftijd van een wolf.
     * @return maximale leeftijd van een wolf
     */
    public int getMaxAge()
    {
    	return MAX_AGE;
    }
    
    /**
     * Make this wolf more hungry. This could result in the wolf's death.
     */
    public void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
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
                    foodLevel = RABBIT_FOOD_VALUE;
                    // Remove the dead rabbit from the field.
                    return where;
                }
            }
            else if(animal instanceof Fox) {
            	Fox fox = (Fox) animal;
            	if(fox.isActive()) {
            		fox.setDead();
            		foodLevel = FOX_FOOD_VALUE;
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
            Wolf young = new Wolf(false, field, loc);
            newWolfes.add(young);
        }
    }
    
    /**
     * opvragen hoeveel voedsel de wolf in zich heeft
     * @return de hoeveelheid voedsel die de wolf in zich heeft
     */
    public int getFoodLevel()
    {
    	return foodLevel;
    }
    
    /**
     * Retourneer de leeftijd waarop een wolf zich begint voort te planten.
     * @return De leeftijd waarop een wolf zich begint voort te planten.
     */
    public int getBreedingAge()
    {
    	return BREEDING_AGE;
    }
    
    /**
     * Retourneer de voortplantingskans van dit dier
     * @return de voortplantingskans van dit dier
     */
    public double getBreedingProbability()
    {
    	return BREEDING_PROBABILITY;
    }
    
    /**
     * Retourneer het maximale aantal jongen van een dier
     * @return het maixmale aantal jongen van een dier
     */
    public int getMaxLitterSize()
    {
    	return MAX_LITTER_SIZE;
    }
    
}

