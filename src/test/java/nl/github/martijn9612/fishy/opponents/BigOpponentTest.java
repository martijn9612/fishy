package nl.github.martijn9612.fishy.opponents;

import static org.mockito.Mockito.mock;
import junit.framework.TestCase;
import nl.github.martijn9612.fishy.OpponentController;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.models.Vector;
import nl.github.martijn9612.fishy.opponents.BigOpponent;

import org.junit.Test;
import org.newdawn.slick.GameContainer;

public class BigOpponentTest extends TestCase {

    private Player player;
    private OpponentController testhandler;
    private final GameContainer gc = mock(GameContainer.class);
    private Vector dimensions;
    private Vector velocity;
    private Vector position;
    private Vector acceleration;
    private BigOpponent test;
    
    @Override
    protected void setUp() {
        player = Player.createPlayer(false);
        testhandler = new OpponentController(false);
        position = new Vector(930, player.position.y - 350 / 2);
        velocity = new Vector(-1, 0);
        dimensions = new Vector(350 * 1.15f, 350);
        acceleration = new Vector(0, 0);
        test = new BigOpponent(dimensions, position, velocity, acceleration, false, player);
    }
    
    @Test
    public void testCreateBigOpponentPosition() {
        BigOpponent bigopp = test.createBigOpponent(player, false);
        assertEquals(bigopp.position, position);
    }
    
    @Test
    public void testCreateBigOpponentVelocity() {
        BigOpponent bigopp = test.createBigOpponent(player, false);
        assertEquals(bigopp.velocity, velocity);
    }
    
    @Test
    public void testCreateBigOpponentAcceleration() {
        BigOpponent bigopp = test.createBigOpponent(player, false);
        assertEquals(bigopp.acceleration, acceleration);
    }
    
    @Test
    public void testCreateBigOpponentDimensions() {
        BigOpponent bigopp = test.createBigOpponent(player, false);
        assertEquals(bigopp.dimensions, dimensions);
    }
    
    @Test
    public void testObjectLogic1() {
        test.changeTimeToLive(1);
        test.objectLogic(gc, 1);
        position.add(velocity);
        assertEquals(test.getTimeToLive(), 0);
    }
    
    @Test
    public void testObjectLogic2() {
        test.changeTimeToLive(20501);
        test.objectLogic(gc, 0);
        assertEquals(test.position.y, player.position.y - 350 / 2);
    }
    
    @Test
    public void testObjectLogic3() {
        test.changeTimeToLive(0);
        test.objectLogic(gc, 0);
        assertEquals(test.position, position);
    }
    
    @Test
    public void testObjectLogic4() {
        test.changeTimeToLive(21001);
        test.objectLogic(gc, 0);
        assertEquals(test.getTimeToLive(), 21001);
    }
    
    @Test
    public void testDestroy() {
        test.changeTimeToLive(100);
        test.destroy();
        assertEquals(test.getTimeToLive(), 0);
    }
    
    @Test
    public void testIsOffScreenFalse() {
        test.changeTimeToLive(1);
        assertFalse(test.isOffScreen());
    }
    
    @Test
    public void testIsOffScreenTrue() {
        test.changeTimeToLive(0);
        assertTrue(test.isOffScreen());
    }
}
