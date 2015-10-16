package nl.github.martijn9612.fishy.powerups;

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
        assertEquals(power.getChance(), 100);
    }
    @Test
    public void testgetExtraLifeChance() {
        Random random = new Random();
        Powerup power = ExtraLife.createPowerup(random, false);
        assertEquals(power.getChance(), 30);
    }
    
    @Test
    public void testgetShieldChance() {
        Random random = new Random();
        Powerup power = Shield.createPowerup(random, false);
        assertEquals(power.getChance(), 30);
    }
    @Test
    public void testgetPoisonChance() {
        Random random = new Random();
        Powerup power = Poison.createPowerup(random, false);
        assertEquals(power.getChance(), 30);
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
    
    @Test
    public void testPlayerEffect3(){
        Player player = mock(Player.class);
        Random random = new Random();
        Powerup power = Shield.createPowerup(random, false);
        power.Effect(player);
        verify(player).addShield(5000,2000);
        
    }
    
    @Test
    public void testPlayerEffect4(){
        Player player = mock(Player.class);
        Random random = new Random();
        Powerup power = Poison.createPowerup(random, false);
        power.Effect(player);
        verify(player).Poison(10000);
        
    }

}
