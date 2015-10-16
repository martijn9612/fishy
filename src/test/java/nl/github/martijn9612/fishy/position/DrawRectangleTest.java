package nl.github.martijn9612.fishy.position;

import org.junit.Test;

import junit.framework.TestCase;

public class DrawRectangleTest extends TestCase {

    private int width;
    private int height;
    private int posX;
    private int posY;
    private DrawRectangle test;
    
    @Override
    protected void setUp() {
        width = 1;
        height = 1;
        posX = 0;
        posY = 0;
        test = new DrawRectangle(posX, posY, width, height);
    }
    
    @Test
    public void testGetHeight() {
        assertEquals(test.getHeight(), 1);
    }
    
    @Test
    public void testGetWidht() {
        assertEquals(test.getWidth(), 1);
    }

    @Test
    public void testGetMouseRectangle() {
        MouseRectangle testrect = new MouseRectangle(posX, posY, width, height);
        testrect.flipYAxis();
        testrect.setPositionY(testrect.getPositionY() - height);
        assertEquals(test.getMouseRectangle().positionX, testrect.positionX);
    }
    
    @Test
    public void testToString() {
        String retstring = "Draw rectangle X: "+posX+", Y: "+posY+", width: "+width+", height: "+height;
        assertEquals(retstring, test.toString());
    }
}
