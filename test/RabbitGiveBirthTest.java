import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class RabbitGiveBirthTest {
	
	// proefkonijn
	private Rabbit rabbit;
	// proefkonijn 2
	private Rabbit rabbitTwo;
	
	// konijnenhok, bevat nieuwe jonge konijntjes
	private List<Animal> newRabbits;
	// konijnenhok 2, bevat nieuwe jonge konijntjes
	private List<Animal> newRabbitsTwo;
	
	@Before
	public void setUp() throws Exception {
		rabbit = new Rabbit(false, new Field(20, 20), new Location(10, 10));
		rabbitTwo = new Rabbit(false, new Field(20, 20), new Location(10, 10));
		newRabbits = new ArrayList<Animal>();
		newRabbitsTwo = new ArrayList<Animal>();
	}
	
	@Test
	public void testActTwo()
	{
		for(int j = 0; j < 5; j++) {
			rabbitTwo.act(newRabbitsTwo);
		}
		assertEquals("Deze test mag fout gaan, maar moet vaker goed dan fout gaan", 0, newRabbitsTwo.size());
	}
	
	@Test
	public void testActOne() {
		for(int i = 0; i < 4; i++) {
			rabbit.act(newRabbits);
		}
		assertEquals("Hier is sprake van kleine-konijnen-porno", 0, newRabbits.size());
	}
	
	

}
