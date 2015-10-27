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
import nl.github.martijn9612.fishy.opponents.SinusOpponent;

/**
 * Test class for SinusOpponent.
 * @author Danique Lummen
 *
 */
public class SinusOpponentTest extends TestCase {
    private final GameContainer gc = mock(GameContainer.class);
    private final Random random = mock(Random.class);
    private Player player = mock(Player.class);


    /**
     * Setup for the testcases.
     */
    @Override
    protected void setUp() {
        int maxSize = (int) (player.getSize() * 2.0);
        int minSize = (int) (player.getSize() * 0.5);
        when(random.nextInt((maxSize - minSize) + minSize)).thenReturn(1);

        int min = (int) Math.round(1);
        int max = 615 - min;
        when(random.nextInt((Math.abs(max - min)) + min)).thenReturn(1);
    }

    /**
     * Testcase for objectLogic method.
     * data.position.y <= 0
     */
    @Test
    public void testObjectLogic() {
        Moveable opponentData = new Moveable();
        opponentData.setDimensions(new Vector(10, 1));
        NonPlayer testopp = new SinusOpponent(opponentData, false);
        testopp.objectLogic(gc, 0);
        assertEquals(-1.0, testopp.getData().getVelocity().y, 0.1);
    }

    /**
     * Testcase for objectLogic method.
     * data.position.y > 0
     */
    @Test
    public void testObjectLogic2() {
        Moveable opponentData = new Moveable();
        opponentData.setDimensions(new Vector(10, 1));
        opponentData.setPosition(new Vector(1, 1));
        NonPlayer testopp = new SinusOpponent(opponentData, false);
        testopp.getData().setPosition(new Vector(1, 1));
        testopp.objectLogic(gc, 0);
        assertEquals(-((1 % 80) / 20 + 1), testopp.getData().getVelocity().y, 0.1);
    }

    /**
     * Testcase for createRandom.
     * getRandomDimensions method is tested.
     */
    @Test
    public void testCreateRandomDimensions() {
        SinusOpponent test = SinusOpponent.createRandom(player, random, false);
        Vector testVector = new Vector(1, 1);
        assertTrue(test.getData().getDimensions().equals(testVector));
    }

    /**
     * Testcase for createRandom.
     * getRandomPosition method is tested.
     */
    @Test
    public void testCreateRandomPosition() {
        SinusOpponent test = SinusOpponent.createRandom(player, random, false);
        Vector testVector = new Vector(1, 550);
        assertTrue(test.getData().getPosition().equals(testVector));
    }
}
