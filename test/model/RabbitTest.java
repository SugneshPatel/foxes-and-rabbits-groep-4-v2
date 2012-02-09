package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RabbitTest
{
	// rabbit
	private Rabbit rabbit;
	private Simulator brain;

	@Before
	public void setUp() throws Exception {
		this.brain = new Simulator();
		this.rabbit = new Rabbit(false, new Field(20, 20), new Location(10, 10), brain);
	}
	
	@Test
	public void testRabbit() {
		assertEquals("Er is geen nieuwe rabbit gemaakt?", rabbit instanceof Rabbit, true);
	}
}
