package nl.github.martijn9612.fishy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.newdawn.slick.GameContainer;

public class SinusOpponentTest {
    private final GameContainer gc = mock(GameContainer.class);
    
    @Test
    public void testObjectLogic() {
        Opponent opp = new SinusOpponent(1, 1);
        opp.setPosition(0, 0);
        opp.objectLogic(gc, 0);
        assertEquals(opp.speed, 1);
    }
    
    @Test
    public void testObjectLogic2() {
        Opponent opp = new SinusOpponent(1, 1);
        opp.setPosition(1, 1);
        opp.objectLogic(gc, 0);
        assertEquals(opp.speed, ((1 % 80) / 20 + 1));
    }
}
