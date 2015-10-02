package nl.github.martijn9612.fishy.models;

import org.newdawn.slick.GameContainer;

import nl.github.martijn9612.fishy.Main;

/**
 * Created by martijn on 23-9-15.
 */
public abstract class Opponent extends Entity {
	
	/**
	 * OPPONENT_FACTOR determines the amount of pixels the opponent has to
	 * be outside of the level boundaries to be considered off the screen.
	 */
    public static final int OPPONENT_FACTOR = 5;
    
    public Opponent(boolean hasOpenGL) {
		super(hasOpenGL);
	}

    /**
     * Checks whether the opponent is visible on the screen.
     * @return returns true if the opponent is not visible on the screen
     */
	public boolean isOffScreen() {
		if (position.x < 0 - dimensions.x * OPPONENT_FACTOR - 1
				|| position.x > Main.WINDOW_WIDTH + dimensions.x * OPPONENT_FACTOR + 1) {
			return true;
		}
		if (position.y < 0 - dimensions.y * OPPONENT_FACTOR - 1
				|| position.y > Main.WINDOW_HEIGHT + dimensions.y * OPPONENT_FACTOR + 1) {
			return true;
		}
		return false;
	}
    
    abstract public void objectLogic(GameContainer gc, int deltaTime);
    
    /**
     * Notify the Opponent instance that it won't be updated anymore.
     */
	abstract public void destroy();
		
}
