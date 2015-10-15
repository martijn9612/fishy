package nl.github.martijn9612.fishy.opponents;

import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.newdawn.slick.GameContainer;

import junit.framework.TestCase;
import nl.github.martijn9612.fishy.models.Moveable;
import nl.github.martijn9612.fishy.models.NonPlayer;
import nl.github.martijn9612.fishy.models.Vector;
import nl.github.martijn9612.fishy.opponents.SinusOpponent;

public class SinusOpponentTest extends TestCase {
    private final GameContainer gc = mock(GameContainer.class);
    
    @Test
    public void testObjectLogic() {
    	Moveable opponentData = new Moveable();
    	opponentData.dimensions = new Vector(10, 1);
		NonPlayer testopp = new SinusOpponent(opponentData, false);
        testopp.objectLogic(gc, 0);
        assertEquals(-1.0, testopp.data.velocity.y, 0.1);
    }

	@Test
    public void testObjectLogic2() {
		Moveable opponentData = new Moveable();
		opponentData.dimensions = new Vector(10,1);
		opponentData.position = new Vector(1,1);
		NonPlayer testopp = new SinusOpponent(opponentData, false);
        testopp.data.position = new Vector(1, 1);
        testopp.objectLogic(gc, 0);
        assertEquals(-((1 % 80) / 20 + 1), testopp.data.velocity.y, 0.1);
    }
}
