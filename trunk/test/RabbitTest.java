import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class RabbitTest {
	
	// rabbit
	private Rabbit rabbit;

	@Before
	public void setUp() throws Exception {
		this.rabbit = new Rabbit(false, new Field(20, 20), new Location(10, 10));
	}
	
	@Test
	public void testRabbit() {
		assertEquals("Is een kind geboren?", rabbit instanceof Rabbit, true);
	}

}
