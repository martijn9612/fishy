package nl.github.martijn9612.fishy.opponents;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.Test;
import org.newdawn.slick.GameContainer;

import junit.framework.TestCase;
import nl.github.martijn9612.fishy.models.Moveable;
import nl.github.martijn9612.fishy.models.NonPlayer;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.models.Vector;

public class LinearOpponentTest extends TestCase {
    private final GameContainer gc = mock(GameContainer.class);
    private final Random random = mock(Random.class);
    private Player player = mock(Player.class);

    /**
     * Setup for the testcases.
     */
    @Override
    protected void setUp() {
    }

    /**
     * Test case for objectLogic method. Spawns the opponent at the left side of
     * the screen.
     */
    @Test
    public void testLinearOpponentMoveRight() {
        Moveable opponentData = new Moveable();
        opponentData.dimensions = new Vector(10, 10);
        opponentData.velocity = new Vector(1, 0);
        NonPlayer opp = new LinearOpponent(opponentData, false);
        opp.objectLogic(gc, 0);
        assertEquals(1.0, opp.data.position.x, 0.1);
    }

    /**
     * Test case for objectLogic method. Spawns the opponent at the right side
     * of the screen.
     */
    @Test
    public void testLinearOpponentMoveLeft() {
        Moveable opponentData = new Moveable();
        opponentData.position = new Vector(10, 10);
        opponentData.dimensions = new Vector(10, 10);
        opponentData.velocity = new Vector(-1, 0);
        NonPlayer opp = new LinearOpponent(opponentData, false);
        opp.objectLogic(gc, 0);
        assertEquals(9.0, opp.data.position.x, 0.1);
    }

    @Test
    public void testCreateRandomDimensions() {
        int maxSize = (int) (player.getSize() * 2.0);
        int minSize = (int) (player.getSize() * 0.5);
        when(random.nextInt((maxSize - minSize) + minSize)).thenReturn(1);

        LinearOpponent test = LinearOpponent
                .createRandom(player, random, false);
        Vector testVector = new Vector(1, 1);
        assertTrue(test.data.dimensions.equals(testVector));
    }

    @Test
    public void testCreateRandomVelocityTrue() {
        when(random.nextInt(4)).thenReturn(1);
        when(random.nextBoolean()).thenReturn(true);
        Vector testVector = new Vector(2, 0);
        LinearOpponent test = LinearOpponent.createRandom(player, random, false);
        
        assertTrue(test.data.velocity.equals(testVector));
    }
    
    @Test
    public void testCreateRandomVelocityFalse() {
        when(random.nextInt(4)).thenReturn(1);
        when(random.nextBoolean()).thenReturn(false);
        Vector testVector = new Vector(-2, 0);
        LinearOpponent test = LinearOpponent.createRandom(player, random, false);
        
        assertTrue(test.data.velocity.equals(testVector));
    }
}
