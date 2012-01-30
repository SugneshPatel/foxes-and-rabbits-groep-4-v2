import java.util.List;


public abstract class Actor {
	abstract public void act(List<Actor> newActors);
	abstract public boolean isActive();
}
