package model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ RabbitTest.class,
				RabbitIncrementAgeTest.class,
				RabbitGiveNoBirthTest.class,
				RabbitGiveBirthTest1.class
				})
public class AllRabbitTests
{

}
