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
        opponentData.position = new Vector(650 / 2, 550 / 2);
        opponentData.velocity = new Vector(0, 0);
        opponentData.dimensions = new Vector(16, 16);
        when(gc.getInput()).thenReturn(input);
    };

    @Test
    public void testCreatePlayer() {
        Player test = new Player(opponentData, false);
        assertEquals(test.data.position, player.data.position);
    }

    @Test
    public void testMovePlayerLeft() {
        when(input.isKeyDown(Input.KEY_A)).thenReturn(true);
        player.objectLogic(gc, 0);
        Vector force = new Vector(-4, 0);
        force.scale(1 / 5);
        assertEquals(opponentData.acceleration.add(force), player.data.acceleration);
    }

    @Test
    public void testMovePlayerUp() {
        when(input.isKeyDown(Input.KEY_W)).thenReturn(true);
        player.objectLogic(gc, 0);
        Vector force = new Vector(0, -4);
        force.scale(1 / 5);
        assertEquals(opponentData.acceleration.add(force), player.data.acceleration);
    }

    @Test
    public void testMovePlayerDown() {
        when(input.isKeyDown(Input.KEY_S)).thenReturn(true);
        player.objectLogic(gc, 0);
        Vector force = new Vector(0, 4);
        force.scale(1 / 5);
        assertEquals(opponentData.acceleration.add(force), player.data.acceleration);
    }

    // @Test
    // public void testMovePlayerRight() {
    // when(input.isKeyDown(Input.KEY_D)).thenReturn(true);
    // doNothing().when(entity).setImageOrientation(1);
    //
    // player.objectLogic(gc, 0);
    // Vector force = new Vector(4,0);
    // force.scale(1 / 5);
    // assertEquals(acceleration.add(force), player.acceleration);
    // }

    @Test
    public void testSetScore() {
        player.setScore(1);
        assertEquals(1.0, player.getScore());
    }

    // @Test
    // public void testEat() {
    // doNothing().when(playermock).playBiteSound();
    // int oppsize = (16 + 16)/2;
    // float score = oppsize * 0.2f;
    // float newDimension = 16 + Math.round(score * 0.5f);
    // dimensions = new Vector(newDimension, newDimension);
    // player.eat(opponent);
    // assertEquals(player.dimensions, dimensions);
    // }

    @Test
    public void testResetPlayerVariablesDimensions() {
        Vector dimensions = new Vector(16, 16);
        player.resetPlayerVariables();
        assertEquals(dimensions, player.data.dimensions);
    }
    
    @Test
    public void testResetPlayerVariablesPosition() {
        Vector position = new Vector(650 / 2, 550 / 2);
        player.resetPlayerVariables();
        assertEquals(position, player.data.position);
    }

    @Test
    public void testResetPlayerVariablesScore() {
        player.resetPlayerVariables();
        assertEquals(player.getScore(), 0.0);
    }

}
