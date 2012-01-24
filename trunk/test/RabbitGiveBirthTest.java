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
		this.rabbit = new Rabbit(true, new Field(20, 20), new Location(10, 10));
		this.newRabbits = new ArrayList<Animal>();
	}

	@Test
	public void testAct() {
		//hier wil ik testen of het voortplanten van de rabbits goed gebeurd.
	}

}
