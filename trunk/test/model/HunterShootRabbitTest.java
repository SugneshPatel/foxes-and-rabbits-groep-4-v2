package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HunterShootRabbitTest
{
	//instantie variabele
	// proefkonijn
	private Rabbit rabbit;
	// schietgrage jager
	private Hunter hunter;
	// Veld waarin de jager en het konijn zich bevinden
	private Field field;
	// simulator
	private Simulator brain;
	
	@Before
	public void setUp() throws Exception {
		brain = new Simulator();
		field = brain.getField();
		rabbit = new Rabbit(false, field, new Location(10, 8), brain);
		hunter = new Hunter(field, new Location(10, 10), brain);
	}
	
	@Test
	public void testRabbitAlive()
	{
		assertEquals("Proefkonijn leeft niet", true, rabbit.isActive());
	}
	
	@Test
	public void testHunterAlive()
	{
		assertEquals("Jager leeft niet", true, hunter.isActive());
	}

	@Test
	public void testShoot() {
		hunter.shoot(100, new Location(10, 8));
		assertEquals("Konijn niet doodgeschoten", false, rabbit.isActive());
	}
}
