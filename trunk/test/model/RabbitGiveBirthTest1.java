package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RabbitGiveBirthTest1
{
	// proefkonijn
	private Rabbit rabbit;
	private Simulator brain;
	
	// konijnenhok, bevat nieuwe jonge konijntjes
	private List<Actor> newRabbits;
	
	@Before
	public void setUp() throws Exception {
		brain = new Simulator();
		rabbit = new Rabbit(false, brain.getField(), new Location(10, 10), brain);
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
