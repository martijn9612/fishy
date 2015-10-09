package nl.github.martijn9612.fishy.models;

import static org.mockito.Mockito.mock;
import junit.framework.TestCase;
import nl.github.martijn9612.fishy.OpponentController;
import nl.github.martijn9612.fishy.opponents.LinearOpponent;
import nl.github.martijn9612.fishy.models.Opponent;

import org.junit.Test;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Ellipse;

public class EntityTest extends TestCase{

    private Player player;
    private OpponentController testhandler;
    private final GameContainer gc = mock(GameContainer.class);
    private Vector dimensions;
    private Vector velocity;
    private Vector position;
    private Vector acceleration;
    private LinearOpponent test;
    
    @Override
    protected void setUp() {
        player = Player.createPlayer(false);
        testhandler = new OpponentController(false);
        position = new Vector(1, 1);
        velocity = new Vector(5, 0);
        dimensions = new Vector(1, 1);
        acceleration = new Vector(0, 0);
        test = new LinearOpponent(dimensions, position, velocity, acceleration, false);
    };
    
    @Test
    public void testLoadResources() {
        test.loadResources("enemy");
        assertFalse(test.hasOpenGL);
    }
    

    @Test
    public void testGetSize() {
        float size = (dimensions.x + dimensions.y) / 2;
        assertEquals(size, test.getSize(), 0.1);
    }

    @Test
    public void testIntersects() {
        assertTrue(test.intersects(test));
    }
    
    @Test
    public void testUpdateBoundingbox() {
        float radiiX = (dimensions.x / 2);
        test.updateBoundingbox();
        assertEquals(test.getBoundingBox().getRadius1(), radiiX);
    }
}
