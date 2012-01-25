import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class RabbitIncrementAgeTest {
	
	// rabbit
	private Rabbit rabbit;
	// a list to add newly born rabbits to
	private List<Animal> newRabbits;
	
	@Before
	public void setUp() throws Exception {
		this.rabbit = new Rabbit(false, new Field(20, 20), new Location(10, 10));
		this.newRabbits = new ArrayList<Animal>();
	}

	@Test
	public void testIncrementingAge() {
		// test of het ophogen van de leeftijd ook daadwerkelijk gebeurd
		rabbit.incrementAge();
		assertEquals("Leeftijd niet juist opgehoogt", 1, rabbit.getAge());
	}
	
	@Test
	public void testIncrementingAgeFourTimes()
	{
		// test of het ophogen vier keer lukt;
		for(int i = 1; i < 5; i++) {
			rabbit.incrementAge();
		}
		
		assertEquals("Leeftijd niet juist opgehoogd na 4 keer", 4, rabbit.getAge());
	}

}
