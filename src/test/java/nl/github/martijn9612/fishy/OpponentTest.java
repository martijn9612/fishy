package nl.github.martijn9612.fishy;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.GameContainer;

import static org.mockito.Mockito.mock;

public class OpponentTest {
    private final GameContainer gc = mock(GameContainer.class);

    /**
     * Test case for getSize method
     */
    @Test
    public void testGetSize() {
        Opponent testopp = new Opponent(true, 0, 0, 1.0, 0);
        assertEquals(testopp.getSize(), 1.0, 0.1);
    }

    /**
     * Test case for isOffScreen method.
     * spawnsleft = true. 
     * Return condition = true.
     */
    @Test
    public void testIsOffScreen1() {
        Opponent testtruetrue = new Opponent(true, 10, 0, 1, 0);
        assertTrue(testtruetrue.isOffScreen());
    }

    /**
     * Test case for isOffScreen method.
     * spawnsleft = true. 
     * Return condition = false.
     */
    @Test
    public void testIsOffScreen2() {
        Opponent testtruefalse = new Opponent(true, 0, 0, 1, 0);
        assertFalse(testtruefalse.isOffScreen());
    }

    /**
     * Test case for isOffScreen method.
     * spawnsleft = false.
     * Return condition = true.
     */
    @Test
    public void testIsOffScreen3() {
        Opponent testfalsetrue = new Opponent(false, -2, 0, 1, 0);
        assertTrue(testfalsetrue.isOffScreen());
    }

    /**
     * Test case for isOffScreen method.
     * spawnsleft = false.
     * Return condition = false.
     */
    @Test
    public void testIsOffScreen4() {
        Opponent testfalsefalse = new Opponent(false, 10, 0, 1, 0);
        assertFalse(testfalsefalse.isOffScreen());
    }

    /**
     * Test case for objectLogic method.
     * spawnsleft = true.
     */
    @Test
    public void testObjectLogic() {
        Opponent opp = new Opponent(true, 0, 0, 1, 1);
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
        Opponent opp = new Opponent(false, 10, 10, 1, 1);
        float centerbefore = opp.ellipse.getCenterX();
        opp.objectLogic(gc, 0);
        assertEquals(opp.ellipse.getCenterX(), centerbefore - 1, 0.1);
        assertEquals(opp.getX(), 9.0, 0.1);
    }

}
