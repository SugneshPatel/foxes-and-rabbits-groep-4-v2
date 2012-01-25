import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class RabbitGiveNoBirthTest2 {

	// proefkonijn
	private Rabbit rabbit;
	
	// konijnenhok, bevat nieuwe jonge konijntjes
	private List<Animal> newRabbits;
	
	@Before
	public void setUp() throws Exception {
		rabbit = new Rabbit(false, new Field(20, 20), new Location(10, 10));
		newRabbits = new ArrayList<Animal>();
	}

	@Test
	public void testAct()
	{
		for(int j = 0; j < 10; j++) {
			rabbit.act(newRabbits);
		}
		assertEquals("Deze test mag fout gaan, maar moet vaker goed dan fout gaan", 0, newRabbits.size());
	}
}