package nl.github.martijn9612.fishy;

import org.junit.Test;

import junit.framework.TestCase;
import nl.github.martijn9612.fishy.models.Opponent;
import nl.github.martijn9612.fishy.opponents.LinearOpponent;
import nl.github.martijn9612.fishy.opponents.SinusOpponent;

public class OpponentTest extends TestCase {

	/**
	 * Test case for getSize method
	 */
	@Test
	public void testGetSize() {
		Opponent testopp = new LinearOpponent(true, 0, 0, 1, 0, false);
		assertEquals(testopp.getSize(), 1.0, 0.1);
	}

	/**
	 * Test case for isOffScreen method. First if statement is true
	 */
	@Test
	public void testIsOffScreen() {
		Opponent testopp = new LinearOpponent(true, 1, 1, -1, 0, false);
		assertTrue(testopp.isOffScreen());
	}

	/**
	 * Test case for isOffScreen method. Second if statement is true
	 */
	@Test
	public void testIsOffScreen2() {
		Opponent testopp = new SinusOpponent(10, -1, false);
		assertTrue(testopp.isOffScreen());
	}

	/**
	 * Test case for isOffScreen method. None of the if statements are true
	 */
	@Test
	public void testIsOffScreen1() {
		Opponent testopp = new LinearOpponent(true, 1, 1, 1, 0, false);
		assertFalse(testopp.isOffScreen());
	}
}
