package nl.github.martijn9612.fishy;

import junit.framework.TestCase;

import org.junit.Test;
import nl.github.martijn9612.fishy.utils.ActionLogger;

/**
 * Test class for Main class.
 * @author Danique Lummen
 *
 */
public class MainTest extends TestCase {

    private Main main;

    /**
     * Setup for the testcases.
     */
    @Override
    protected void setUp() {
        main = new Main("testMain");
    }

    /**
     * Testcase for the get and set method for ActionLogger.
     */
    @Test
    public void testSetGetActionLogger() {
        ActionLogger test = new ActionLogger();
        Main.setActionLogger(test);
        assertEquals(Main.getActionLogger(), test);
    }

    /**
     * Testcase for closeRequested.
     */
    @Test
    public void testCloseRequested() {
        assertTrue(main.closeRequested());
    }

}
