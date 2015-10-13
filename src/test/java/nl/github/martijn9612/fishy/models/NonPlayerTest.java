package nl.github.martijn9612.fishy.models;

import org.junit.Test;

import junit.framework.TestCase;
import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.models.NonPlayer;
import nl.github.martijn9612.fishy.models.Vector;
import nl.github.martijn9612.fishy.opponents.LinearOpponent;
import nl.github.martijn9612.fishy.opponents.SinusOpponent;

public class NonPlayerTest extends TestCase {

	/**
	 * Test case for getSize method
	 */
	@Test
	public void testGetSize() {
		float size = 5.0f;
		Vector position = new Vector(0,0);
        Vector dimensions = new Vector(size,size);
        Vector velocity = new Vector(1,0);
        Vector acceleration = new Vector(0,0);
    	NonPlayer testopp = new LinearOpponent(dimensions, position, velocity, acceleration, false);
		assertEquals(testopp.getSize(), size, 0.1);
	}

	/**
	 * Test case for testing whether the position of an opponent is on the screen.
	 */
	@Test
	public void testIsOpponentOnScreen() {
		Vector velocity = new Vector(0,0);
		Vector position = new Vector(10,10);
        Vector dimensions = new Vector(1,1);
        Vector acceleration = new Vector(0,0);
        NonPlayer testopp = new LinearOpponent(dimensions, position, velocity, acceleration, false);
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
		Vector velocity = new Vector(0,0);
        Vector acceleration = new Vector(0,0);
		NonPlayer testopp = new SinusOpponent(dimensions, position, velocity, acceleration, false);
		assertTrue(testopp.isOffScreen());
		testopp.position = new Vector(-10, Main.WINDOW_HEIGHT + 10);
		assertTrue(testopp.isOffScreen());
		testopp.position = new Vector(Main.WINDOW_WIDTH + 10, Main.WINDOW_HEIGHT + 10);
		assertTrue(testopp.isOffScreen());
		testopp.position = new Vector(Main.WINDOW_WIDTH + 10, -10);
		assertTrue(testopp.isOffScreen());
	}
}