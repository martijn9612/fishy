package nl.github.martijn9612.fishy.models;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;

/**
 * A sprite that indicates a big opponent will be spawned soon.
 * Created by Skullyhoofd on 25/09/2015.
 */
public class BigOpponentIndicator extends Entity {

    private Player player;
    private static final int LAG_BY_FRAMES = 15;
    private static final float WHALE_START_X = 580;
    private static final float WHALE_SIZE_X = 115;
    private static final float WHALE_SIZE_Y = 100;
    private static final String SPRITE_PATH = "resources/whale.png";
    private List<Float> positionHistory = new ArrayList<Float>();
    
    /**
	 * Constructor an instance of BigOpponentIndicator that responds to player movement.
	 * 
	 * @param dimensions size of the new opponent.
	 * @param position vector with the start position of the opponent.
	 * @param velocity initial speed of the opponent.
	 * @param acceleration initial acceleration of the opponent.
	 * @param loadResources whether the sprite resources should be loaded.
	 * @param player an instance of the Player class.
	 */
    public BigOpponentIndicator(Vector dimensions, Vector position, Vector velocity, Vector acceleration, boolean loadResources, Player player) {
    	super(dimensions, position, velocity, acceleration, loadResources);
        loadResources(SPRITE_PATH);
        this.player = player;
    }
    
    /**
	 * Creates an instance of BigOpponentIndicator at the right side of the screen.
	 * 
	 * @param player an instance of the Player class.
	 * @param loadResources whether the sprite resources should be loaded.
	 */
    public static BigOpponentIndicator createIndicator(Player player, boolean loadResources) {
    	Vector velocity = new Vector(0,0);
    	Vector acceleration = new Vector(0,0);
    	Vector dimensions = new Vector(WHALE_SIZE_X, WHALE_SIZE_Y);
    	Vector position = new Vector(WHALE_START_X, player.position.y - (WHALE_SIZE_Y / 2));
        return new BigOpponentIndicator(dimensions, position, velocity, acceleration, loadResources, player);
    }
    
    /**
     * Moves the indicator towards the player's y-axis location. Also moves
     * the indicator on the screen when acceleration is set to a value.
     * 
     * @param gc the container holding the game.
     * @param deltaTime time elapsed since method was called in milliseconds.
     */
	@Override
	public void objectLogic(GameContainer gc, int deltaTime) {
		float newIndicatorPosition = player.position.y - (WHALE_SIZE_Y / 2);
		positionHistory.add(newIndicatorPosition);
		updateIndicatorY();
		velocity.add(acceleration);
		position.add(velocity);
		acceleration.scale(0);
	}
	
	/**
	 * Updates the Y coordinate of the indicator. This value will
	 * follow player's Y coordinate lagged by LAG_BY_FRAMES frames.
	 */
	private void updateIndicatorY() {
		if(positionHistory.size() > LAG_BY_FRAMES) {
			position.y = positionHistory.get(0);
			positionHistory.remove(0);
		}
	}
}
