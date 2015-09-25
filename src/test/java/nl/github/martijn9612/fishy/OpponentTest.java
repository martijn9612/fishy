package nl.github.martijn9612.fishy;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.GameContainer;

import static org.mockito.Mockito.mock;

public class OpponentTest {

    /**
     * Test case for getSize method
     */
    @Test
    public void testGetSize() {
        Opponent testopp = new LinearOpponent(true, 0, 0, 1.0, 0);
        assertEquals(testopp.getSize(), 1.0, 0.1);
    }

    /**
     * Test case for isOffScreen method.
     * First if statement is true
     */
    @Test
    public void testIsOffScreen() {
        Opponent testopp = new LinearOpponent(true, 1, 1, -1, 0);
        assertTrue(testopp.isOffScreen());
    }
    
    /**
     * Test case for isOffScreen method.
     * Second if statement is true
     */
    @Test
    public void testIsOffScreen2() {
        Opponent testopp = new SinusOpponent(10, -1);
        assertTrue(testopp.isOffScreen());
    }
    
    /**
     * Test case for isOffScreen method.
     * None of the if statements are true
     */
    @Test
    public void testIsOffScreen1() {
        Opponent testopp = new LinearOpponent(true, 1, 1, 1, 0);
        assertFalse(testopp.isOffScreen());
    }
}
