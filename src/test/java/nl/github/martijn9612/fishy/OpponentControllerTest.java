package nl.github.martijn9612.fishy;

import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.newdawn.slick.GameContainer;

import junit.framework.TestCase;
import nl.github.martijn9612.fishy.models.Moveable;
import nl.github.martijn9612.fishy.models.NonPlayer;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.models.Vector;
import nl.github.martijn9612.fishy.opponents.LinearOpponent;

public class OpponentControllerTest extends TestCase {

	private Player player;
	private OpponentController opponentController;
	private final GameContainer gc = mock(GameContainer.class);
	private Moveable opponentData;
	
	@Override
	protected void setUp() {
		player = Player.createPlayer(false);
		opponentController = new OpponentController(false);
		opponentData = new Moveable();
		opponentData.position = new Vector(1, 1);
		opponentData.velocity = new Vector(5, 0);
	};
	
	@Test
	public void testNewOpponent() {
		int size = opponentController.getOpponents().size();
		opponentController.spawnOpponents(player);
		assertEquals(opponentController.getOpponents().size(), size + 1);
	}

	@Test
	public void testUpdateOpponents1() {
		opponentData.velocity.x = -5;
		NonPlayer testopp = new LinearOpponent(opponentData, false);
		opponentController.addOpponent(testopp);
		assertEquals(opponentController.getOpponents().size(), 1);
		opponentController.updateOpponents(gc, 0);
		assertEquals(opponentController.getOpponents().size(), 0);
	}

	@Test
	public void testUpdateOpponents2() {
		NonPlayer testopp = new LinearOpponent(opponentData, false);
		opponentController.addOpponent(testopp);
		assertEquals(opponentController.getOpponents().size(), 1);
		opponentController.updateOpponents(gc, 0);
		assertEquals(opponentController.getOpponents().size(), 1);
	}

	@Test
	public void testDestroy() {
		NonPlayer testopp = new LinearOpponent(opponentData, false);
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
