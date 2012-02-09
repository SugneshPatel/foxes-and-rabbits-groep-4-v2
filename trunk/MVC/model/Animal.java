package model;
import java.util.List;
import java.util.Random;

/**
 * A class representing shared characteristics of animals.
 * 
 * @author David J. Barnes and Michael Kolling
 * @author Marco
 * @author Malcolm
 * @author Harold
 * @version 2012.02.07
 */
public abstract class Animal implements Actor {
	// the used brain
	protected Simulator brain;
    // Whether the animal is alive or not.
    private boolean alive;
    // The animal's field.
    private Field field;
    // The animal's position in the field.
    private Location location;
    // The animal's age
    private int age;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    /**
     * Create a new animal at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Animal(Field field, Location location, Simulator brain) {
    	this.brain = brain;
        alive = true;
        this.field = field;
        setLocation(location);
    }

    /**
     * Check whether the animal is alive or not.
     * @return true if the animal is still alive.
     */
    public boolean isActive() {
        return alive;
    }

    /**
     * Indicate that the animal is no longer alive.
     * It is removed from the field.
     */
    public void setDead() {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    /**
     * Return the animal's location.
     * @return The animal's location.
     */
    public Location getLocation() {
        return location;
    }
    
    /**
     * Return the animal's field.
     * @return The animal's field.
     */
    public Field getField() {
        return field;
    }
    
    /**
     * Place the animal at the new location in the given field.
     * @param newLocation The animal's new location.
     */
    public void setLocation(Location newLocation) {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    
    /**
     * Method to determine if an animal has reached the age
     * for it to be able to breed.
     * @return boolean If the animal can breed
     */
    public boolean canBreed() {
    	return age >= getBreedingAge();
    }
    
    /**
     * Set the age of the animal
     * @param age
     */
    public void setAge(int age) {
    	this.age = age;
    }
    
    /**
     * Get the age of the animal
     * @return age Age of the animal
     */
    public int getAge() {
    	return age;
    }
    
    /**
     * Method to increase the animals age, if age is greater
     * then max age it dies
     */
    public void incrementAge() {
        setAge(getAge() + 1);
        if(getAge() > getMaxAge()) {
            setDead();
        }
    }
    
    /**
     * Method to return the amount of births the animal will give
     * @return births The amount of births
     */
    public int breed() {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= getBreedingProbability()) {
            births = rand.nextInt(getMaxLitterSize()) + 1;
        }
        return births;
    }
    
    /**
     * Get the breeding age of the animal
     * @return int The breeding age
     */
    abstract public int getBreedingAge();
    
    /**
     * get the breeding probability
     * @return double The breeding probability
     */
    abstract public double getBreedingProbability();
    
    /**
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to add newly born animals to.
     */
    abstract public void act(List<Actor> newAnimals);
    
    /**
     * Get the max age of the animal
     * @return int Max age
     */
    abstract public int getMaxAge();
    
    /**
     * Get max litter size
     * @return int Max litter size
     */
    abstract public int getMaxLitterSize();
    
}

