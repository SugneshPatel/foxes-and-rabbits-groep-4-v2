import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class FoxEatRabbitTest {
	
	// proefkonijn
	private Rabbit rabbit;
	// hongerige vos
	private Fox fox;
	// veld waarin vos en konijn komen
	private Field field;

	@Before
	public void setUp() throws Exception {
		field = new Field(20, 20);
		rabbit = new Rabbit(false, field, new Location(10,10));
		fox = new Fox(false, field, new Location(10,11), 1);
	}
	
	@Test
	public void testFoodLevel()
	{
		assertEquals("Verkeerde constructor aangeroepen", 1, fox.getFoodLevel());
	}

	@Test
	public void testFindFood() {
		Location location = fox.getLocation();
		fox.findFood(location);
		assertEquals("De vos heeft het konijn gespaard", 7, fox.getFoodLevel());
		assertEquals("Konijn gaat niet dood", false, rabbit.isAlive());
	}

}
