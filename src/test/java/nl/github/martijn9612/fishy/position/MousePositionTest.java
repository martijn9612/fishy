package nl.github.martijn9612.fishy.position;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;

import org.junit.Test;
import org.lwjgl.input.Mouse;

/**
 * Test class for MousePosition.java.
 * @author Danique Lummen
 *
 */

public class MousePositionTest extends TestCase {

    private int posX;
    private int posY;
    private MousePosition test;

    
    /**
     * Setup for MousePositionTest
     */
    @Override
    protected void setUp() {
        posX = 1;
        posY = 1;
        test = new MousePosition(posX, posY);
    }
    
    /**
     * Testcase for IsInBetweenX.
     * True & True
     */
    @Test
    public void testIsInBetweenX1() {
        assertTrue(test.isInBetweenX(0, 2));
    }
    
    /**
     * Testcase for IsInBetweenX.
     * False & True
     */
    @Test
    public void testIsInBetweenX2() {
        assertFalse(test.isInBetweenX(1, 2));
    }
    
    /**
     * Testcase for IsInBetweenX.
     * True & False
     */
    @Test
    public void testIsInBetweenX3() {
        assertFalse(test.isInBetweenX(0, 1));
    }
    
    /**
     * Testcase for IsInBetweenX.
     * False & False
     */
    @Test
    public void testIsInBetweenX4() {
        assertFalse(test.isInBetweenX(1, 1));
    }
    
    /**
     * Testcase for IsInBetweenY.
     * True & True
     */
    @Test
    public void testIsInBetweenY1() {
        assertTrue(test.isInBetweenY(0, 2));
    }
    
    /**
     * Testcase for IsInBetweenY.
     * False & True
     */
    @Test
    public void testIsInBetweenY2() {
        assertFalse(test.isInBetweenY(1, 2));
    }
    
    /**
     * Testcase for IsInBetweenY.
     * True & False
     */
    @Test
    public void testIsInBetweenY3() {
        assertFalse(test.isInBetweenY(0, 1));
    }
    
    /**
     * Testcase for IsInBetweenY.
     * False & False
     */
    @Test
    public void testIsInBetweenY4() {
        assertFalse(test.isInBetweenY(1, 1));
    }
    
//    @Test
//    public void testIsLeftButtonDown() {
//        when(mouse.isButtonDown(0)).thenReturn(true);
//        assertTrue(test.isLeftButtonDown());
//    }
    
    /**
     * Testcase for isInRectangle
     * True & True
     */
    @Test
    public void testIsInRectangle1() {
        assertTrue(test.isInRectangle(0, 2, 0, 2));
    }
    
    /**
     * Testcase for isInRectangle
     * False & True
     */
    @Test
    public void testIsInRectangle2() {
        assertFalse(test.isInRectangle(1, 1, 0, 2));
    }
    
    /**
     * Testcase for isInRectangle
     * True & False
     */
    @Test
    public void testIsInRectangle3() {
        assertFalse(test.isInRectangle(0, 2, 1, 1));
    }
    
    /**
     * Testcase for isInRectangle
     * False & False
     */
    @Test
    public void testIsInRectangle4() {
        assertFalse(test.isInRectangle(1, 1, 1, 1));
    }
    
    /**
     * Testcase for IsInRectangle(MouseRectangle rectangle).
     * True & True
     */
    @Test
    public void testIsInRectangle5() {
        MouseRectangle testrect = new MouseRectangle(0, 0, 2, 2);
        assertTrue(test.isInRectangle(testrect));
    }
    
    /**
     * Testcase for IsInRectangle(MouseRectangle rectangle).
     * False & True
     */
    @Test
    public void testIsInRectangle6() {
        MouseRectangle testrect = new MouseRectangle(1, 0, 0, 2);
        assertFalse(test.isInRectangle(testrect));
    }
    
    /**
     * Testcase for IsInRectangle(MouseRectangle rectangle).
     * True & False
     */
    @Test
    public void testIsInRectangle7() {
        MouseRectangle testrect = new MouseRectangle(0, 1, 2, 0);
        assertFalse(test.isInRectangle(testrect));
    }
    
    /**
     * Testcase for IsInRectangle(MouseRectangle rectangle).
     * False & False
     */
    @Test
    public void testIsInRectangle8() {
        MouseRectangle testrect = new MouseRectangle(1, 1, 0, 0);
        assertFalse(test.isInRectangle(testrect));
    }
    
    /**
     * Testcase for the getDrawPosition method.
     */
    @Test
    public void testGetDrawPosition() {
        DrawPosition testpos = new DrawPosition(posX, posY);
        testpos.flipYAxis();
        assertEquals(test.getDrawPosition().toString(), testpos.toString());
    }
    
    @Test
    public void testToString() {
        String retstring = "Mouse position X: "+posX+", Y: "+posY;
        assertEquals(test.toString(), retstring);
    }
}
