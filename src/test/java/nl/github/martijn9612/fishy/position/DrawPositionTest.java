package nl.github.martijn9612.fishy.position;

import junit.framework.TestCase;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class DrawPositionTest extends TestCase {

    private int posX;
    private int posY;
    private DrawPosition test;
    private Graphics g = mock(Graphics.class);
    private Image im = mock(Image.class);
    
    @Override
    protected void setUp() {
        posX = 1;
        posY = 1;
        test = new DrawPosition(posX, posY);
    }
    
    @Test
    public void testDrawImage() {
        test.drawImage(g, im);
    }
    
    @Test
    public void testGetMousePosition() {
        MousePosition mousetest = new MousePosition(posX, posY);
        mousetest.flipYAxis();
        assertEquals(test.getMousePosition().toString(), mousetest.toString());
    }

    @Test
    public void testToString() {
        String retstring = "Draw position X: 1, Y: 1";
        assertEquals(retstring, test.toString());
    }
}
