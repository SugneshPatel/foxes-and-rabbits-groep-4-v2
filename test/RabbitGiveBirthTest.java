import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class RabbitGiveBirthTest {
	
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
		
	}

}
