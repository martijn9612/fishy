package nl.github.martijn9612.fishy;

import org.junit.Test;

import junit.framework.TestCase;
import nl.github.martijn9612.fishy.models.Opponent;
import nl.github.martijn9612.fishy.models.Vector;
import nl.github.martijn9612.fishy.opponents.LinearOpponent;
import nl.github.martijn9612.fishy.opponents.SinusOpponent;

public class OpponentTest extends TestCase {

	/**
	 * Test case for getSize method
	 */
	@Test
	public void testGetSize() {
		float size = 5.0f;
		Vector position = new Vector(0,0);
        Vector dimensions = new Vector(size,size);
        Vector speed = new Vector(1,0);
    	Opponent testopp = new LinearOpponent(position, dimensions, speed, false);
		assertEquals(testopp.getSize(), size, 0.1);
	}

	/**
	 * Test case for testing whether the position of an opponent is on the screen.
	 */
	@Test
	public void testIsOpponentOnScreen() {
		Vector speed = new Vector(0,0);
		Vector position = new Vector(10,10);
        Vector dimensions = new Vector(1,1);
        Opponent testopp = new LinearOpponent(position, dimensions, speed, false);
		assertFalse(testopp.isOffScreen());
		testopp.position = new Vector(10, Main.WINDOW_HEIGHT - 10);
		assertFalse(testopp.isOffScreen());
		testopp.position = new Vector(Main.WINDOW_WIDTH - 10, Main.WINDOW_HEIGHT - 10);
		assertFalse(testopp.isOffScreen());
		testopp.position = new Vector(Main.WINDOW_WIDTH - 10, 10);
		assertFalse(testopp.isOffScreen());
	}

	/**
	 * Test case for testing whether the position of an opponent is off the screen.
	 */
	@Test
	public void testIsOpponentOffScreen() {
		Vector dimensions = new Vector(1,1);
		Vector position = new Vector(-10,-10);
		Opponent testopp = new SinusOpponent(position, dimensions, false);
		assertTrue(testopp.isOffScreen());
		testopp.position = new Vector(-10, Main.WINDOW_HEIGHT + 10);
		assertTrue(testopp.isOffScreen());
		testopp.position = new Vector(Main.WINDOW_WIDTH + 10, Main.WINDOW_HEIGHT + 10);
		assertTrue(testopp.isOffScreen());
		testopp.position = new Vector(Main.WINDOW_WIDTH + 10, -10);
		assertTrue(testopp.isOffScreen());
	}
}
