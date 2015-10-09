package nl.github.martijn9612.fishy;

import static org.junit.Assert.*;
import nl.github.martijn9612.fishy.powerups.Powerup;
import nl.github.martijn9612.fishy.powerups.PowerupFactory;

import org.junit.Test;

public class PowerFactoryTest {

    @Test
    public void testSetup() {
        PowerupFactory factory = new PowerupFactory(false);
        factory.setupFactory();
        assertEquals(factory.getPowerups().size(), 2);
    }
    
    @Test
    public void spawnTest(){
        PowerupFactory factory = new PowerupFactory(false);
        Powerup power = factory.spawnPowerup();
        assertEquals(factory.getPowerups().size(), 0);
    }

}
