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
		Moveable opponentData = new Moveable();
		opponentData.dimensions = new Vector(size,size);
		opponentData.velocity = new Vector(1,0);
    	NonPlayer testopp = new LinearOpponent(opponentData, false);
		assertEquals(testopp.getSize(), size, 0.1);
	}

	/**
	 * Test case for testing whether the position of an opponent is on the screen.
	 */
	@Test
	public void testIsOpponentOnScreen() {
        Moveable opponentData = new Moveable();
        opponentData.position = new Vector(10, 10);
		opponentData.dimensions = new Vector(1, 1);
        NonPlayer testOpponent = new LinearOpponent(opponentData, false);
		assertFalse(testOpponent.isOffScreen());
		testOpponent.data.position = new Vector(10, Main.WINDOW_HEIGHT - 10);
		assertFalse(testOpponent.isOffScreen());
		testOpponent.data.position = new Vector(Main.WINDOW_WIDTH - 10, Main.WINDOW_HEIGHT - 10);
		assertFalse(testOpponent.isOffScreen());
		testOpponent.data.position = new Vector(Main.WINDOW_WIDTH - 10, 10);
		assertFalse(testOpponent.isOffScreen());
	}

	/**
	 * Test case for testing whether the position of an opponent is off the screen.
	 */
	@Test
	public void testIsOpponentOffScreen() {
		Moveable opponentData = new Moveable();
        opponentData.position = new Vector(-10, -10);
		opponentData.dimensions = new Vector(1, 1);
		NonPlayer testOpponent = new SinusOpponent(opponentData, false);
		assertTrue(testOpponent.isOffScreen());
		testOpponent.data.position = new Vector(-10, Main.WINDOW_HEIGHT + 10);
		assertTrue(testOpponent.isOffScreen());
		testOpponent.data.position = new Vector(Main.WINDOW_WIDTH + 10, Main.WINDOW_HEIGHT + 10);
		assertTrue(testOpponent.isOffScreen());
		testOpponent.data.position = new Vector(Main.WINDOW_WIDTH + 10, -10);
		assertTrue(testOpponent.isOffScreen());
	}
}
