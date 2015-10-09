package nl.github.martijn9612.fishy;

import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.newdawn.slick.GameContainer;

import junit.framework.TestCase;
import nl.github.martijn9612.fishy.models.NonPlayer;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.models.Vector;
import nl.github.martijn9612.fishy.opponents.LinearOpponent;

public class OpponentControllerTest extends TestCase {

	private Player player;
	private OpponentController opponentController;
	private final GameContainer gc = mock(GameContainer.class);
	private Vector dimensions;
	private Vector velocity;
	private Vector position;
	private Vector acceleration;
	
	@Override
	protected void setUp() {
		player = Player.createPlayer(false);
		opponentController = new OpponentController(false);
		position = new Vector(1, 1);
		velocity = new Vector(5, 0);
		dimensions = new Vector(0, 0);
		acceleration = new Vector(0, 0);
	};
	
	@Test
	public void testNewLinearOpponent() {
		int size = opponentController.getOpponents().size();
		opponentController.spawnOpponents(player);
		assertEquals(opponentController.getOpponents().size(), size + 1);
	}

	@Test
	public void testNewSinusOpponent() {

	}

	@Test
	public void testUpdateOpponents1() {
		velocity.x = -5;
		NonPlayer testopp = new LinearOpponent(dimensions, position, velocity, acceleration, false);
		opponentController.addOpponent(testopp);
		assertEquals(opponentController.getOpponents().size(), 1);
		opponentController.updateOpponents(gc, 0);
		assertEquals(opponentController.getOpponents().size(), 0);
	}

	@Test
	public void testUpdateOpponents2() {
		NonPlayer testopp = new LinearOpponent(dimensions, position, velocity, acceleration, false);
		opponentController.addOpponent(testopp);
		assertEquals(opponentController.getOpponents().size(), 1);
		opponentController.updateOpponents(gc, 0);
		assertEquals(opponentController.getOpponents().size(), 1);
	}

	@Test
	public void testDestroy() {
		NonPlayer testopp = new LinearOpponent(dimensions, position, velocity, acceleration, false);
		int size = opponentController.getToRemove().size();
		opponentController.remove(testopp);
		assertEquals(opponentController.getToRemove().size(), size + 1);
	}

	@Test
	public void testDestroyAllOpponents() {
		opponentController.spawnOpponents(player);
		opponentController.spawnOpponents(player);
		int size = opponentController.getToRemove().size();
		opponentController.removeAllOpponents();
		assertEquals(opponentController.getToRemove().size(), size + 2);
	}

}
