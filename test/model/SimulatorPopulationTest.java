package model;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class SimulatorPopulationTest
{
	// aantal vossen
	private int aantalVossen;
	// aantal konijnen
	private int aantalKonijnen;
	// simulator
	private Simulator brain;
	
	@Before
	public void setUp() throws Exception {
		this.aantalVossen = 0;
		this.aantalKonijnen = 0;
		brain = new Simulator();
	}

	@Test
	public void test() {
		Random rand = Randomizer.getRandom();
        for(int i = 0; i < 10000000; i++) {
            if(rand.nextDouble() <= brain.getConfig().getFOX_CREATION_PROBABILITY()) {
                aantalVossen++;
            }
            else if(rand.nextDouble() <= brain.getConfig().getRABBIT_CREATION_PROBABILITY()) {
                aantalKonijnen++;
            }
        }
        
        double uitkomst = aantalVossen / aantalKonijnen;
        
        assertEquals("Verhouding niet goed", 0.25, uitkomst, 2);
	}
}
