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
        opponentData.position = new Vector(1, 1);
        opponentData.velocity = new Vector(5, 0);
        opponentData.dimensions = new Vector(1, 1);
        test = new LinearOpponent(opponentData, false);
    };
    
    @Test
    public void testLoadResources() {
        test.loadResources("enemy");
        assertFalse(test.hasOpenGL);
    }

    @Test
    public void testGetSize() {
        float size = (opponentData.dimensions.x + opponentData.dimensions.y) / 2;
        assertEquals(size, test.getSize(), 0.1);
    }

    @Test
    public void testIntersects() {
        assertTrue(test.intersects(test));
    }
    
    @Test
    public void testUpdateBoundingboxRadiiX() {
        float radiiX = (opponentData.dimensions.x / 2);
        test.updateBoundingbox();
        assertEquals(test.getBoundingBox().getRadius1(), radiiX);
    }
    
    @Test
    public void testUpdateBoundingboxRadiiY() {
        float radiiY = (opponentData.dimensions.y / 2);
        test.updateBoundingbox();
        assertEquals(test.getBoundingBox().getRadius2(), radiiY);
    }
    
    @Test
    public void testUpdateBoundingBoxPosition() {
        test.updateBoundingbox();
        assertEquals(test.getBoundingBox().getLocation(), opponentData.position);
    }
}
