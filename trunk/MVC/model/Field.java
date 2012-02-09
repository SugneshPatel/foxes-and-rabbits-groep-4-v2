package model;
import java.util.Collections;
//import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Represent a rectangular grid of field positions.
 * Each position is able to store a single animal.
 * 
 * @author David J. Barnes and Michael Kolling
 * @author Marco
 * @author Malcolm
 * @author Harold
 * @version 2012.02.09
 */
public class Field {
    // A random number generator for providing random locations.
    private static final Random rand = Randomizer.getRandom();
    
    // The depth and width of the field.
    private int depth, width;
    // Storage for the animals.
    private Object[][] field;

    /**
     * Represent a field of the given dimensions.
     * @param depth The depth of the field.
     * @param width The width of the field.
     */
    public Field(int depth, int width) {
        this.depth = depth;
        this.width = width;
        field = new Object[depth][width];
    }
    
    /**
     * Empty the field.
     */
    public void clear() {
        for(int row = 0; row < depth; row++) {
            for(int col = 0; col < width; col++) {
                field[row][col] = null;
            }
        }
    }
    
    /**
     * Clear the given location.
     * @param location The location to clear.
     */
    public void clear(Location location) {
        field[location.getRow()][location.getCol()] = null;
    }
    
    /**
     * Place an animal at the given location.
     * If there is already an animal at the location it will
     * be lost.
     * @param animal The animal to be placed.
     * @param row Row coordinate of the location.
     * @param col Column coordinate of the location.
     */
    public void place(Object animal, int row, int col) {
        place(animal, new Location(row, col));
    }
    
    /**
     * Place an animal at the given location.
     * If there is already an animal at the location it will
     * be lost.
     * @param animal The animal to be placed.
     * @param location Where to place the animal.
     */
    public void place(Object animal, Location location) {
        field[location.getRow()][location.getCol()] = animal;
    }
    
    /**
     * Return the animal at the given location, if any.
     * @param location Where in the field.
     * @return The animal at the given location, or null if there is none.
     */
    public Object getObjectAt(Location location) {
        return getObjectAt(location.getRow(), location.getCol());
    }
    
    /**
     * Return the animal at the given location, if any.
     * @param row The desired row.
     * @param col The desired column.
     * @return The animal at the given location, or null if there is none.
     */
    public Object getObjectAt(int row, int col) {
        return field[row][col];
    }
    
    /**
     * Generate a random location that is adjacent to the
     * given location, or is the same location.
     * The returned location will be within the valid bounds
     * of the field.
     * @param location The location from which to generate an adjacency.
     * @return A valid location within the grid area.
     */
    public Location randomAdjacentLocation(Location location)
    {
        List<Location> adjacent = adjacentLocations(location);
        return adjacent.get(0);
    }
    
    /**
     * Get a shuffled list of the free adjacent locations.
     * @param location Get locations adjacent to this.
     * @return A list of free adjacent locations.
     */
    public List<Location> getFreeAdjacentLocations(Location location) {
        List<Location> free = new LinkedList<Location>();
        List<Location> adjacent = adjacentLocations(location);
        for(Location next : adjacent) {
            if(getObjectAt(next) == null) {
                free.add(next);
            }
        }
        return free;
    }
    
    /**
     * Try to find a free location that is adjacent to the
     * given location. If there is none, return null.
     * The returned location will be within the valid bounds
     * of the field.
     * @param location The location from which to generate an adjacency.
     * @return A valid location within the grid area.
     */
    public Location freeAdjacentLocation(Location location) {
        // The available free ones.
        List<Location> free = getFreeAdjacentLocations(location);
        if(free.size() > 0) {
            return free.get(0);
        }
        else {
            return null;
        }
    }

    /**
     * Return a shuffled list of locations adjacent to the given one.
     * The list will not include the location itself.
     * All locations will lie within the grid.
     * @param location The location from which to generate adjacencies.
     * @return A list of locations adjacent to that given.
     */
    public List<Location> adjacentLocations(Location location) {
        assert location != null : "Null location passed to adjacentLocations";
        // The list of locations to be returned.
        List<Location> locations = new LinkedList<Location>();
        if(location != null) {
            int row = location.getRow();
            int col = location.getCol();
            for(int roffset = -1; roffset <= 1; roffset++) {
                int nextRow = row + roffset;
                if(nextRow >= 0 && nextRow < depth) {
                    for(int coffset = -1; coffset <= 1; coffset++) {
                        int nextCol = col + coffset;
                        // Exclude invalid locations and the original location.
                        if(nextCol >= 0 && nextCol < width && (roffset != 0 || coffset != 0)) {
                            locations.add(new Location(nextRow, nextCol));
                        }
                    }
                }
            }
            // Shuffle the list. Several other methods rely on the list
            // being in a random order.
            Collections.shuffle(locations, rand);
        }
        return locations;
    }

