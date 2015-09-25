package nl.github.martijn9612.fishy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.newdawn.slick.GameContainer;

public class LinearOpponentTest {
    private final GameContainer gc = mock(GameContainer.class);

    /**
     * Test case for objectLogic method.
     * spawnsleft = true.
     */
    @Test
    public void testObjectLogic1() {
        Opponent opp = new LinearOpponent(true, 0, 0, 1, 1);
        float centerbefore = opp.ellipse.getCenterX();
        opp.objectLogic(gc, 0);
        assertEquals(opp.ellipse.getCenterX(), centerbefore + 1, 0.1);
        assertEquals(opp.getX(), 1, 0.1);
    }

    
    /**
     * Test case for objectLogic method.
     * spawnsleft = false;
     */
    @Test
    public void testObjectLogic2() {
        Opponent opp = new LinearOpponent(false, 10, 10, 1, 1);
        float centerbefore = opp.ellipse.getCenterX();
        opp.objectLogic(gc, 0);
        assertEquals(opp.ellipse.getCenterX(), centerbefore - 1, 0.1);
        assertEquals(opp.getX(), 9.0, 0.1);
    }
   
}
