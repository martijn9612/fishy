package nl.github.martijn9612.fishy.opponents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import nl.github.martijn9612.fishy.models.BigOpponentIndicator;
import nl.github.martijn9612.fishy.models.Opponent;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.models.Vector;
import nl.github.martijn9612.fishy.utils.MusicPlayer;

/**
 * The BigOpponent class. A BigOpponent is a special opponent that has a small
 * chance of spawning and fills a large part of the screen.
 * Created by Skullyhoofd on 25/09/2015.
 */
public class BigOpponent extends Opponent {
    private static final float BIG_OPPONENT_SIZE = 350;
    private static final float BIG_OPPONENT_SPEED = 1;
    private static final float BIG_OPPONENT_START_X = 930;
    private static final int INDICATOR_REMOVED_AT = 20500;
    private static final int INDICATOR_MOVES_AT = 21000;
    private static final String SPRITE_PATH = "resources/whale.png";
    private BigOpponentIndicator indicator;
    private int timeToLive = 25000;
	private boolean loadResources;
	private Player player;
    
    /**
	 * Creates an instance of BigOpponent at the given location.
     * @param position vector with the start position of the opponent.
	 * @param dimensions size of the new opponent.
	 * @param velocity initial speed of the opponent.
	 * @param player instance of the Player class.
	 * @param loadResources whether the sprite resources should be loaded.
	 */
    public BigOpponent(Vector position, Vector dimensions, Vector velocity, Player player, boolean loadResources) {
    	super(loadResources);
    	this.loadResources = loadResources;
    	indicator = new BigOpponentIndicator(player, loadResources);
        this.position = position;
        this.dimensions = dimensions;
        this.velocity = velocity;
        this.player = player;
        loadBigOpponentResources();
        updateBoundingbox();
    }
    
    /**
	 * Creates an instance of BigOpponent at the payer's y location.
	 * @param player an instance of the Player class.
	 * @param loadResources whether the sprite resources should be loaded.
	 */
	public static BigOpponent createBigOpponent(Player player, boolean loadResources) {
		Vector velocity = new Vector(-BIG_OPPONENT_SPEED, 0);
		Vector dimensions = new Vector(BIG_OPPONENT_SIZE * 1.15f, BIG_OPPONENT_SIZE);
		Vector position = new Vector(BIG_OPPONENT_START_X, player.position.y - BIG_OPPONENT_SIZE / 2);
		return new BigOpponent(position, dimensions, velocity, player, loadResources);
    }
    
    public void objectLogic(GameContainer gc, int deltaTime) {
    	indicator.objectLogic(gc, deltaTime);
        position.add(velocity);
        updateBoundingbox();
        
		if (timeToLive > 0) {
			timeToLive -= deltaTime;
		}
		if (timeToLive > INDICATOR_REMOVED_AT) {
			position.y = player.position.y - BIG_OPPONENT_SIZE / 2;
		}
		if (timeToLive < INDICATOR_MOVES_AT) {
			indicator.acceleration.x = 2;
		}
    }
    
    @Override
    public void renderObject(Graphics g) {
    	super.renderObject(g);
		if (timeToLive > INDICATOR_REMOVED_AT) {
			indicator.renderObject(g);
		}
    }

	@Override
	public void destroy() {
		if (loadResources) {
			musicPlayer.stopSound(MusicPlayer.BIG_OPPONENT_EVENT);
		}
		timeToLive = 0;
	}
	
    @Override
    public boolean isOffScreen() {
    	return (timeToLive <= 0);
    }
	
	private void loadBigOpponentResources() {
    	if (loadResources) {
    		this.loadResources(SPRITE_PATH);
    		musicPlayer.playSound(MusicPlayer.BIG_OPPONENT_EVENT);
    	}
    }
}
