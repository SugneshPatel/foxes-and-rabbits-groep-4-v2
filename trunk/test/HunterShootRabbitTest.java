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
	
	@Before
	public void setUp() throws Exception {
		field = new Field(20, 20);
		rabbit = new Rabbit(false, field, new Location(10, 8));
		hunter = new Hunter(field, new Location(10, 10));
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
		hunter.shoot(1, new Location(10, 8));
		assertEquals("Konijn niet doodgeschoten", false, rabbit.isActive());
	}

}
