package model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AllFoxTests.class,
				AllHunterTests.class,
				AllRabbitTests.class,
				SimulatorPopulationTest.class })
public class AllTests
{

}
