package nl.github.martijn9612.fishy.position;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * Testclass for the Position class
 * 
 * @author Danique Lummen
 *
 */
public class PositionTest extends TestCase {
    private int posX;
    private int posY;
    private MousePosition mousePos;

    /**
     * Setup method for the tests
     */
    @Override
    protected void setUp() {
        posX = 8;
        posY = 76;
        mousePos = new MousePosition(posX, posY);
    }

    /**
     * Test get and set methods for positionX.
     */
    @Test
    public void testGetSetPositionX() {
        mousePos.setPositionX(0);
        assertEquals(mousePos.getPositionX(), 0);
    }

    /**
     * Test get and set methods for positionY.
     */
    @Test
    public void testGetSetPositionY() {
        mousePos.setPositionY(0);
        assertEquals(mousePos.getPositionY(), 0);
    }

    /**
     * Test method for the FlipXAxis method.
     */
    @Test
    public void testFlipXAxis() {
        posX = -posX + 650;
        mousePos.flipXAxis();
        assertEquals(mousePos.getPositionX(), posX);
    }

    /**
     * Test method for the FlipYAxis method.
     */
    @Test
    public void testFlipYAxis() {
        posY = -posY + 550;
        mousePos.flipYAxis();
        assertEquals(mousePos.getPositionY(), posY);
    }

}
