package nl.github.martijn9612.fishy.models;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import junit.framework.TestCase;
import nl.github.martijn9612.fishy.opponents.LinearOpponent;
import nl.github.martijn9612.fishy.states.LevelState;

import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class PlayerTest extends TestCase {

    private Player player;
    private LinearOpponent opponent;
    private LevelState levelstate = mock(LevelState.class);
    private Player playermock = mock(Player.class);
    private Entity entity = Mockito.mock(Entity.class, Mockito.CALLS_REAL_METHODS);
    private final GameContainer gc = mock(GameContainer.class);
    private final Input input = mock(Input.class);
    private Vector dimensions;
    private Vector velocity;
    private Vector position;
    private Vector acceleration;

    @Override
    protected void setUp() {
        player = Player.createPlayer(false);
        position = new Vector(650/2, 550/2);
        velocity = new Vector(0, 0);
        dimensions = new Vector(16, 16);
        acceleration = new Vector(0, 0);
        opponent = new LinearOpponent(dimensions, position,velocity, acceleration, false);
        when(gc.getInput()).thenReturn(input);
        
    };

    @Test
    public void testCreatePlayer() {
        Player test = new Player(dimensions, position, velocity, acceleration, false);
        assertEquals(test.position, player.position);
    }

    
    @Test
    public void testMovePlayerLeft() {
        when(input.isKeyDown(Input.KEY_A)).thenReturn(true);
        player.objectLogic(gc, 0);
        Vector force = new Vector(-4,0);
        force.scale(1 / 5);
        assertEquals(acceleration.add(force), player.acceleration);
    }
    
    @Test
    public void testMovePlayerUp() {
        when(input.isKeyDown(Input.KEY_W)).thenReturn(true);
        player.objectLogic(gc, 0);
        Vector force = new Vector(0,-4);
        force.scale(1 / 5);
        assertEquals(acceleration.add(force), player.acceleration);
    }
    
    
    @Test
    public void testMovePlayerDown() {
        when(input.isKeyDown(Input.KEY_S)).thenReturn(true);
        player.objectLogic(gc, 0);
        Vector force = new Vector(0,4);
        force.scale(1 / 5);
        assertEquals(acceleration.add(force), player.acceleration);
    }
    
//    @Test
//    public void testMovePlayerRight() {
//        when(input.isKeyDown(Input.KEY_D)).thenReturn(true);
//        doNothing().when(entity).setImageOrientation(1);
//        
//        player.objectLogic(gc, 0);
//        Vector force = new Vector(4,0);
//        force.scale(1 / 5);
//        assertEquals(acceleration.add(force), player.acceleration);
//    }
    
    @Test
    public void testSetScore() {
        player.setScore(1);
        assertEquals(1.0, player.getScore());
    }
    
//    @Test
//    public void testEat() {
//        doNothing().when(playermock).playBiteSound();
//        int oppsize = (16 + 16)/2;
//        float score = oppsize * 0.2f;
//        float newDimension = 16 + Math.round(score * 0.5f);
//        dimensions = new Vector(newDimension, newDimension);
//        player.eat(opponent);
//        assertEquals(player.dimensions, dimensions);
//    }
    
    @Test
    public void testResetPlayerVariables() {
        player.resetPlayerVariables();
        assertEquals(player.getScore(), 0.0);
    }
    

    
}
