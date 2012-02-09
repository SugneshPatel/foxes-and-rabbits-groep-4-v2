package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FoxEatRabbitTest
{

	// proefkonijn
	private Rabbit rabbit;
	// hongerige vos
	private Fox fox;
	// veld waarin vos en konijn komen
	private Field field;
	// simulator
	private Simulator brain;

	@Before
	public void setUp() throws Exception {
		brain = new Simulator();
		field = brain.getField();
		rabbit = new Rabbit(false, field, new Location(10,10), brain);
		fox = new Fox(false, field, new Location(10,11), 1, brain);
	}
	
	@Test
	public void testFoodLevel()
	{
		assertEquals("Verkeerde constructor aangeroepen", 1, fox.getFoodLevel());
	}
	
	@Test
	public void testRabbitInTheField()
	{
		assertEquals("Konijn is niet in veld geplaatst", true, rabbit.isActive());
	}
	
	@Test
	public void testFoxInTheField()
	{
		assertEquals("Vos is niet in veld geplaatst", true, fox.isActive());
	}

	@Test
	public void testFindFood() {
		Location location = fox.getLocation();
		fox.findFood(location);
		assertEquals("De vos heeft het konijn gespaard", brain.getConfig().getFoxRabbitFoodValue(), fox.getFoodLevel());
		assertEquals("Konijn gaat niet dood", false, rabbit.isActive());
	}

}
