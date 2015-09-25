package nl.github.martijn9612.fishy;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import static org.mockito.Mockito.mock;

public class OpponentHandlerTest {

    private final Graphics g = mock(Graphics.class);
    private final StateBasedGame sbg = mock(StateBasedGame.class);
    private final GameContainer gc = mock(GameContainer.class);

    private final Player player = new Player(false);
    OpponentHandler testhandler = new OpponentHandler(false);

    // Opponent testopp = new Opponent(true, 0, 0, 1.0, 0);

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
        Opponent testopp = new LinearOpponent(true, 1, 1, -1, 0);
        testhandler.addOpponent(testopp);
        assertEquals(testhandler.getOpponents().size(), 1);
        testhandler.updateOpponents(gc, 0, player);
        assertEquals(testhandler.getOpponents().size(), 0);
    }

    @Test
    public void testUpdateOpponents2() {
        Opponent testopp = new LinearOpponent(true, 1, 1, 1, 0);
        testhandler.addOpponent(testopp);
        assertEquals(testhandler.getOpponents().size(), 1);
        testhandler.updateOpponents(gc, 0, player);
        assertEquals(testhandler.getOpponents().size(), 1);
    }

    @Test
    public void testDestroy() {
        Opponent testopp = new LinearOpponent(true, 1, 1, 1, 0);
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
