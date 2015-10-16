package nl.github.martijn9612.fishy.powerups;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;
import nl.github.martijn9612.fishy.powerups.PowerupFactory;

import java.util.Random;

import org.junit.Test;

/**
 * Testclass for PowerupFactory.
 * @author Danique Lummen
 */
public class PowerFactoryTest extends TestCase {

    PowerupFactory factory = new PowerupFactory(false);
    
    /**
     * Test for setupFactory.
     */
    @Test
    public void testSetupFactory() {
        factory.setupFactory();
        assertEquals(4, factory.getPowerups().size());
    }
    
    /**
     * Test for spawnPowerup.
     * Case where random < chance.
     */
    @Test
    public void testSpawnPowerup1() {
        factory.setupFactory();
        Random randommock = mock(Random.class);
        factory.setRandom(randommock);
        when(randommock.nextInt(factory.getPowerups().size())).thenReturn(1);
        when(randommock.nextInt(1001)).thenReturn(1);
        factory.spawnPowerup();
        assertFalse(factory.getPowerup() == null);
        
    }
    
    /**
     * Test for spawnPowerup.
     * Case where random >= chance.
     */
    @Test
    public void testSpawnPowerup2() {
        factory.setupFactory();
        Random randommock = mock(Random.class);
        factory.setRandom(randommock);  
        when(randommock.nextInt(factory.getPowerups().size())).thenReturn(1);
        when(randommock.nextInt(1001)).thenReturn(200);
        factory.spawnPowerup();
        assertTrue(factory.getPowerup() == null);
    }
    
    /**
     * Test for clearFactory.
     */
    @Test 
    public void testClearFactory() {
        factory.clearFactory();
        assertTrue(factory.getPowerups().isEmpty());
    }
}
