package nl.github.martijn9612.fishy.opponents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import nl.github.martijn9612.fishy.models.BigOpponentIndicator;
import nl.github.martijn9612.fishy.models.Moveable;
import nl.github.martijn9612.fishy.models.NonPlayer;
import nl.github.martijn9612.fishy.models.Vector;
import nl.github.martijn9612.fishy.utils.MusicPlayer;

/**
 * Implementation of the BigOpponent class. 
 * A BigOpponent is a special opponent that has a small
 * chance of spawning and fills a large part of the screen.
 * Software Engineering Methods Project - Group 11.
 */
public class BigOpponent extends NonPlayer {
    private static final float BIG_OPPONENT_SIZE = 350;
    private static final float BIG_OPPONENT_SPEED = 1;
    private static final float BIG_OPPONENT_START_X = 930;
    private static final int INDICATOR_REMOVED_AT = 20500;
    private static final int INDICATOR_MOVES_AT = 21000;
    private static final String SPRITE_PATH = "resources/whale.png";
    private BigOpponentIndicator indicator;
    private int timeToLive = 25000;
	private Moveable playerData;
    
    /**
	 * Creates an instance of BigOpponent at the given location.
	 * @param data - the Moveable data the BigOpponent will have.
	 * @param loadResources - true if OpenGL context should be loaded, false if not.
	 * @param playerData - the Moveable data of the current player.
	 */
    public BigOpponent(Moveable data, boolean loadResources, Moveable playerData) {
    	super(data, loadResources);
    	this.indicator = BigOpponentIndicator.createIndicator(playerData, loadResources);
        this.playerData = playerData;
        loadBigOpponentResources();
    }
    
    /**
	 * Creates an instance of BigOpponent at the player's y-axis location.
	 * @param playerData - the Moveable data of the current player.
	 * @param loadResources - true if OpenGL context should be loaded, false if not.
	 * @return new BigOpponent at the player's y-axis position.
	 */
	public static BigOpponent createBigOpponent(Moveable playerData, boolean loadResources) {
		Moveable data = new Moveable();
		data.setVelocity(new Vector(-BIG_OPPONENT_SPEED, 0));
		data.setDimensions(new Vector(BIG_OPPONENT_SIZE * 1.15f, BIG_OPPONENT_SIZE));
		data.setPosition(new Vector(BIG_OPPONENT_START_X, playerData.getPosition().y - BIG_OPPONENT_SIZE / 2));
		return new BigOpponent(data, loadResources, playerData);
    }
	
    /**
     * Execute the logic calculations associated with this BigOpponent.
     * @param gc - the container holding the game.
     * @param deltaTime - milliseconds passed since last call.
     */
	@Override
    public void objectLogic(GameContainer gc, int deltaTime) {
    	indicator.objectLogic(gc, deltaTime);
    	checkProgress(deltaTime);
    	getData().updatePosition(100);
        updateBoundingbox();
    }

	/**
	 * Check the progress of the BigOpponent.
	 * @param deltaTime - milliseconds passed since last call.
	 */
	private void checkProgress(int deltaTime) {
		if (timeToLive > 0) {
			timeToLive -= deltaTime;
		}
		if (timeToLive > INDICATOR_REMOVED_AT) {
			getData().getPosition().y = playerData.getPosition().y - BIG_OPPONENT_SIZE / 2;
		}
		if (timeToLive < INDICATOR_MOVES_AT) {
			indicator.getData().getAcceleration().x = 2;
		}
	}

    /**
     * Renders the object and the boundary box when enabled.
     * @param g - the graphics content used to render.
     */
    @Override
    public void renderObject(Graphics g) {
    	super.renderObject(g);
		if (timeToLive > INDICATOR_REMOVED_AT) {
			indicator.renderObject(g);
		}
    }
    
    /**
	 * Stops playing the background music 
	 * and sets timeToLive to 0.
	 */
    @Override
	public void destroy() {
		if (hasOpenGL) {
			musicPlayer.stopSound(MusicPlayer.BIG_OPPONENT_EVENT);
		}
		timeToLive = 0;
	}
	
    /**
     * Checks whether the opponent is visible on the screen.
     * @return returns true if the opponent is not visible on the screen.
     */
    @Override
    public boolean isOffScreen() {
    	return (timeToLive <= 0);
    }
	
    /**
     * Loads the resources associated with BigOpponent.
     * Only if the OpenGL context should be loaded.
     */
	private void loadBigOpponentResources() {
    	if (hasOpenGL) {
    		this.loadResources(SPRITE_PATH);
    		musicPlayer.playSound(MusicPlayer.BIG_OPPONENT_EVENT);
    	}
    }
	
	/**
	 * Change the timeToLive.
	 * Method for testing purposes.
	 * @param newtime - new timeToLive.
	 */
	public void changeTimeToLive(int newtime) {
	    timeToLive = newtime;
	}
	
	/**
	 * Gets the timeToLive.
	 * @return the current time to live.
	 */
	public int getTimeToLive() {
	    return timeToLive;
	}
	
}
