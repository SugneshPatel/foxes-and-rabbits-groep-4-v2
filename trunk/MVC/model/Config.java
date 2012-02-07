package model;

public class Config
{
	
	// fox variabelen
	private static int FOX_BREEDING_AGE = 10;
	private static int FOX_MAX_AGE = 150;
	private static double FOX_BREEDING_PROBABILITY = 0.35;
	private static int FOX_MAX_LITTER_SIZE = 5;
	private static int FOX_RABBIT_FOOD_VALUE = 7;
	
	// rabbit variabelen
	private static int RABBIT_BREEDING_AGE = 5;
	private static int RABBIT_MAX_AGE = 40;
	private static double RABBIT_BREEDING_PROBABILITY = 0.15;
	private static int RABBIT_MAX_LITTER_SIZE = 4;
	
	// wolf variabelen
	private static int WOLF_BREEDING_AGE = 10;
	private static int WOLF_MAX_AGE = 150;
	private static double WOLF_BREEDING_PROBABILITY = 0.01;
	private static int WOLF_MAX_LITTER_SIZE = 5;
	private static int WOLF_RABBIT_FOOD_VALUE = 7;
	private static int WOLF_FOX_FOOD_VALUE = 20;
	
	// hunter variabelen
	private static int HUNTER_BULLETS = 1;
	
	/*
	 * SETTERS
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
	
	/*
	 * GETTERS
	 */

	public static int getFoxBreedingAge() {
		return FOX_BREEDING_AGE;
	}

	public static int getFoxMaxAge() {
		return FOX_MAX_AGE;
	}

	public static double getFoxBreedingProbability() {
		return FOX_BREEDING_PROBABILITY;
	}

	public static int getFoxMaxLitterSize() {
		return FOX_MAX_LITTER_SIZE;
	}

	public static int getFoxRabbitFoodValue() {
		return FOX_RABBIT_FOOD_VALUE;
	}

	public static double getRabbitBreedingProbability() {
		return RABBIT_BREEDING_PROBABILITY;
	}

	public static int getRabbitMaxLitterSize() {
		return RABBIT_MAX_LITTER_SIZE;
	}

	public static int getRabbitMaxAge() {
		return RABBIT_MAX_AGE;
	}

	public static int getRabbitBreedingAge() {
		return RABBIT_BREEDING_AGE;
	}

	public static double getWolfBreedingProbability() {
		return WOLF_BREEDING_PROBABILITY;
	}

	public static int getWolfMaxAge() {
		return WOLF_MAX_AGE;
	}

	public static int getWolfBreedingAge() {
		return WOLF_BREEDING_AGE;
	}

	public static int getWolfMaxLitterSize() {
		return WOLF_MAX_LITTER_SIZE;
	}

	public static int getWolfRabbitFoodValue() {
		return WOLF_RABBIT_FOOD_VALUE;
	}

	public static int getWolfFoxFoodValue() {
		return WOLF_FOX_FOOD_VALUE;
	}

	public static int getHunterBullets() {
		return HUNTER_BULLETS;
	}
	
}
