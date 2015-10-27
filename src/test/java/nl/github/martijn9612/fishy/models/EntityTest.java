package nl.github.martijn9612.fishy.models;

import org.junit.Test;

import junit.framework.TestCase;
import nl.github.martijn9612.fishy.opponents.LinearOpponent;

public class EntityTest extends TestCase {

    private Moveable opponentData;
    private LinearOpponent test;
    
    @Override
    protected void setUp() {
        opponentData = new Moveable();
        opponentData.setPosition(new Vector(1, 1));
        opponentData.setVelocity(new Vector(5, 0));
        opponentData.setDimensions(new Vector(1, 1));
        test = new LinearOpponent(opponentData, false);
    };
    
    @Test
    public void testLoadResources() {
        test.loadResources("enemy");
        assertFalse(test.hasOpenGL);
    }

    @Test
    public void testGetSize() {
        float size = (opponentData.getDimensions().x + opponentData.getDimensions().y) / 2;
        assertEquals(size, test.getSize(), 0.1);
    }

    @Test
    public void testIntersects() {
        assertTrue(test.intersects(test));
    }
    
    @Test
    public void testUpdateBoundingboxRadiiX() {
        float radiiX = (opponentData.getDimensions().x / 2);
        test.updateBoundingbox();
        assertEquals(test.getBoundingBox().getRadius1(), radiiX);
    }
    
    @Test
    public void testUpdateBoundingboxRadiiY() {
        float radiiY = (opponentData.getDimensions().y / 2);
        test.updateBoundingbox();
        assertEquals(test.getBoundingBox().getRadius2(), radiiY);
    }
    
    @Test
    public void testUpdateBoundingBoxPosition() {
        test.updateBoundingbox();
        assertEquals(test.getBoundingBox().getLocation(), opponentData.getPosition());
    }
}
