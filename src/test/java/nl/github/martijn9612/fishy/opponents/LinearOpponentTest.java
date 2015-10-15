package nl.github.martijn9612.fishy.opponents;

import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.newdawn.slick.GameContainer;

import junit.framework.TestCase;
import nl.github.martijn9612.fishy.models.Moveable;
import nl.github.martijn9612.fishy.models.NonPlayer;
import nl.github.martijn9612.fishy.models.Vector;

public class LinearOpponentTest extends TestCase {
    private final GameContainer gc = mock(GameContainer.class);

    /**
     * Test case for objectLogic method.
     * Spawns the opponent at the left side of the screen.
     */
    @Test
    public void testLinearOpponentMoveRight() {
    	Moveable opponentData = new Moveable();
    	opponentData.dimensions = new Vector(10, 10);
    	opponentData.velocity = new Vector(1, 0);
    	NonPlayer opp = new LinearOpponent(opponentData, false);
        opp.objectLogic(gc, 0);
        assertEquals(1.0, opp.data.position.x, 0.1);
    }
    
    /**
     * Test case for objectLogic method.
     * Spawns the opponent at the right side of the screen.
     */
    @Test
    public void testLinearOpponentMoveLeft() {
    	Moveable opponentData = new Moveable();
    	opponentData.position = new Vector(10, 10);
    	opponentData.dimensions = new Vector(10, 10);
    	opponentData.velocity = new Vector(-1, 0);
    	NonPlayer opp = new LinearOpponent(opponentData, false);
        opp.objectLogic(gc, 0);
        assertEquals(9.0, opp.data.position.x, 0.1);
    }
}
