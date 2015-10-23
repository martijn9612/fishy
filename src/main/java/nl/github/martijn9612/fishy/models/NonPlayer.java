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
<<<<<<< HEAD
	 * Creates a new opponent, based on the Entity class.
	 * 
	 * @param dimensions size of the opponent in vector notation.
	 * @param position start location of the opponent.
	 * @param velocity initial speedUp of the opponent.
	 * @param acceleration initial acceleration of the opponent.
	 * @param hasOpenGL whether OpenGL context is available.
=======
	 * Creates a new NonPlayer, based on the Entity class.
	 * @param data - the moveable data of the new NonPlayer.
	 * @param hasOpenGL - true if OpenGL context should be loaded, false if not.
>>>>>>> 0e55725f20ac101dfd7c2c07acdf2dd1c757ee4e
	 */
	public NonPlayer(Moveable data, boolean hasOpenGL) {
		super(data, hasOpenGL);
	}

	/**
	 * Checks whether the opponent is visible on the screen.
	 * @return returns true if the opponent is not visible on the screen.
	 */
	public boolean isOffScreen() {
		if (data.position.x < 0 - data.dimensions.x * OPPONENT_FACTOR - 1
				|| data.position.x > Main.WINDOW_WIDTH + data.dimensions.x * OPPONENT_FACTOR + 1) {
			return true;
		}
		if (data.position.y < 0 - data.dimensions.y * OPPONENT_FACTOR - 1
				|| data.position.y > Main.WINDOW_HEIGHT + data.dimensions.y * OPPONENT_FACTOR + 1) {
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
