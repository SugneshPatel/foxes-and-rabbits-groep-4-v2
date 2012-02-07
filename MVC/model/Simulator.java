package model;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple predator-prey simulator, based on a rectangular field
 * containing rabbits and foxes.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class Simulator extends AbstractModel implements Runnable
{
	
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 100;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 100;
    // The probability that a fox will be created in any given grid position.
    protected static final double FOX_CREATION_PROBABILITY = 0.01;
    // The probability that a rabbit will be created in any given grid position.
    protected static final double RABBIT_CREATION_PROBABILITY = 0.08;    
    // The kans dat een jager gemaakt wordt op een bepaalde gegeven locatie
    protected static final double HUNTER_CREATION_PROBABILITY = 0.002;
    // De kans dat een wolf gemaakt wordt op een bepaalde gegeven locatie.
    protected static final double WOLF_CREATION_PROBABILITY = 0.005;
    
    protected static final double GRASS_CREATION_PROBABILITY = 0.1;
    

    // List of animals in the field.
    private List<Actor> animals;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    
    private boolean run;
    
    private FieldStats stats;
    
    // het config bestand
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
        field = new Field(DEFAULT_WIDTH, DEFAULT_DEPTH);
        stats = new FieldStats();

        
        // Setup a valid starting point.
        reset();
    }
    
    /**
     * returns the config
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
            	if(rand.nextDouble() <= HUNTER_CREATION_PROBABILITY) {
            		Location location = new Location(row, col);
            		Hunter hunter = new Hunter(field, location, this);
            		animals.add(hunter);
            	}
            	else if(rand.nextDouble() <= WOLF_CREATION_PROBABILITY) {
            		Location location = new Location(row, col);
            		Wolf wolf = new Wolf(true, field, location, this);
            		animals.add(wolf);
            	}
            	else if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Fox fox = new Fox(true, field, location, this);
                    animals.add(fox);
                }
                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(true, field, location, this);
                    animals.add(rabbit);
                }
            	
                else if(rand.nextDouble() <= GRASS_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Grass grass = new Grass(field, location, this);
                    animals.add(grass);
                }
                // else leave the location empty.
            }
        }
    }
    // vanaf hier
    public int getStep() {
    	return step;
    }
    
    public FieldStats getFieldStats() {
    	return stats;
    }
    
    public Field getField() {
    	return field;
    }
    
    public boolean isViable()
    {
        return stats.isViable(field);
    	
    }
    // tot hier
    
    
    
    public void start(){
    	new Thread(this).start();
    }
    
    public void stop() {
    	run = false;
    }

	@Override
	public void run() {
		run=true;
		while(run) {
			simulateOneStep();
			try {
				Thread.sleep(100);
			} catch (Exception e) {} 
		}
	}
}

