package nl.github.martijn9612.fishy;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import junit.framework.TestCase;
import nl.github.martijn9612.fishy.models.Moveable;
import nl.github.martijn9612.fishy.models.NonPlayer;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.models.Vector;
import nl.github.martijn9612.fishy.opponents.LinearOpponent;

/**
 * Test class for OpponentController.
 * @author Danique Lummen
 */
public class OpponentControllerTest extends TestCase {

    private Player player;
    private OpponentController opponentController;
    private final GameContainer gc = mock(GameContainer.class);
    private final StateBasedGame sbg = mock(StateBasedGame.class);
    private Moveable opponentData;

    /**
     * Setup before execution.
     */
    @Override
    protected void setUp() {
        player = Player.createPlayer(false);
        opponentController = new OpponentController(false);
        opponentData = new Moveable();
        opponentData.setPosition(new Vector(1, 1));
        opponentData.setVelocity(new Vector(5, 0));
    };

    /**
     * Testcase for newOpponent.
     */
    @Test
    public void testNewOpponent() {
        int size = opponentController.getOpponents().size();
        opponentController.spawnOpponents(player);
        assertEquals(opponentController.getOpponents().size(), size + 1);
    }

    /**
     * Testcase for updateOpponents.
     * opponent.isOffScreen is false.
     */
    @Test
    public void testUpdateOpponents1() {
        opponentData.getVelocity().x = -5;
        NonPlayer testopp = new LinearOpponent(opponentData, false);
        opponentController.addOpponent(testopp);
        assertEquals(opponentController.getOpponents().size(), 1);
        opponentController.updateOpponents(gc, 0);
        assertEquals(opponentController.getOpponents().size(), 0);
    }

    /**
     * Testcase for updateOpponents.
     * opponent.isOffScreen is true.
     */
    @Test
    public void testUpdateOpponents2() {
        NonPlayer testopp = new LinearOpponent(opponentData, false);
        opponentController.addOpponent(testopp);
        assertEquals(opponentController.getOpponents().size(), 1);
        opponentController.updateOpponents(gc, 0);
        assertEquals(opponentController.getOpponents().size(), 1);
    }

    /**
     * Testcase for remove.
     */
    @Test
    public void testRemove() {
        NonPlayer testopp = new LinearOpponent(opponentData, false);
        int size = opponentController.getToRemove().size();
        opponentController.remove(testopp);
        assertEquals(opponentController.getToRemove().size(), size + 1);
    }

    /**
     * Testcase for removeAllOpponents.
     */
    @Test
    public void testRemoveAllOpponents() {
        opponentController.spawnOpponents(player);
        opponentController.spawnOpponents(player);
        int size = opponentController.getToRemove().size();
        opponentController.removeAllOpponents();
        assertEquals(opponentController.getToRemove().size(), size + 2);
    }

    /**
     * Testcase for spawnOpponents.
     * opponents.size() < MAX_OPPONENTS.
     */
    @Test
    public void testSpawnOpponents1() {
        NonPlayer testopp = new LinearOpponent(opponentData, false);
        for (int i = 0; i < 21; i++) {
            opponentController.addOpponent(testopp);
        }

        opponentController.spawnOpponents(player);
        assertTrue(opponentController.getOpponents().size() > 20);
    }

    
    /**
     * Testcase for renderOpponents.
     */
    @Test
    public void testRenderOpponents() {
        Graphics g = mock(Graphics.class);
        NonPlayer testopp = new LinearOpponent(opponentData, false);
        opponentController.addOpponent(testopp);
        NonPlayer opponents = mock(NonPlayer.class);
        Mockito.doNothing().when(opponents).renderObject(g);
        opponentController.renderOpponents(g);
        assertEquals(opponentController.getOpponents().size(), 1);
    }
    
    /**
     * Test for collide.
     * opponent.intersects(player) is false.
     */
    @Test
    public void testCollide1() {
        NonPlayer opponent = mock(NonPlayer.class);
        opponentController.addOpponent(opponent);
        when(opponent.intersects(player)).thenReturn(false);
        opponentController.collide(player, sbg);
        assertTrue(opponentController.getOpponents().contains(opponent));
    }
    

}
