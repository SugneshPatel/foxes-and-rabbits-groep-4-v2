package model;

/**
 * The config class keeps track of the the variables needed for the simulation
 * 
 * @author Marco
 * @author Malcolm
 * @author Harold
 * @version 2012.02.07
 */
public class Config {
	
	// creation variables
	private static int DEFAULT_WIDTH = 100;
	private static int DEFAULT_DEPTH = 100;
	private static double FOX_CREATION_PROBABILITY = 0.01;
	private static double RABBIT_CREATION_PROBABILITY = 0.08;
	private static double HUNTER_CREATION_PROBABILITY = 0.002;
	private static double WOLF_CREATION_PROBABILITY = 0.01;
	private static double GRASS_CREATION_PROBABILITY = 0.1;
	
	// fox variables
	private static int FOX_BREEDING_AGE = 8;
	private static int FOX_MAX_AGE = 150;
	private static double FOX_BREEDING_PROBABILITY = 0.37;
	private static int FOX_MAX_LITTER_SIZE = 4;
	private static int FOX_RABBIT_FOOD_VALUE = 13;
	
	// rabbit variables
	private static int RABBIT_BREEDING_AGE = 5;
	private static int RABBIT_MAX_AGE = 40;
	private static double RABBIT_BREEDING_PROBABILITY = 0.45;
	private static int RABBIT_MAX_LITTER_SIZE = 4;
	private static int RABBIT_GRASS_FOOD_VALUE = 4;
	
	// wolf variables
	private static int WOLF_BREEDING_AGE = 10;
	private static int WOLF_MAX_AGE = 150;
	private static double WOLF_BREEDING_PROBABILITY = 0.060;
	private static int WOLF_MAX_LITTER_SIZE = 6;
	private static int WOLF_RABBIT_FOOD_VALUE = 16;
	private static int WOLF_FOX_FOOD_VALUE = 23;
	
	// hunter variables
	private static int HUNTER_BULLETS = 3;
	
	// grass variables
	private static double GRASS_BREEDING_PROBABILITY = 0.40;
	private static int GRASS_MAX_LITTER_SIZE = 6;
	
	// deathcount
	private static int DEATH_BY_CROWD = 0;
	private static int DEATH_BY_STARVATION = 0;
	private static int DEATH_BY_BULLET = 0;
	private static int DEATH_BY_EATEN = 0;
	
	/*
	 * Setters for all the variables
	 */
	
	// fox setters
	public void setFoxBreedingAge(int i) {
		FOX_BREEDING_AGE = i;
	}
	
	public void setFoxMaxAge(int i) {
		FOX_MAX_AGE = i;
	}
	
	public void setFoxBreedingProbability(double d) {
		FOX_BREEDING_PROBABILITY = d;
	}
	
	public void setFoxMaxLitterSize(int i) {
		FOX_MAX_LITTER_SIZE = i;
	}
	
	public void setFoxRabbitFoodValue(int i) {
		FOX_RABBIT_FOOD_VALUE = i;
	}
	
	// rabbit setters
	public void setRabbitBreedingAge(int i) {
		RABBIT_BREEDING_AGE = i;
	}
	
	public void setRabbitMaxAge(int i) {
		RABBIT_MAX_AGE = i;
	}
	
	public void setRabbitBreedingProbability(double d) {
		RABBIT_BREEDING_PROBABILITY = d;
	}
	
	public void setRabbitMaxLitterSize(int i) {
		RABBIT_MAX_LITTER_SIZE = i;
	}
	
	public void setRabbitGrassFoodValue(int i) {
		RABBIT_GRASS_FOOD_VALUE = i;
	}
		
	// wolf setters
	public void setWolfBreedingAge(int i) {
		WOLF_BREEDING_AGE = i;
	}
	
	public void setWolfMaxAge(int i) {
		WOLF_MAX_AGE = i;
	}
	
	public void setWolfBreedingProbability(double d) {
		WOLF_BREEDING_PROBABILITY = d;
	}
	
	public void setWolfMaxLitterSize(int i) {
		WOLF_MAX_LITTER_SIZE = i;
	}
	
	public void setWolfRabbitFoodValue(int i) {
		WOLF_RABBIT_FOOD_VALUE = i;
	}
	
	public void setWolfFoxFoodValue(int i) {
		WOLF_FOX_FOOD_VALUE = i;
	}
	
	// hunter setters
	public void setHunterBullets(int i) {
		HUNTER_BULLETS = i;
	}
	
	// grass setters
	public void setGrassBreedingProbability(int i) {
		GRASS_BREEDING_PROBABILITY = i;
	}
	
	public void setGrassMaxLitterSize(int i) {
		GRASS_MAX_LITTER_SIZE = i;
	}
	
	// deathcount setters
	public void incrementDeathByCrowd() {
		DEATH_BY_CROWD++;
	}
	
	public void incrementDeathByStarvation() {
		DEATH_BY_STARVATION++;
	}
	
	public void incrementDeathByBullet() {
		DEATH_BY_BULLET++;
	}
	
	public void incrementDeathByEaten() {
		DEATH_BY_EATEN++;
	}
	
