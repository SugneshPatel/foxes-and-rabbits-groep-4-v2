package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RabbitIncrementAgeTest
{
	// proefkonijn 1
	private Rabbit rabbitOne;
	// proefkonijn 2
	private Rabbit rabbitTwo;
	// proefkonijn 3
	private Rabbit rabbitThree;
	// proefkonijn 4
	private Rabbit rabbitFour;
	// simulator
	private Simulator brain;
	
	@Before
	public void setUp() throws Exception {
		brain = new Simulator();
		this.rabbitOne = new Rabbit(false, new Field(20, 20), new Location(10, 10), brain);
		this.rabbitTwo = new Rabbit(false, new Field(20, 20), new Location(10, 10), brain);
		this.rabbitThree = new Rabbit(false, new Field(20, 20), new Location(10, 10), brain);
		this.rabbitFour = new Rabbit(false, new Field(20, 20), new Location(10, 10), brain);
	}

	@Test
	public void testIncrementingAge() {
		// test of het ophogen van de leeftijd ook daadwerkelijk gebeurd
		rabbitOne.incrementAge();
		assertEquals("Leeftijd niet juist opgehoogt", 1, rabbitOne.getAge());
	}
	
	@Test
	public void testIncrementingAgeFourTimes()
	{
		// test of het ophogen vier keer lukt;
		for(int i = 1; i < 5; i++) {
			rabbitTwo.incrementAge();
		}
		
		assertEquals("Leeftijd niet juist opgehoogd na 4 keer", 4, rabbitTwo.getAge());
	}
	
	@Test
	public void testRabbitAlive()
	{
		assertEquals("Konijn is een zombie", true, rabbitThree.isActive());
	}
	
	@Test
	public void testDieingRabbit()
	{
		rabbitFour.setDead();
		assertEquals("Konijn is onsterfelijk", false, rabbitFour.isActive());
	}

}
