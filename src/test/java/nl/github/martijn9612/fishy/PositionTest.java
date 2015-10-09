package nl.github.martijn9612.fishy;

import org.junit.Test;

import junit.framework.TestCase;
import nl.github.martijn9612.fishy.position.DrawPosition;
import nl.github.martijn9612.fishy.position.MousePosition;
import nl.github.martijn9612.fishy.position.MouseRectangle;

public class PositionTest extends TestCase {
	private static int POSITION_X = 8;
	private static int POSITION_Y = 76;

	/**
	 * Test the MousePosition class by verifying if the correct values were
	 * inserted in the class constructor and the conversion to the draw
	 * coordinate system works.
	 */
	@Test
	public void testMousePosition() {
		MousePosition mousePos = new MousePosition(POSITION_X, POSITION_Y);
		assertEquals(POSITION_X, mousePos.getPositionX());
		assertEquals(POSITION_Y, mousePos.getPositionY());
		DrawPosition drawPos = mousePos.getDrawPosition();
		assertEquals(POSITION_X, drawPos.getPositionX());
		assertEquals(Main.WINDOW_HEIGHT - POSITION_Y, drawPos.getPositionY());
	}

	/**
	 * Test the DrawPosition class by verifying if the correct values were
	 * inserted in the class constructor and the conversion to the mouse
	 * coordinate system works.
	 */
	@Test
	public void testDrawPosition() {
		DrawPosition drawPos = new DrawPosition(POSITION_X, POSITION_Y);
		assertEquals(POSITION_X, drawPos.getPositionX());
		assertEquals(POSITION_Y, drawPos.getPositionY());
		MousePosition mousePos = drawPos.getMousePosition();
		assertEquals(POSITION_X, mousePos.getPositionX());
		assertEquals(Main.WINDOW_HEIGHT - POSITION_Y, mousePos.getPositionY());
	}

	/**
	 * Test whether the inBetweenBounds() method of the MousePosition class
	 * works as intended. The expected result is that these methods return if
	 * the position of the MousePosition is within the given bounds.
	 */
	@Test
	public void testIsInBetweenBounds() {
		MousePosition mousePos = new MousePosition(POSITION_X, POSITION_Y);
		assertTrue(mousePos.isInBetweenX(0, 100));
		assertFalse(mousePos.isInBetweenX(100, 200));
		assertTrue(mousePos.isInBetweenY(0, 100));
		assertFalse(mousePos.isInBetweenY(100, 200));
	}

	/**
	 * Test whether the isInRectangle() method is correct. This method should
	 * return whether the given mouse position is within the defined rectangle
	 * (mouse coordinates).
	 */
	@Test
	public void testIsInRectangle() {
		MousePosition mousePos = new MousePosition(POSITION_X, POSITION_Y);
		MouseRectangle rectangle1 = new MouseRectangle(0, 0, 100, 100);
		assertTrue(mousePos.isInRectangle(rectangle1));
		MouseRectangle rectangle2 = new MouseRectangle(100, 0, 100, 100);
		assertFalse(mousePos.isInRectangle(rectangle2));
		MouseRectangle rectangle3 = new MouseRectangle(0, 0, 50, 50);
		assertFalse(mousePos.isInRectangle(rectangle3));
		MouseRectangle rectangle4 = new MouseRectangle(5, 5, 50, 100);
		assertTrue(mousePos.isInRectangle(rectangle4));
	}
}
