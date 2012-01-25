import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ RabbitGiveBirthTest.class, RabbitIncrementAgeTest.class, RabbitTest.class })
public class AllRabbitTests {

}
