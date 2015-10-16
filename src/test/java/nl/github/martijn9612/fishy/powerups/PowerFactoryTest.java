package nl.github.martijn9612.fishy.powerups;

import org.junit.Test;

import junit.framework.TestCase;

public class PowerFactoryTest extends TestCase {

    @Test
    public void testSetup() {
        PowerupFactory factory = new PowerupFactory(false);
        factory.setupFactory();
        assertEquals(4, factory.getPowerups().size());
    }
    
    @Test
    public void spawnTest(){
        PowerupFactory factory = new PowerupFactory(false);
        assertEquals(0, factory.getPowerups().size());
    }
}
