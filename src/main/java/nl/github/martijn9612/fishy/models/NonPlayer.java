package nl.github.martijn9612.fishy.models;

import org.newdawn.slick.GameContainer;

import nl.github.martijn9612.fishy.Main;

/**
 * Implements the nonplayer objects in the game which are opponents and powerups.
 * Software Engineering Methods Project - Group 11.
 */
public abstract class NonPlayer extends Entity {

	/**
	 * OPPONENT_FACTOR determines the amount of pixels the opponent has to be
	 * outside of the level boundaries to be considered off the screen.
	 */
	public static final int OPPONENT_FACTOR = 5;

	/**
	 * Creates a new NonPlayer, based on the Entity class.
	 * @param data - the moveable data of the new NonPlayer.
	 * @param hasOpenGL - true if OpenGL context should be loaded, false if not.
	 */
	public NonPlayer(Moveable data, boolean hasOpenGL) {
		super(data, hasOpenGL);
	}

	/**
	 * Checks whether the opponent is visible on the screen.
	 * @return returns true if the opponent is not visible on the screen.
	 */
	public boolean isOffScreen() {
		if (getData().getPosition().x < 0 - getData().getDimensions().x * OPPONENT_FACTOR - 1
				|| getData().getPosition().x > Main.WINDOW_WIDTH + getData().getDimensions().x * OPPONENT_FACTOR + 1) {
			return true;
		}
		if (getData().getPosition().y < 0 - getData().getDimensions().y * OPPONENT_FACTOR - 1
				|| getData().getPosition().y > Main.WINDOW_HEIGHT + getData().getDimensions().y * OPPONENT_FACTOR + 1) {
			return true;
		}
		return false;
	}

	/**
	 * Notify the Opponent instance that it won't be updated anymore.
	 */
	public void destroy() {
		// Blank
	};

	/**
	 * {@inheritDoc}
	 */
	abstract public void objectLogic(GameContainer gc, int deltaTime);
}
