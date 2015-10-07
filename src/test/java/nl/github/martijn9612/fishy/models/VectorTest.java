package nl.github.martijn9612.fishy.models;

import junit.framework.TestCase;

import org.junit.Test;

public class VectorTest extends TestCase{

    private float x;
    private float y;
    private Vector test;
    
    @Override
    protected void setUp(){
        x = 1;
        y = 1;
        test = new Vector(x, y);
    }
    
    @Test
    public void testCopy() {
        Vector copytest = new Vector(1, 1);
        assertEquals(copytest, test.copy());
    }
    
    @Test
    public void testLimit() {
        float limitx = Math.max(Math.min(1, 3), -3);
        float limity = Math.max(Math.min(1, 3), -3);
        Vector limittest = new Vector(limitx, limity);
        test.limit(3);
        assertEquals(limittest, test);
    }
    
    @Test
    public void testCenterOfScreen() {
        Vector center = new Vector(650 / 2, 550 / 2);
        Vector centertest = Vector.centerOfScreen();
        assertEquals(center, centertest);
    }

    @Test
    public void testToString() {
        String teststring = "(" + Math.round(x)  + "," + Math.round(y) + ")";
        assertEquals(teststring, test.toString());
    }
    
}
