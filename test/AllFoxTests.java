import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ FoxDieFromHungerTest.class, FoxEatRabbitTest.class, FoxIncrementAgeTest.class, FoxTest.class })
public class AllFoxTests {

}
