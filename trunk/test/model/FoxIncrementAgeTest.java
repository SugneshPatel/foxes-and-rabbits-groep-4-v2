package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FoxIncrementAgeTest
{

	// proefvos 1
	private Fox foxOne;
	// proefvos 2
	private Fox foxTwo;
	// proefvos 3
	private Fox foxThree;
	// proefvos 4
	private Fox foxFour;
	// simulator
	private Simulator brain;
	
	@Before
	public void setUp() throws Exception {
		brain = new Simulator();
		this.foxOne = new Fox(false, new Field(20, 20), new Location(10, 10), 100, brain);
		this.foxTwo = new Fox(false, new Field(20, 20), new Location(10, 10), 100, brain);
		this.foxThree = new Fox(false, new Field(20, 20), new Location(10, 10), 100, brain);
		this.foxFour = new Fox(false, new Field(20, 20), new Location(10, 10), 100, brain);
	}

	@Test
	public void testIncrementingAge() {
		// test of het ophogen van de leeftijd ook daadwerkelijk gebeurd
		foxOne.incrementAge();
		assertEquals("Leeftijd niet juist opgehoogt", 1, foxOne.getAge());
	}
	
	@Test
	public void testIncrementingAgeFourTimes()
	{
		// test of het ophogen vier keer lukt;
		for(int i = 1; i < 5; i++) {
			foxTwo.incrementAge();
		}
		
		assertEquals("Leeftijd niet juist opgehoogd na 4 keer", 4, foxTwo.getAge());
	}
	
	@Test
	public void testFoxAlive()
	{
		assertEquals("Vos is een zombie", true, foxThree.isActive());
	}
	
	@Test
	public void testDieingFox()
	{
		foxFour.setDead();
		assertEquals("Vos is onsterfelijk", false, foxFour.isActive());
	}

}
