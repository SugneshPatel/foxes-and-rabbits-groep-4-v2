package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HunterTest
{
	// hunter
	private Hunter hunter;
	// simulator
	private Simulator brain;

	@Before
	public void setUp() throws Exception {
		brain = new Simulator();
		hunter = new Hunter(brain.getField(), new Location(10, 10), brain);
	}

	@Test
	public void testHunter() {
		assertEquals("Er is geen hunter aangemaakt", true, hunter.isActive());
	}

}
