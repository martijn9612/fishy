package nl.github.martijn9612.fishy;

import junit.framework.TestCase;
import nl.github.martijn9612.fishy.models.Score;

import org.junit.Before;
import org.junit.Test;

/**
 * Testclass for ScoreController.
 * @author Danique Lummen
 */
public class ScoreControllerTest extends TestCase {

    private ScoreController test;
    
    /**
     * Setup for the test class.
     */
    @Before
    protected void setUp() {
        test = ScoreController.getInstance();
    }
    
    /**
     * Test method for getInstance method.
     */
    @Test
    public void testGetInstance() {
        assertEquals(test, ScoreController.getInstance());
    }
    
    /**
     * Test method for StorePlayerScore method.
     */
    @Test
    public void testStorePlayerScore() {
        test.storePlayerScore(100.0);
        assertEquals(test.getPlayerScore(), 100.0);
    }

    /**
     * Test method for addScore method.
     */
    @Test
    public void testAddScore() {
        Score score = new Score("Highscore", 1000.0);
        test.addScore(score);
        assertTrue(test.getScoreList().contains(score));
    }
    
    /**
     * Test method for removeScore method.
     */
    @Test
    public void testRemoveScore1() {
        Score score = new Score("Highscore", 1000.0);
        test.addScore(score);
        test.removeScore(score);
        assertFalse(test.getScoreList().contains(score));
    }
    
    /**
     * Test method for removeScore method.
     */
    @Test
    public void testRemoveScore2() {
        Score score = new Score("Highscore", 1000.0);
        test.removeScore(score);
        assertFalse(test.getScoreList().contains(score));
    }
    
    /**
     * Test method for savedStoredScore method.
     */
    @Test
    public void testSavedStoredScore() {
        test.storePlayerScore(200.0);
        test.savedStoredScore("player");
        assertEquals(test.getPlayerScore(), 0.0);
        
    }
}