    /**
     * Return the depth of the field.
     * @return The depth of the field.
     */
    public int getDepth() {
        return depth;
    }
    
    /**
     * Return the width of the field.
     * @return The width of the field.
     */
    public int getWidth() {
        return width;
    }
    
	/**
     * List of locations where a hunter can Shoot
     * the hunter can shoot for 3 locations ahead.
     * @param location Location of the hunter
     * @return List of random locations
     */
	public List<Location> getHunterRangeList(Location location) {
		assert location != null : "Null location passed to adjacentLocations";
        // The list of locations to be returned.
        List<Location> locations = new LinkedList<Location>();
        if(location != null) {
            int row = location.getRow();
            int col = location.getCol();
            for(int roffset = -3; roffset <= 3; roffset++) {
                int nextRow = row + roffset;
                if(nextRow >= 0 && nextRow < depth) {
                    for(int coffset = -3; coffset <= 3; coffset++) {
                        int nextCol = col + coffset;
                        // Exclude invalid locations and the original location.
                        if(nextCol >= 0 && nextCol < width && (roffset != 0 || coffset != 0)) {
                            locations.add(new Location(nextRow, nextCol));
                        }
                    }
                }
            }           
            // Shuffle the list. Several other methods rely on the list
            // being in a random order.
            Collections.shuffle(locations, rand);
        }
        return locations;
	}
	
	/**
	 * Method to return the location of the water in the field
	 * @return
	 */
	public List<Location> getWaterLocations() {
		List<Location> waterLocations = new LinkedList<Location>();
		for(int x = 75; x <= 100; x++) {
    		int v = (25 + x - 100);
    		for(int y = 100; y >= 100 - v; y--) {
    			Location location = new Location(x, y);
    			waterLocations.add(location);
    		}
    	}
		
		for(int x = 78; x <= 83; x++) {
    		for(int y = 96; y >= 91; y--) {
    			Location location = new Location(x, y);
    			waterLocations.add(location);
    		}
    	}
		
		for(int x = 79; x <= 85; x++) {
    		for(int y = 90; y >= 89; y--) {
    			Location location = new Location(x, y);
    			waterLocations.add(location);
    		}
    	}
		
		for(int x = 88; x <= 100; x++) {
    		for(int y = 86; y >= 75; y--) {
    			Location location = new Location(x, y);
    			waterLocations.add(location);
    		}
    	}
		
		for(int x = 84; x <= 87; x++) {
    		for(int y = 88; y >= 85; y--) {
    			Location location = new Location(x, y);
    			waterLocations.add(location);
    		}
    	}
		
		for(int x = 85; x <= 87; x++) {
    		for(int y = 84; y >= 82; y--) {
    			Location location = new Location(x, y);
    			waterLocations.add(location);
    		}
    	}
		
		for(int x = 86; x <= 87; x++) {
    		for(int y = 81; y >= 80; y--) {
    			Location location = new Location(x, y);
    			waterLocations.add(location);
    		}
    	}
		
		for(int x = 92; x <= 100; x++) {
    		for(int y = 74; y >= 73; y--) {
    			Location location = new Location(x, y);
    			waterLocations.add(location);
    		}
    	}
		
		for(int x = 93; x <= 100; x++) {
    		for(int y = 72; y >= 72; y--) {
    			Location location = new Location(x, y);
    			waterLocations.add(location);
    		}
    	}
		
		for(int x = 95; x <= 98; x++) {
    		for(int y = 71; y >= 70; y--) {
    			Location location = new Location(x, y);
    			waterLocations.add(location);
    		}
    	}
		
		waterLocations.add(new Location(77, 95));
		waterLocations.add(new Location(77, 96));
		waterLocations.add(new Location(77, 97));
		
		waterLocations.add(new Location(80, 88));
		waterLocations.add(new Location(81, 88));
		
		waterLocations.add(new Location(83, 88));
		
		waterLocations.add(new Location(87, 75));
		waterLocations.add(new Location(87, 76));
		
		waterLocations.add(new Location(88, 74));
		waterLocations.add(new Location(89, 74));
		
		waterLocations.add(new Location(94, 71));
		waterLocations.add(new Location(99, 71));
		waterLocations.add(new Location(100, 71));
		
		waterLocations.add(new Location(100, 70));
		
		waterLocations.add(new Location(96, 69));
		waterLocations.add(new Location(97, 69));
		
		return waterLocations;
	}
}

