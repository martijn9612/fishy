package nl.github.martijn9612.fishy.opponents;

import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import junit.framework.TestCase;
import nl.github.martijn9612.fishy.models.Moveable;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.models.Vector;

/**
 * Testclass for BigOpponent.
 * @author Danique Lummen
 */
public class BigOpponentTest extends TestCase {

    private Player player;
    private final GameContainer gc = mock(GameContainer.class);
    private final Graphics g = mock(Graphics.class);
    private Moveable opponentData;
    private BigOpponent test;
    
    /**
     * Setup for the testcases.
     */
    @Override
    protected void setUp() {
        player = Player.createPlayer(false);
        opponentData = new Moveable();
        opponentData.position = new Vector(930, player.data.position.y - 350 / 2);
        opponentData.velocity = new Vector(-1, 0);
        opponentData.dimensions = new Vector(350 * 1.15f, 350);
        test = new BigOpponent(opponentData, false, player.data);
    }
    
    /**
     * Testcase for createBigOpponent.
     * Test position.
     */
    @Test
    public void testCreateBigOpponentPosition() {
        BigOpponent bigopp = BigOpponent.createBigOpponent(player.data, false);
        assertEquals(bigopp.data.position, opponentData.position);
    }
    
    /**
     * Testcase for createBigOpponent.
     * Test velocity.
     */
    @Test
    public void testCreateBigOpponentVelocity() {
    	BigOpponent bigopp = BigOpponent.createBigOpponent(player.data, false);
        assertEquals(bigopp.data.velocity, opponentData.velocity);
    }
    
    /**
     * Testcase for createBigOpponent.
     * Test acceleration.
     */
    @Test
    public void testCreateBigOpponentAcceleration() {
    	BigOpponent bigopp = BigOpponent.createBigOpponent(player.data, false);
        assertEquals(bigopp.data.acceleration, opponentData.acceleration);
    }
    
    /**
     * Testcase for createBigOpponent.
     * Test dimensions.
     */
    @Test
    public void testCreateBigOpponentDimensions() {
    	BigOpponent bigopp = BigOpponent.createBigOpponent(player.data, false);
        assertEquals(bigopp.data.dimensions, opponentData.dimensions);
    }
    
    /**
     * Testcase for objectLogic.
     * timeToLive > 0
     */
    @Test
    public void testObjectLogic1() {
        test.changeTimeToLive(1);
        test.objectLogic(gc, 1);
        opponentData.position.add(opponentData.velocity);
        assertEquals(test.getTimeToLive(), 0);
    }
    
    /**
     * Testcase for objectLogic.
     * timeToLive > INDICATOR_REMOVED_AT
     */
    @Test
    public void testObjectLogic2() {
        test.changeTimeToLive(20501);
        test.objectLogic(gc, 0);
        assertEquals(test.data.position.y, player.data.position.y - 350 / 2);
    }
    
    /**
     * Testcase for objectLogic.
     * timeToLive < INDICATOR_MOVES_AT
     */
    @Test
    public void testObjectLogic3() {
        test.changeTimeToLive(0);
        test.objectLogic(gc, 0);
        assertEquals(test.data.position, opponentData.position);
    }
    
    /**
     * Testcase for objectLogic.
     */
    @Test
    public void testObjectLogic4() {
        test.changeTimeToLive(21001);
        test.objectLogic(gc, 0);
        assertEquals(test.getTimeToLive(), 21001);
    }
    
    /**
     * Testcase for destroy.
     */
    @Test
    public void testDestroy() {
        test.changeTimeToLive(100);
        test.destroy();
        assertEquals(test.getTimeToLive(), 0);
    }
    
    /**
     * Testcase for IsOffScreen(), result is false.
     */
    @Test
    public void testIsOffScreenFalse() {
        test.changeTimeToLive(1);
        assertFalse(test.isOffScreen());
    }
    
    /**
     * Testcase for isOffScreen(), result is true.
     */
    @Test
    public void testIsOffScreenTrue() {
        test.changeTimeToLive(0);
        assertTrue(test.isOffScreen());
    }
    
    /**
     * Testcase for renderObject.
     */
    @Test
    public void testRenderObject() {
        test.renderObject(g);
        assertTrue(test.getTimeToLive() > 20500);
    }
    
    /**
     * Testcase for renderObject.
     */
    @Test
    public void testRenderObject2() {
        test.changeTimeToLive(0);
        test.renderObject(g);
        assertTrue(test.getTimeToLive() < 20500);
    }
    
    
}
