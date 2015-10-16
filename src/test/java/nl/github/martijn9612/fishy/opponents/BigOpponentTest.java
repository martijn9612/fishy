package nl.github.martijn9612.fishy.opponents;

import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.newdawn.slick.GameContainer;

import junit.framework.TestCase;
import nl.github.martijn9612.fishy.models.Moveable;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.models.Vector;

public class BigOpponentTest extends TestCase {

    private Player player;
    private final GameContainer gc = mock(GameContainer.class);
    private Moveable opponentData;
    private BigOpponent test;
    
    @Override
    protected void setUp() {
        player = Player.createPlayer(false);
        opponentData = new Moveable();
        opponentData.position = new Vector(930, player.data.position.y - 350 / 2);
        opponentData.velocity = new Vector(-1, 0);
        opponentData.dimensions = new Vector(350 * 1.15f, 350);
        test = new BigOpponent(opponentData, false, player.data);
    }
    
    @Test
    public void testCreateBigOpponentPosition() {
        BigOpponent bigopp = BigOpponent.createBigOpponent(player.data, false);
        assertEquals(bigopp.data.position, opponentData.position);
    }
    
    @Test
    public void testCreateBigOpponentVelocity() {
    	BigOpponent bigopp = BigOpponent.createBigOpponent(player.data, false);
        assertEquals(bigopp.data.velocity, opponentData.velocity);
    }
    
    @Test
    public void testCreateBigOpponentAcceleration() {
    	BigOpponent bigopp = BigOpponent.createBigOpponent(player.data, false);
        assertEquals(bigopp.data.acceleration, opponentData.acceleration);
    }
    
    @Test
    public void testCreateBigOpponentDimensions() {
    	BigOpponent bigopp = BigOpponent.createBigOpponent(player.data, false);
        assertEquals(bigopp.data.dimensions, opponentData.dimensions);
    }
    
    @Test
    public void testObjectLogic1() {
        test.changeTimeToLive(1);
        test.objectLogic(gc, 1);
        opponentData.position.add(opponentData.velocity);
        assertEquals(test.getTimeToLive(), 0);
    }
    
    @Test
    public void testObjectLogic2() {
        test.changeTimeToLive(20501);
        test.objectLogic(gc, 0);
        assertEquals(test.data.position.y, player.data.position.y - 350 / 2);
    }
    
    @Test
    public void testObjectLogic3() {
        test.changeTimeToLive(0);
        test.objectLogic(gc, 0);
        assertEquals(test.data.position, opponentData.position);
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
