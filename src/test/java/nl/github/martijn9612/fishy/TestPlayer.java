package nl.github.martijn9612.fishy;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;

public class TestPlayer extends TestCase {
	
	/**
	 * Tests if a created Player instance doesn't return an invalid Object;
	 */
	@Test
	public void testCreatePlayer() {
		Player player = new Player(false);
		Assert.assertTrue(player instanceof Player);
	}
	
	/**
	 * Tests whether the Player is able to move to the left.
	 */
	@Test
	public void testMovePlayerLeft() {
		Player player = new Player(false);
		int startXValue = player.getX();
		int startYValue = player.getY();
		player.left(1);
		Assert.assertEquals(startYValue, player.getY());
		Assert.assertEquals(startXValue - 1, player.getX());
	}
	
	/**
	 * Tests whether the Player is able to move to the right.
	 */
	@Test
	public void testMovePlayerRight() {
		Player player = new Player(false);
		int startXValue = player.getX();
		int startYValue = player.getY();
		player.right(1);
		Assert.assertEquals(startYValue, player.getY());
		Assert.assertEquals(startXValue + 1, player.getX());
	}
	
	/**
	 * Tests whether the Player is able to move up.
	 */
	@Test
	public void testMovePlayerUp() {
		Player player = new Player(false);
		int startXValue = player.getX();
		int startYValue = player.getY();
		player.up(1);
		Assert.assertEquals(startYValue - 1, player.getY()); // Moving up decreases the y-axis, because (0,0) is at the upper left corner.
		Assert.assertEquals(startXValue, player.getX());
	}
	
	/**
	 * Tests whether the Player is able to move down.
	 */
	@Test
	public void testMovePlayerDown() {
		Player player = new Player(false);
		int startXValue = player.getX();
		int startYValue = player.getY();
		player.down(1);
		Assert.assertEquals(startYValue + 1, player.getY()); // Moving down increases the y-axis, because (0,0) is at the upper left corner.
		Assert.assertEquals(startXValue, player.getX());
	}
	
}
