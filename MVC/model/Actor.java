package model;
import java.util.List;


public interface Actor
{
	/**
	 * Voer het gebruikelijke gedrag van de deelnemer uit.
	 * @param newActors Een lijst waarin zojuist gemaakte deelnemers worden opgeslagen.
	 */
	void act(List<Actor> newActors);
	
	/**
	 * Is de deelnemer nog steeds actief?
	 * @return true als de deelnemer nog actief is, anders
	 * false.
	 */
	boolean isActive();
}
