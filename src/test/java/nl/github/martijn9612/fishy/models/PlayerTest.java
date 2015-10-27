package nl.github.martijn9612.fishy.models;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import junit.framework.TestCase;

public class PlayerTest extends TestCase {

    private Player player;
    private final GameContainer gc = mock(GameContainer.class);
    private final Input input = mock(Input.class);
    private Moveable opponentData;

    @Override
    protected void setUp() {
        player = Player.createPlayer(false);
        opponentData = new Moveable();
        opponentData.setPosition(new Vector(650 / 2, 550 / 2));
        opponentData.setVelocity(new Vector(0, 0));
        opponentData.setDimensions(new Vector(16, 16));
        when(gc.getInput()).thenReturn(input);
    };

    @Test
    public void testCreatePlayer() {
        Player test = new Player(opponentData, false);
        assertEquals(test.getData().getPosition(), player.getData().getPosition());
    }

    @Test
    public void testMovePlayerLeft1() {
        when(input.isKeyDown(Input.KEY_A)).thenReturn(true);
        player.objectLogic(gc, 0);
        Vector force = new Vector(-4, 0);
        force.scale(1 / 5);
        assertEquals(opponentData.getAcceleration().add(force), player.getData().getAcceleration());
    }
    
    @Test
    public void testMovePlayerLeft2() {
        when(input.isKeyDown(Input.KEY_LEFT)).thenReturn(true);
        player.objectLogic(gc, 0);
        Vector force = new Vector(-4, 0);
        force.scale(1 / 5);
        assertEquals(opponentData.getAcceleration().add(force), player.getData().getAcceleration());
    }
    

    @Test
    public void testMovePlayerUp1() {
        when(input.isKeyDown(Input.KEY_W)).thenReturn(true);
        player.objectLogic(gc, 0);
        Vector force = new Vector(0, -4);
        force.scale(1 / 5);
        assertEquals(opponentData.getAcceleration().add(force), player.getData().getAcceleration());
    }
    
    @Test
    public void testMovePlayerUp2() {
        when(input.isKeyDown(Input.KEY_UP)).thenReturn(true);
        player.objectLogic(gc, 0);
        Vector force = new Vector(0, -4);
        force.scale(1 / 5);
        assertEquals(opponentData.getAcceleration().add(force), player.getData().getAcceleration());
    }

    @Test
    public void testMovePlayerDown1() {
        when(input.isKeyDown(Input.KEY_S)).thenReturn(true);
        player.objectLogic(gc, 0);
        Vector force = new Vector(0, 4);
        force.scale(1 / 5);
        assertEquals(opponentData.getAcceleration().add(force), player.getData().getAcceleration());
    }
    
    @Test
    public void testMovePlayerDown2() {
        when(input.isKeyDown(Input.KEY_DOWN)).thenReturn(true);
        player.objectLogic(gc, 0);
        Vector force = new Vector(0, 4);
        force.scale(1 / 5);
        assertEquals(opponentData.getAcceleration().add(force), player.getData().getAcceleration());
    }


    @Test
    public void testSetScore() {
        player.setScore(1);
        assertEquals(1.0, player.getScore());
    }

    @Test
    public void testResetPlayerVariablesDimensions() {
        Vector dimensions = new Vector(16, 16);
        player.resetPlayerVariables();
        assertEquals(dimensions, player.getData().getDimensions());
    }
    
    @Test
    public void testResetPlayerVariablesPosition() {
        Vector position = new Vector(650 / 2, 550 / 2);
        player.resetPlayerVariables();
        assertEquals(position, player.getData().getPosition());
    }

    @Test
    public void testResetPlayerVariablesScore() {
        player.resetPlayerVariables();
        assertEquals(player.getScore(), 0.0);
    }
    
    @Test
    public void testEat() {
        double testscore = player.getScore() + 1 * 0.2f;
        player.eat(1);
        assertEquals(player.getScore(), testscore);
    }
    
    @Test
    public void testSpeedup() {
        //NOT FINISHED!
        player.Speedup(0);

    }
    
    @Test
    public void testPoison() {
        //NOT FINISHED!
        player.Poison(0);
    }
    
    @Test
    public void testExtraLife() {
        int lives = player.getLives();
        player.Extralife();
        assertEquals(player.getLives(), lives + 1);
    }
    
    @Test
    public void testLoseLife() {
        int lives = player.getLives();
        player.Loselife();
        assertEquals(player.getLives(), lives - 1);
    }

    @Test
    public void testGetLivesAsString() {
        String lives = "lives: (" + player.getLives() + ")";
        assertEquals(player.getLivesAsString(), lives);
    }
    
    @Test
    public void testAddShield() {
        player.setKey("half");
        player.addShield(0, 0);
        assertEquals("full", player.getKey());

    }
    
    @Test
    public void testRemoveShield() {
        player.setKey("full");
        player.removeShield(1);
        assertEquals("half", player.getKey());
    }
    
    @Test
    public void testHasShield() {
        assertFalse(player.hasShield());
    }
}
