package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RabbitGiveNoBirthTest
{
	// proefkonijn
	private Rabbit rabbit;
	
	// simulator
	private Simulator brain;
	
	// konijnenhok, bevat nieuwe jonge konijntjes
	private List<Actor> newRabbits;
	
	@Before
	public void setUp() throws Exception {
		brain = new Simulator();
		rabbit = new Rabbit(false, new Field(20, 20), new Location(10, 10), brain);
		newRabbits = new ArrayList<Actor>();
	}
	
	@Test
	public void testAct() {
		for(int i = 0; i < 4; i++) {
			rabbit.act(newRabbits);
		}
		assertEquals("Hier is sprake van kleine-konijnen-porno", 0, newRabbits.size());
	}
	
	@After
	public void tearDownAfter()
	{
		rabbit.setDead();
	}

}
