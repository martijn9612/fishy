package nl.github.martijn9612.fishy.models;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.newdawn.slick.GameContainer;

import junit.framework.TestCase;

public class BigOpponentIndicatorTest extends TestCase {

    private Player player;
    private Moveable opponentData;
    private BigOpponentIndicator testIndicator;
    private final GameContainer gc = mock(GameContainer.class);
    private List<Float> posHistory = new ArrayList<Float>();

    @Override
    protected void setUp() {
        player = Player.createPlayer(false);
        opponentData = new Moveable();
        opponentData.setDimensions(new Vector(115, 100));
        opponentData.setPosition(new Vector(580, player.getData().getPosition().y - (100 / 2)));
        testIndicator = new BigOpponentIndicator(opponentData, false, player.getData());
    }

    @Test
    public void testIndicatortPosition() {
    	BigOpponentIndicator indicator = BigOpponentIndicator.createIndicator(player.getData(), false);
        assertEquals(indicator.getData().getPosition(), opponentData.getPosition());
    }
    
    @Test
    public void testIndicatorVelocity() {
    	BigOpponentIndicator indicator = BigOpponentIndicator.createIndicator(player.getData(), false);
        assertEquals(indicator.getData().getVelocity(), opponentData.getVelocity());
    }
    
    @Test
    public void testIndicatorAcceleration() {
    	BigOpponentIndicator indicator = BigOpponentIndicator.createIndicator(player.getData(), false);
        assertEquals(indicator.getData().getAcceleration(), opponentData.getAcceleration());
    }
    
    @Test
    public void testIndicatorDimensions() {
    	BigOpponentIndicator indicator = BigOpponentIndicator.createIndicator(player.getData(), false);
        assertEquals(indicator.getData().getDimensions(), opponentData.getDimensions());
    }
    
    @Test
    public void testObjectLogicVelocity() {
        float newIndicatorPosition = player.getData().getPosition().y - (100 / 2);
        posHistory.add(newIndicatorPosition);
        opponentData.getVelocity().add(opponentData.getAcceleration());
        testIndicator.objectLogic(gc, 0);
        assertEquals(testIndicator.getData().getVelocity(), opponentData.getVelocity());
    }
    
    @Test
    public void testObjectLogicAcceleration() {
        float newIndicatorPosition = player.getData().getPosition().y - (100 / 2);
        posHistory.add(newIndicatorPosition);
        opponentData.getAcceleration().scale(0);
        testIndicator.objectLogic(gc, 0);
        assertEquals(testIndicator.getData().getAcceleration(), opponentData.getAcceleration());
    }
    
    @Test
    public void testObjectLogicPosition() {
        float newIndicatorPosition = player.getData().getPosition().y - (100 / 2);
        posHistory.add(newIndicatorPosition);
        opponentData.getPosition().add(opponentData.getVelocity());
        testIndicator.objectLogic(gc, 0);
        assertEquals(testIndicator.getData().getPosition(), opponentData.getPosition());
    }

}
