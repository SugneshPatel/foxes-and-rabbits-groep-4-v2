package model;
import java.util.List;
import java.util.Random;

/**
 * A class representing shared characteristics of animals.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public abstract class Animal implements Actor
{
	// het simulatie brein
	protected Simulator brain;
    // Whether the animal is alive or not.
    private boolean alive;
    // The animal's field.
    private Field field;
    // The animal's position in the field.
    private Location location;
    // De leeftijd van het dier
    private int age;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    /**
     * Create a new animal at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Animal(Field field, Location location, Simulator brain)
    {
    	this.brain = brain;
        alive = true;
        this.field = field;
        setLocation(location);
    }

    /**
     * Check whether the animal is alive or not.
     * @return true if the animal is still alive.
     */
    public boolean isActive()
    {
        return alive;
    }

    /**
     * Indicate that the animal is no longer alive.
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
     * Return the animal's location.
     * @return The animal's location.
     */
    public Location getLocation()
    {
        return location;
    }
    
    /**
     * Return the animal's field.
     * @return The animal's field.
     */
    public Field getField()
    {
        return field;
    }
    
    /**
     * Place the animal at the new location in the given field.
     * @param newLocation The animal's new location.
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
     * Een dier kan zich voortplanten als het de 
     * voorplantingsleeftijd heeft bereikt
     * @return true als het dier zich kan voortplanten
     */
    public boolean canBreed()
    {
    	return age >= getBreedingAge();
    }
    
    /**
     * Geef het dier een leeftijd
     * @param age
     */
    public void setAge(int age)
    {
    	this.age = age;
    }
    
    /**
     * Retourneer de leeftijd van het dier
     * @return de leeftijd van het dier
     */
    public int getAge()
    {
    	return age;
    }
    
    /**
     * Verhoog de leeftijd van het dier.
     * Dit kan leiden tot de dood van het dier.
     */
    public void incrementAge()
    {
        setAge(getAge() + 1);
        if(getAge() > getMaxAge()) {
            setDead();
        }
    }
    
    /**
     * Retourneer het aantal jongen dat dit dier zal voortbrengen
     * @return het aantal jongen dat dit dier zal voorbrengen
     */
    public int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= getBreedingProbability()) {
            births = rand.nextInt(getMaxLitterSize()) + 1;
        }
        return births;
    }
    
    /**
     * Retourneer de voortplantingsleeftijd van dit dier
     * @return De voortplantingsleeftijd van dit dier
     */
    abstract public int getBreedingAge();
    
    /**
     * Retourneer de voortplantingskans van dit dier
     * @return de voortplantingskans van dit dier
     */
    abstract public double getBreedingProbability();
    
    /**
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to add newly born animals to.
     */
    abstract public void act(List<Actor> newAnimals);
    
    /**
     * Retourneer de maximale leeftijd van een dier
     * @return De maximale leeftijd van een dier
     */
    abstract public int getMaxAge();
    
    /**
     * Retourneer het maximale aantal jongen van een dier
     * @return het maixmale aantal jongen van een dier
     */
    abstract public int getMaxLitterSize();
    
}

