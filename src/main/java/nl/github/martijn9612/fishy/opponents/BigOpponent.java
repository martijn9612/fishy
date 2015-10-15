package nl.github.martijn9612.fishy.opponents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import nl.github.martijn9612.fishy.models.BigOpponentIndicator;
import nl.github.martijn9612.fishy.models.Moveable;
import nl.github.martijn9612.fishy.models.NonPlayer;
import nl.github.martijn9612.fishy.models.Vector;
import nl.github.martijn9612.fishy.utils.MusicPlayer;

/**
 * The BigOpponent class. A BigOpponent is a special opponent that has a small
 * chance of spawning and fills a large part of the screen.
 * Created by Skullyhoofd on 25/09/2015.
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
	 * 
	 * @param dimensions size of the new opponent.
     * @param position vector with the start position of the opponent.
	 * @param velocity initial speed of the opponent.
	 * @param acceleration initial acceleration of the opponent.
	 * @param loadResources whether the sprite resources should be loaded.
	 * @param player instance of the Player class.
	 */
    public BigOpponent(Moveable data, boolean loadResources, Moveable playerData) {
    	super(data, loadResources);
    	this.indicator = BigOpponentIndicator.createIndicator(playerData, loadResources);
        this.playerData = playerData;
        loadBigOpponentResources();
    }
    
    /**
	 * Creates an instance of BigOpponent at the payer's y-axis location.
	 * 
	 * @param player an instance of the Player class.
	 * @param loadResources whether the sprite resources should be loaded.
	 */
	public static BigOpponent createBigOpponent(Moveable playerData, boolean loadResources) {
		Moveable data = new Moveable();
		data.velocity = new Vector(-BIG_OPPONENT_SPEED, 0);
		data.dimensions = new Vector(BIG_OPPONENT_SIZE * 1.15f, BIG_OPPONENT_SIZE);
		data.position = new Vector(BIG_OPPONENT_START_X, playerData.position.y - BIG_OPPONENT_SIZE / 2);
		return new BigOpponent(data, loadResources, playerData);
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public void objectLogic(GameContainer gc, int deltaTime) {
    	indicator.objectLogic(gc, deltaTime);
    	data.updatePosition(100);
        updateBoundingbox();
        
		if (timeToLive > 0) {
			timeToLive -= deltaTime;
		}
		if (timeToLive > INDICATOR_REMOVED_AT) {
			data.position.y = playerData.position.y - BIG_OPPONENT_SIZE / 2;
		}
		if (timeToLive < INDICATOR_MOVES_AT) {
			indicator.data.acceleration.x = 2;
		}
    }
    
    /**
	 * {@inheritDoc}
	 */
    @Override
    public void renderObject(Graphics g) {
    	super.renderObject(g);
		if (timeToLive > INDICATOR_REMOVED_AT) {
			indicator.renderObject(g);
		}
    }
    
    /**
	 * Stops playing the background music.
	 */
    @Override
	public void destroy() {
		if (hasOpenGL) {
			musicPlayer.stopSound(MusicPlayer.BIG_OPPONENT_EVENT);
		}
		timeToLive = 0;
	}
	
	/**
	 * {@inheritDoc}
	 */
    @Override
    public boolean isOffScreen() {
    	return (timeToLive <= 0);
    }
	
	private void loadBigOpponentResources() {
    	if (hasOpenGL) {
    		this.loadResources(SPRITE_PATH);
    		musicPlayer.playSound(MusicPlayer.BIG_OPPONENT_EVENT);
    	}
    }
}
