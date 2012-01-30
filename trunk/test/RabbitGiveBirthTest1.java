import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class RabbitGiveBirthTest1 {

	// proefkonijn
	private Rabbit rabbit;
	
	// konijnenhok, bevat nieuwe jonge konijntjes
	private List<Actor> newRabbits;
	
	@Before
	public void setUp() throws Exception {
		rabbit = new Rabbit(false, new Field(20, 20), new Location(10, 10));
		newRabbits = new ArrayList<Actor>();
	}

	@Test
	public void testAct()
	{
		for(int j = 0; j < 40; j++) {
			rabbit.act(newRabbits);
		}
		assertEquals("Dit konijn is onvruchtbaar gebleken", false, newRabbits.isEmpty());
	}

}