	// width and depth setters
	public void setDEFAULT_WIDTH(int dEFAULT_WIDTH) {
		DEFAULT_WIDTH = dEFAULT_WIDTH;
	}

	public void setDEFAULT_DEPTH(int dEFAULT_DEPTH) {
		DEFAULT_DEPTH = dEFAULT_DEPTH;
	}
	
	// creation probability setters
	public void setFOX_CREATION_PROBABILITY(double fOX_CREATION_PROBABILITY) {
		FOX_CREATION_PROBABILITY = fOX_CREATION_PROBABILITY;
	}

	public void setRABBIT_CREATION_PROBABILITY(
			double rABBIT_CREATION_PROBABILITY) {
		RABBIT_CREATION_PROBABILITY = rABBIT_CREATION_PROBABILITY;
	}

	public void setHUNTER_CREATION_PROBABILITY(
			double hUNTER_CREATION_PROBABILITY) {
		HUNTER_CREATION_PROBABILITY = hUNTER_CREATION_PROBABILITY;
	}

	public void setWOLF_CREATION_PROBABILITY(
			double wOLF_CREATION_PROBABILITY) {
		WOLF_CREATION_PROBABILITY = wOLF_CREATION_PROBABILITY;
	}

	public void setGRASS_CREATION_PROBABILITY(
			double gRASS_CREATION_PROBABILITY) {
		GRASS_CREATION_PROBABILITY = gRASS_CREATION_PROBABILITY;
	}
	
	/*
	 * Getters for all the variables
	 */
	
	// fox getters
	public int getFoxBreedingAge() {
		return FOX_BREEDING_AGE;
	}

	public int getFoxMaxAge() {
		return FOX_MAX_AGE;
	}

	public double getFoxBreedingProbability() {
		return FOX_BREEDING_PROBABILITY;
	}

	public int getFoxMaxLitterSize() {
		return FOX_MAX_LITTER_SIZE;
	}

	public int getFoxRabbitFoodValue() {
		return FOX_RABBIT_FOOD_VALUE;
	}
	
	// rabbit getters
	public double getRabbitBreedingProbability() {
		return RABBIT_BREEDING_PROBABILITY;
	}

	public int getRabbitMaxLitterSize() {
		return RABBIT_MAX_LITTER_SIZE;
	}

	public int getRabbitMaxAge() {
		return RABBIT_MAX_AGE;
	}

	public int getRabbitBreedingAge() {
		return RABBIT_BREEDING_AGE;
	}
	
	public int getRabbitGrassFoodValue() {
		return RABBIT_GRASS_FOOD_VALUE;
	}
	
	// wolf getters
	public double getWolfBreedingProbability() {
		return WOLF_BREEDING_PROBABILITY;
	}

	public int getWolfMaxAge() {
		return WOLF_MAX_AGE;
	}

	public int getWolfBreedingAge() {
		return WOLF_BREEDING_AGE;
	}

	public int getWolfMaxLitterSize() {
		return WOLF_MAX_LITTER_SIZE;
	}

	public int getWolfRabbitFoodValue() {
		return WOLF_RABBIT_FOOD_VALUE;
	}

	public int getWolfFoxFoodValue() {
		return WOLF_FOX_FOOD_VALUE;
	}
	
	// hunter getters
	public int getHunterBullets() {
		return HUNTER_BULLETS;
	}
	
	//getters grass
	public double getGrassBreedingProbability() {
		return GRASS_BREEDING_PROBABILITY;
	}
	
	public int getGrassMaxLitterSize() {
		return GRASS_MAX_LITTER_SIZE;
	}
	
	//getters deathcount
	public int getDeathByCrowd() {
		return DEATH_BY_CROWD;
	}
	
	public int getDeathByStarvation() {
		return DEATH_BY_STARVATION;
	}
	
	public int getDeathByBullet() {
		return DEATH_BY_BULLET;
	}
	
	public int getDeathByEaten() {
		return DEATH_BY_EATEN;
	}
	
	
	/**
	 * Method to reset all the death counts to 0
	 */
	public void resetDeath(){
		DEATH_BY_CROWD = 0;
		DEATH_BY_STARVATION = 0;
		DEATH_BY_BULLET = 0;
		DEATH_BY_EATEN = 0;
	}
	
	// getters width and depth
	public int getDEFAULT_WIDTH() {
		return DEFAULT_WIDTH;
	}

	public int getDEFAULT_DEPTH() {
		return DEFAULT_DEPTH;
	}
	
	// getters creation probabilities
	public double getFOX_CREATION_PROBABILITY() {
		return FOX_CREATION_PROBABILITY;
	}

	public double getRABBIT_CREATION_PROBABILITY() {
		return RABBIT_CREATION_PROBABILITY;
	}

	public double getHUNTER_CREATION_PROBABILITY() {
		return HUNTER_CREATION_PROBABILITY;
	}

	public double getWOLF_CREATION_PROBABILITY() {
		return WOLF_CREATION_PROBABILITY;
	}

	public double getGRASS_CREATION_PROBABILITY() {
		return GRASS_CREATION_PROBABILITY;
	}

}