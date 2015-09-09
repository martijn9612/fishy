package nl.github.martijn9612.fishy;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import junit.framework.TestCase;

public class TestPlayer extends TestCase {
	
	private Main game = null;
	private AppGameContainer appgc = null;
	
	@Override
	protected void setUp() {
		try {
			game = new Main(Main.gameName);
			appgc = new AppGameContainer(game);
			appgc.setDisplayMode(Main.xSize, Main.ySize, false);
			appgc.setTargetFrameRate(60);
			appgc.start();
	    } catch (SlickException ex) {
	    	Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	
//	@Test
//	public void testCreatePlayer() {
//		Player player = new Player();
//		Assert.assertTrue(player instanceof Player);
//	}
	
	@Test
	public void testMovePlayerLeft() {
		game.enterState(Main.play);
		Player player = new Player();
		player.left(1);
		int startXValue = player.getX();
		player.momentum(player.accelA, player.accelD, player.accelW, player.accelS);
		Assert.assertEquals(startXValue - 1, player.getX());
	}
	
	@Override
	protected void tearDown()
	{
		appgc.exit();
	}

}
