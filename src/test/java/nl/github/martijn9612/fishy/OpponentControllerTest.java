package nl.github.martijn9612.fishy;

import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.newdawn.slick.GameContainer;

import junit.framework.TestCase;
import nl.github.martijn9612.fishy.models.Opponent;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.models.Vector;
import nl.github.martijn9612.fishy.opponents.LinearOpponent;

public class OpponentControllerTest extends TestCase {

	private Player player;
	private OpponentController testhandler;
	private final GameContainer gc = mock(GameContainer.class);
	private Vector dimensions;
	private Vector velocity;
	private Vector position;
	private Vector acceleration;
	
	@Override
	protected void setUp() {
		player = Player.createPlayer(false);
		testhandler = new OpponentController(false);
		position = new Vector(1, 1);
		velocity = new Vector(5, 0);
		dimensions = new Vector(0, 0);
		acceleration = new Vector(0, 0);
	};
	
	@Test
	public void testNewLinearOpponent() {
		int size = testhandler.getOpponents().size();
		testhandler.spawnOpponents(player);
		assertEquals(testhandler.getOpponents().size(), size + 1);
	}

	@Test
	public void testNewSinusOpponent() {

	}

	@Test
	public void testUpdateOpponents1() {
		velocity.x = -5;
		Opponent testopp = new LinearOpponent(dimensions, position, velocity, acceleration, false);
		testhandler.addOpponent(testopp);
		assertEquals(testhandler.getOpponents().size(), 1);
		testhandler.updateOpponents(gc, 0);
		assertEquals(testhandler.getOpponents().size(), 0);
	}

	@Test
	public void testUpdateOpponents2() {
		Opponent testopp = new LinearOpponent(dimensions, position, velocity, acceleration, false);
		testhandler.addOpponent(testopp);
		assertEquals(testhandler.getOpponents().size(), 1);
		testhandler.updateOpponents(gc, 0);
		assertEquals(testhandler.getOpponents().size(), 1);
	}

	@Test
	public void testDestroy() {
		Opponent testopp = new LinearOpponent(dimensions, position, velocity, acceleration, false);
		int size = testhandler.getToRemove().size();
		testhandler.remove(testopp);
		assertEquals(testhandler.getToRemove().size(), size + 1);
	}

	@Test
	public void testDestroyAllOpponents() {
		testhandler.spawnOpponents(player);
		testhandler.spawnOpponents(player);
		int size = testhandler.getToRemove().size();
		testhandler.removeAllOpponents();
		assertEquals(testhandler.getToRemove().size(), size + 2);
	}

}
