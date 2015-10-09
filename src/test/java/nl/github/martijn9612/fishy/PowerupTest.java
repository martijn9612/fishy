package nl.github.martijn9612.fishy;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.util.Random;

import junit.framework.TestCase;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.powerups.ExtraLife;
import nl.github.martijn9612.fishy.powerups.Powerup;
import nl.github.martijn9612.fishy.powerups.Speedup;

import org.junit.Test;

public class PowerupTest extends TestCase{

    @Test
    public void testgetSpeedupChance() {
        Random random = new Random();
        Powerup power = Speedup.createPowerup(random, false);
        assertEquals(power.getChance(), 30);
    }
    @Test
    public void testgetExtraLifeChance() {
        Random random = new Random();
        Powerup power = ExtraLife.createPowerup(random, false);
        assertEquals(power.getChance(), 10);
    }
    
    @Test
    public void testPlayerEffect(){
        Player player = mock(Player.class);
        Random random = new Random();
        Powerup power = Speedup.createPowerup(random, false);
        power.Effect(player);
        verify(player).Speedup(5000);
        
    }
    
    @Test
    public void testPlayerEffect2(){
        Player player = mock(Player.class);
        Random random = new Random();
        Powerup power = ExtraLife.createPowerup(random, false);
        power.Effect(player);
        verify(player).Extralife();
        
    }

}
