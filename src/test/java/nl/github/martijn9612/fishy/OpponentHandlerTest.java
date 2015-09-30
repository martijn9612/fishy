package nl.github.martijn9612.fishy;

import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.newdawn.slick.GameContainer;

import junit.framework.TestCase;
import nl.github.martijn9612.fishy.models.Opponent;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.opponents.LinearOpponent;

public class OpponentHandlerTest extends TestCase {

	private final GameContainer gc = mock(GameContainer.class);

	private final Player player = new Player(false);
	OpponentController testhandler = new OpponentController(false);

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
		Opponent testopp = new LinearOpponent(true, 1, 1, -1, 0, false);
		testhandler.addOpponent(testopp);
		assertEquals(testhandler.getOpponents().size(), 1);
		testhandler.updateOpponents(gc, 0, player);
		assertEquals(testhandler.getOpponents().size(), 0);
	}

	@Test
	public void testUpdateOpponents2() {
		Opponent testopp = new LinearOpponent(true, 1, 1, 1, 0, false);
		testhandler.addOpponent(testopp);
		assertEquals(testhandler.getOpponents().size(), 1);
		testhandler.updateOpponents(gc, 0, player);
		assertEquals(testhandler.getOpponents().size(), 1);
	}

	@Test
	public void testDestroy() {
		Opponent testopp = new LinearOpponent(true, 1, 1, 1, 0, false);
		int size = testhandler.getToRemove().size();
		testhandler.destroy(testopp);
		assertEquals(testhandler.getToRemove().size(), size + 1);
	}

	@Test
	public void testDestroyAllOpponents() {
		testhandler.spawnOpponents(player);
		testhandler.spawnOpponents(player);
		int size = testhandler.getToRemove().size();
		testhandler.destroyAllOpponents();
		assertEquals(testhandler.getToRemove().size(), size + 2);
	}

}
