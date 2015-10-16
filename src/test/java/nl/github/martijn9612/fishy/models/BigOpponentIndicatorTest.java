package nl.github.martijn9612.fishy.models;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.newdawn.slick.GameContainer;

import junit.framework.TestCase;

public class BigOpponentIndicatorTest extends TestCase {

    private Player player;
    private Moveable opponentData;
    private BigOpponentIndicator testIndicator;
    private final GameContainer gc = mock(GameContainer.class);
    private List<Float> posHistory = new ArrayList<Float>();

    @Override
    protected void setUp() {
        player = Player.createPlayer(false);
        opponentData = new Moveable();
        opponentData.velocity = new Vector(115, 100);
        opponentData.position = new Vector(580, player.data.position.y - (100 / 2));
        testIndicator = new BigOpponentIndicator(opponentData, false, player.data);
    }

    @Test
    public void testCreateIndicatorMovement() {
        assertEquals(testIndicator.data.velocity, opponentData.velocity);
        assertEquals(testIndicator.data.acceleration, opponentData.acceleration);
        assertEquals(testIndicator.data.dimensions, opponentData.dimensions);
        assertEquals(testIndicator.data.position, opponentData.position);
    }
    
    @Test
    public void testObjectLogicVelocity() {
        float newIndicatorPosition = player.data.position.y - (100 / 2);
        posHistory.add(newIndicatorPosition);
        opponentData.velocity.add(opponentData.acceleration);
        testIndicator.objectLogic(gc, 0);
        assertEquals(testIndicator.data.velocity, opponentData.velocity);
    }
    
    @Test
    public void testObjectLogicAcceleration() {
        float newIndicatorPosition = player.data.position.y - (100 / 2);
        posHistory.add(newIndicatorPosition);
        opponentData.acceleration.scale(0);
        testIndicator.objectLogic(gc, 0);
        assertEquals(testIndicator.data.acceleration, opponentData.acceleration);
    }
    
    @Test
    public void testObjectLogicPosition() {
        float newIndicatorPosition = player.data.position.y - (100 / 2);
        posHistory.add(newIndicatorPosition);
        opponentData.position.add(opponentData.velocity);
        testIndicator.objectLogic(gc, 0);
        assertEquals(testIndicator.data.position, opponentData.position);
    }

}
