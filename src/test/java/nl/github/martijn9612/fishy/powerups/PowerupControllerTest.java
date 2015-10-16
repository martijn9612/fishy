package nl.github.martijn9612.fishy.powerups;

import java.util.Random;

import junit.framework.TestCase;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.powerups.Powerup;
import nl.github.martijn9612.fishy.powerups.PowerupController;
import nl.github.martijn9612.fishy.powerups.PowerupFactory;

import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;


/**
 * Testclass for PowerupController
 * @author Danique Lummen
 *
 */
public class PowerupControllerTest extends TestCase {

    PowerupController powercontroller = new PowerupController();
    PowerupFactory factory = mock(PowerupFactory.class);
    GameContainer gc = mock(GameContainer.class);
    Graphics g = mock(Graphics.class);
    Player player = mock(Player.class);
    StateBasedGame sbg = mock(StateBasedGame.class);
    Powerup powerup;
    
    /**
     * Setup before the testcases are executed.
     */
    @Override
    protected void setUp() {
        Random random = new Random();
        powerup = ExtraLife.createPowerup(random, false);
        when(factory.spawnPowerup()).thenReturn(powerup);
        powercontroller.setPowerupFactory(factory);
    }
    
    /**
     * Test for spawnPowerup.
     * Case where power == null
     */
    @Test
    public void testSpawnPowerup1() {
        powercontroller.SpawnPowerup();
        assertEquals(powerup, powercontroller.getPower());
    }
    
    /**
     * Test for spawnPowerup.
     * Case where power != null
     */
    @Test
    public void testSpawnPowerup2() {
        powercontroller.SpawnPowerup();
        powercontroller.SpawnPowerup();
        assertFalse(powercontroller.getPower().equals(null));
    }
    
    /**
     * Test for updatePowerup.
     * Case where power == null.
     */
    @Test
    public void testUpdatePowerup() {
        powercontroller.updatePowerup(gc, 0);
        assertEquals(powercontroller.getPower(), null);
    }
    
    /**
     * Test for updatePowerup.
     * Case where power != null & !power.isOffScreen().
     */
    @Test
    public void testUpdatePowerup2() {
        powercontroller.SpawnPowerup();
        powercontroller.updatePowerup(gc, 0);
    }
    
    /**
     * Test for updatePowerup.
     * Case where power != null & power.isOffScreen().
     */
    @Test 
    public void testUpdatePowerup3() {
        powercontroller.SpawnPowerup();
        powercontroller.getPower().data.dimensions.x = 0;
        powercontroller.getPower().data.position.x = 700;
        powercontroller.updatePowerup(gc, 0); 
    }
    
    /**
     * Test for renderOpponents.
     * Case where power == null.
     */
    @Test
    public void testRenderOpponents1() {
        powercontroller.renderOpponents(g);
        assertEquals(powercontroller.getPower(), null);
    }
    
    /**
     * Test for renderOpponents.
     * Case where power != null.
     */
    @Test
    public void testRenderOpponents2() {
        powercontroller.SpawnPowerup();
        powercontroller.renderOpponents(g);
        assertFalse(powercontroller.getPower().equals(null));    
    }
    
    /**
     * Test for collide.
     * Case where power == null.
     */
    @Test
    public void testCollide1() {
        powercontroller.collide(player, sbg);
        assertEquals(powercontroller.getPower(), null);
    }
    
    /**
     * Test for collide.
     * Case where power.intersects(player) is true.
     */
    @Test
    public void testCollide2() {
        Powerup powerup = mock(Powerup.class);
        when(powerup.intersects(player)).thenReturn(true);
        Mockito.doNothing().when(player).playBiteSound();
        powercontroller.setPower(powerup);
        powercontroller.collide(player, sbg);
    }
    
    /**
     * Test for collide.
     * Case where power.intersects(player) is false.
     */
    @Test
    public void testCollide3() {
        Powerup powerup = mock(Powerup.class);
        when(powerup.intersects(player)).thenReturn(false);
        powercontroller.setPower(powerup);
        powercontroller.collide(player, sbg);
    }
    
    /**
     * Test for Remove.
     * Case where power == null.
     */
    @Test
    public void testRemove() {
        powercontroller.Remove();
        assertEquals(powercontroller.getPower(), null);
    }

}
