package nl.github.martijn9612.fishy.models;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.newdawn.slick.GameContainer;

public class BigOpponentIndicatorTest extends TestCase {

    private Player player;
    private Vector dimensions;
    private Vector position;
    private Vector velocity;
    private Vector acceleration;
    private BigOpponentIndicator test;
    private final GameContainer gc = mock(GameContainer.class);
    private List<Float> posHistory = new ArrayList<Float>();

    @Override
    protected void setUp() {
        player = Player.createPlayer(false);
        acceleration = new Vector(0, 0);
        velocity = new Vector(0, 0);
        dimensions = new Vector(115, 100);
        position = new Vector(580, player.position.y - (100 / 2));
        test = new BigOpponentIndicator(dimensions, position, velocity,
                acceleration, false, player);
    }

    @Test
    public void testCreateIndicatorPosition() {
        BigOpponentIndicator testindicator = test
                .createIndicator(player, false);
        assertEquals(testindicator.position, position);
    }

    @Test
    public void testCreateIndicatorVelocity() {
        BigOpponentIndicator testindicator = test
                .createIndicator(player, false);
        assertEquals(testindicator.velocity, velocity);
    }
    
    @Test
    public void testCreateIndicatorAcceleration() {
        BigOpponentIndicator testindicator = test
                .createIndicator(player, false);
        assertEquals(testindicator.acceleration, acceleration);
    }
    
    @Test
    public void testCreateIndicatorDimensions() {
        BigOpponentIndicator testindicator = test
                .createIndicator(player, false);
        assertEquals(testindicator.dimensions, dimensions);
    }
    
    @Test
    public void testObjectLogicVelocity() {
        float newIndicatorPosition = player.position.y - (100 / 2);
        posHistory.add(newIndicatorPosition);
        velocity.add(acceleration);
        test.objectLogic(gc, 0);
        assertEquals(test.velocity, velocity);
    }
    
    @Test
    public void testObjectLogicAcceleration() {
        float newIndicatorPosition = player.position.y - (100 / 2);
        posHistory.add(newIndicatorPosition);
        acceleration.scale(0);
        test.objectLogic(gc, 0);
        assertEquals(test.acceleration, acceleration);
    }
    
    @Test
    public void testObjectLogicPosition() {
        float newIndicatorPosition = player.position.y - (100 / 2);
        posHistory.add(newIndicatorPosition);
        position.add(velocity);
        test.objectLogic(gc, 0);
        assertEquals(test.position, position);
    }

}
