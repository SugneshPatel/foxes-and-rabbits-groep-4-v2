import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class FoxDieFromHungerTest {
	// vos die gedoemd is dood te gaan
	private Fox fox;

	@Before
	public void setUp() throws Exception {
		this.fox = new Fox(false, new Field(20, 20), new Location(10, 10));
	}
	
	/**
	 * testen of vos na het aanmaken een foodlevel heeft van 7
	 */
	@Test
	public void testStartingHunger() {
		assertEquals("Vos heeft verkeerd foodlevel", 7, fox.getFoodLevel());
	}

	/**
	 * testen of vos zijn foodLevel inderdaad 1 omlaag gaat bij 
	 * aanroep van de methode IncrementHunger()
	 */
	@Test
	public void testIncrementHunger() {
		fox.incrementHunger();
		assertEquals("Vos krijgt nooit meer honger", 6, fox.getFoodLevel());
	}
	
	/**
	 * testen of vos dood gaat als foodLevel op 0 (nul) komt
	 */
	@Test
	public void testDieFromHunger() {
		assertEquals("Vos leeft niet", true, fox.isAlive());
		int currentFoodLevel = fox.getFoodLevel();
		for (int i = 0; i < currentFoodLevel; i++) {
			fox.incrementHunger();
		}
		assertEquals("Vos heeft geen konijnen nodig", false, fox.isAlive());
	}

}
