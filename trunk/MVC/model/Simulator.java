package model;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The pure brain of the simulation. This is model that that
 * talks to all the other models of the simulation
 * 
 * @author David J. Barnes and Michael Kolling
 * @author Marco
 * @author Malcolm
 * @author Harold
 * @version 2012.02.07
 */
public class Simulator extends AbstractModel implements Runnable
{    
    // List of animals in the field.
    private List<Actor> animals;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // Whether the simulation is running
    private boolean run;
    // That stat's that belong to the field
    private FieldStats stats;
    // Config file with variables
    protected static Config config;
    
    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator()
    {
        config = new Config();
        animals = new ArrayList<Actor>();
        field = new Field(config.getDEFAULT_WIDTH(), config.getDEFAULT_DEPTH());
        stats = new FieldStats();
    }
    
    /**
     * Get the config
     * @return config
     */
    public Config getConfig()
    {
    	return config;
    }
    
    /**
     * Run the simulation from its current state for a reasonably long period,
     * e.g. 500 steps.
     */
    public void runLongSimulation()
    {
        simulate(500);
    }
    
    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps)
    {
        for(int step = 1; step <= numSteps && isViable(); step++) {
            simulateOneStep();
        }
    }
    
    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * fox and rabbit.
     */
    public void simulateOneStep()
    {
        step++;

        // Provide space for newborn animals.
        List<Actor> newAnimals = new ArrayList<Actor>();        
        // Let all rabbits act.
        for(Iterator<Actor> it = animals.iterator(); it.hasNext(); ) {
            Actor actor = it.next();
            actor.act(newAnimals);
            if(! actor.isActive()) {
                it.remove();
            }
        }
               
        // Add the newly born foxes and rabbits to the main lists.
        animals.addAll(newAnimals);
        statusUpdate();
   
    }
        
    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
        step = 0;
        animals.clear();
        config.resetDeath();
        populate();
        
        // Show the starting state in the view.
        statusUpdate();
    }
    
    /**
     * Randomly populate the field with foxes and rabbits.
     */
    private void populate()
    {
        Random rand = Randomizer.getRandom();
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
            	
            	if(row > 29 && row < 36 & col > 29 && col < 46)
            	{
            		Location location = new Location(row, col);
            		Rock rock = new Rock(field, location, this);
            		animals.add(rock);
            	}
            	else if(rand.nextDouble() <= config.getHUNTER_CREATION_PROBABILITY()) {
            		Location location = new Location(row, col);
            		Hunter hunter = new Hunter(field, location, this);
            		animals.add(hunter);
            	}
            	else if(rand.nextDouble() <= config.getWOLF_CREATION_PROBABILITY()) {
            		Location location = new Location(row, col);
            		Wolf wolf = new Wolf(true, field, location, this);
            		animals.add(wolf);
            	}
            	else if(rand.nextDouble() <= config.getFOX_CREATION_PROBABILITY()) {
                    Location location = new Location(row, col);
                    Fox fox = new Fox(true, field, location, this);
                    animals.add(fox);
                }
                else if(rand.nextDouble() <= config.getRABBIT_CREATION_PROBABILITY()) {
                    Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(true, field, location, this);
                    animals.add(rabbit);
                }
            	
                else if(rand.nextDouble() <= config.getGRASS_CREATION_PROBABILITY()) {
                    Location location = new Location(row, col);
                    Grass grass = new Grass(field, location, this);
                    animals.add(grass);
                }
                // else leave the location empty.
            }
        }
    }
    
    /**
     * Get step
     * @return Step
     */
    public int getStep() {
    	return step;
    }
    
    /**
     * Get fieldstats
     * @return Fieldstats
     */
    public FieldStats getFieldStats() {
    	return stats;
    }
    
    /**
     * Get field
     * @return field
     */
    public Field getField() {
    	return field;
    }
    
    /**
     * Check whether none of the population is dead
     */
    public boolean isViable() {
        return stats.isViable(field);   
    }

    /**
     * Start a new thread that runs the run method
     */
    public void start(){
    	new Thread(this).start();
    }
    /**
     * Set's run to false thus stopping the thread/simulation
     */
    public void stop() {
    	run = false;
    }
    
    /**
     * Run's 1 step of the simulation
     */
	@Override
	public void run() {
		run=true;
		while(run) {
			simulate(1);
			try {
				Thread.sleep(100);
			} catch (Exception e) {} 
		}
	}
	
	/**
	 * Method to kill all actors on the field
	 */
	public void killAll(){
		step = 0;
        animals.clear();
        config.resetDeath();
        field.clear();
        stats.reset();
        statusUpdate();
	}

	public List<Actor> getAnimals() {
		return animals;
	}
}

