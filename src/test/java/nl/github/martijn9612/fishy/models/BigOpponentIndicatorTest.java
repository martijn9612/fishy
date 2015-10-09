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
        position = new Vector(580, player.position.y - (100/2));
        test = new BigOpponentIndicator(dimensions, position, velocity, acceleration, false, player);
    }
    
    
    @Test
    public void testCreateIndicator() {
        BigOpponentIndicator testindicator = test.createIndicator(player, false);
        assertEquals(testindicator.velocity, velocity);
        assertEquals(testindicator.position, position);
        assertEquals(testindicator.acceleration, acceleration);
        assertEquals(testindicator.dimensions, dimensions);
     }
    @Test
    public void testObjectLogic() {
        float newIndicatorPosition = player.position.y - (100 / 2);
        posHistory.add(newIndicatorPosition);
        velocity.add(acceleration);
        position.add(velocity);
        acceleration.scale(0);
        test.objectLogic(gc, 0);
        assertEquals(test.velocity, velocity);
        assertEquals(test.acceleration, acceleration);
        assertEquals(test.position, position);
    }
    
}
