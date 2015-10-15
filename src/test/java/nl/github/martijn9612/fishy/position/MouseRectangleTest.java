package nl.github.martijn9612.fishy.position;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Test class for MouseRectangle class.
 * @author Danique Lummen
 * 
 * The method MouseRectangle(int width, int height) 
 * is not tested because the x and y positions
 * are determined by getting the x and y position 
 * of the mouse in OpenGL context.
 */
public class MouseRectangleTest extends TestCase {

    private int posX;
    private int posY;
    private int height;
    private int width;
    private MouseRectangle test;
    
    /**
     * SetUp for the tests.
     */
    @Override
    protected void setUp() {
        posX = 0;
        posY = 0;
        height = 1;
        width = 1;
        test = new MouseRectangle(posX, posY, width, height);
        
    }
    
    /**
     * Testcase for the getHeight method.
     */
    @Test
    public void testGetHeight() {
        assertEquals(test.getHeight(), height);
    }
    
    /**
     * Testcase for the getWidth method.
     */
    @Test
    public void testGetWidth() {
        assertEquals(test.getWidth(), width);
    }
    
    /**
     * Testcase for the getDrawRectangle method.
     */
    @Test
    public void testGetDrawRectangle() {
        DrawRectangle testrect = new DrawRectangle(posX, posY, width, height);
        testrect.flipYAxis();
        testrect.setPositionY(testrect.getPositionY() + height);
        assertEquals(test.getDrawRectangle().toString(), testrect.toString());
    }
    
    /**
     * Testcase for the toString method.
     */
    @Test
    public void testToString() {
        String retstring = "Mouse rectangle X: "+posX+", Y: "+posY+", width: "+width+", height: "+height;
        assertEquals(test.toString(), retstring);
    }

}
