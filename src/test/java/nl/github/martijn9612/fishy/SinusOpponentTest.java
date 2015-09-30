package nl.github.martijn9612.fishy;

import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.newdawn.slick.GameContainer;

import junit.framework.TestCase;
import nl.github.martijn9612.fishy.models.Opponent;
import nl.github.martijn9612.fishy.models.Vector;
import nl.github.martijn9612.fishy.opponents.SinusOpponent;

public class SinusOpponentTest extends TestCase {
    private final GameContainer gc = mock(GameContainer.class);
    
    @Test
    public void testObjectLogic() {
    	Vector dimensions = new Vector(10,1);
		Vector position = new Vector(0,0);
		Opponent testopp = new SinusOpponent(position, dimensions, false);
        testopp.objectLogic(gc, 0);
        assertEquals(-1.0, testopp.velocity.y, 0.1);
    }

	@Test
    public void testObjectLogic2() {
		Vector dimensions = new Vector(10,1);
		Vector position = new Vector(1,1);
		Opponent testopp = new SinusOpponent(position, dimensions, false);
        testopp.position = new Vector(1, 1);
        testopp.objectLogic(gc, 0);
        assertEquals(-((1 % 80) / 20 + 1), testopp.velocity.y, 0.1);
    }
}
