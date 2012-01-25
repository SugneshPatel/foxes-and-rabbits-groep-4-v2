import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class FoxTest {

	// proefvos
	private Fox fox;
	
	@Before
	public void setUp() throws Exception {
		fox = new Fox(false, new Field(20, 20), new Location(10, 10), 100);
	}

	@Test
	public void testFoxBooleanFieldLocationInt() {
		assertEquals("Er is geen nieuw vosje geboren", true, fox instanceof Fox);
	}

}
