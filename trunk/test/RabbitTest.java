import static org.junit.Assert.*;

import org.junit.Test;


public class RabbitTest {

	@Test
	public void testRabbit() {
		Field field = new Field(10, 10);
		Location location = new Location(2, 2);
		Rabbit rabbit = new Rabbit(true, field, location);
		assertEquals("Is een kind geboren?", rabbit instanceof Rabbit, true);
	}

}
