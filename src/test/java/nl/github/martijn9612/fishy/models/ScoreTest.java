package nl.github.martijn9612.fishy.models;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Score class.
 * @author Danique Lummen
 */
public class ScoreTest extends TestCase {

    Score test;
    
    /**
     * Setup for the test class.
     */
    @Before
    protected void setUp() {
        test = new Score("Highscore", 1000);
    }
    
   
    /**
     * Test for the getName method.
     */
    @Test
    public void testGetName() {
        assertEquals("Highscore", test.getName());
    }

    /**
     * Test for the getScore method.
     */
    @Test
    public void testGetScore() {
        assertEquals(1000.0, test.getScore());
    }
    
    /**
     * Test for the setScore method.
     */
    @Test
    public void testSetScore() {
        test.setScore(2000);
        assertEquals(2000.0, test.getScore());
    }
    
    /**
     * Test for the setName method.
     */
    @Test
    public void testSetName() {
        test.setName("NewHighscore");
        assertEquals("NewHighscore", test.getName());
    }
    
    /**
     * Test for the compareTo method.
     */
    @Test
    public void testCompareTo() {
        Score other = new Score("Highscore", 1500);
        assertEquals(test.compareTo(other), 500);
    }
    
    
}
