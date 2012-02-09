package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FoxDieFromHungerTest
{

	// vos die gedoemd is dood te gaan
	private Fox fox;
	// Simulator
	private Simulator brain;

	@Before
	public void setUp() throws Exception {
		this.brain = new Simulator();
		this.fox = new Fox(false, new Field(20, 20), new Location(10, 10), brain);
	}
	
	/**
	 * testen of vos na het aanmaken een foodlevel heeft van 7
	 */
	@Test
	public void testStartingHunger() {
		assertEquals("Vos heeft verkeerd foodlevel", brain.getConfig().getFoxRabbitFoodValue(), fox.getFoodLevel());
	}

	/**
	 * testen of vos zijn foodLevel inderdaad 1 omlaag gaat bij 
	 * aanroep van de methode IncrementHunger()
	 */
	@Test
	public void testIncrementHunger() {
		fox.incrementHunger();
		assertEquals("Vos krijgt nooit meer honger", brain.getConfig().getFoxRabbitFoodValue() - 1, fox.getFoodLevel());
	}
	
	/**
	 * testen of vos dood gaat als foodLevel op 0 (nul) komt
	 */
	@Test
	public void testDieFromHunger() {
		assertEquals("Vos leeft niet", true, fox.isActive());
		int currentFoodLevel = fox.getFoodLevel();
		for (int i = 0; i < currentFoodLevel; i++) {
			fox.incrementHunger();
		}
		assertEquals("Vos heeft geen konijnen nodig", false, fox.isActive());
	}

}
