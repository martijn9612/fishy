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
        Opponent opp = new SinusOpponent(10, 1, false);
        opp.position = new Vector(0, 0);
        opp.objectLogic(gc, 0);
        assertEquals(-1.0, opp.velocity.y, 0.1);
    }

	@Test
    public void testObjectLogic2() {
        Opponent opp = new SinusOpponent(10, 1, false);
        opp.position = new Vector(1, 1);
        opp.objectLogic(gc, 0);
        assertEquals(-((1 % 80) / 20 + 1), opp.velocity.y, 0.1);
    }
}
