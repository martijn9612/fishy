package nl.github.martijn9612.fishy.opponents;

import org.newdawn.slick.GameContainer;

import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.models.Opponent;
import nl.github.martijn9612.fishy.models.Vector;

/**
 * An opponent that moves horizontally with a constant speed.
 */
public class LinearOpponent extends Opponent {
	private String left = "resources/" + Main.OPPONENT_CHARACTER + "left.png";
	private String right = "resources/" + Main.OPPONENT_CHARACTER + "right.png";

	/**
	 * Constructor for opponent fishes.
	 * @param spawnsLeft whether the fish spawns left or right.
	 * @param xpos the x position.
	 * @param ypos the y position.
	 * @param size the size of the fish.
	 * @param speed the speed of the fish.
	 * @param loadResources whether the sprite resources should be loaded or not
	 */
	public LinearOpponent(boolean spawnsLeft, int xpos, int ypos, float size, int speed, boolean loadResources) {
		super(loadResources);
		loadResources(spawnsLeft ? right : left);
		position = new Vector(xpos, ypos);
		dimensions = new Vector(size, size);
		velocity = new Vector((spawnsLeft ? speed : -speed), 0);
		calculateBoundingbox();
	}

	/**
	 * * Moves the fish towards the left or right side of the screen.
	 * @param gc the container holding the game
	 * @param deltaTime the time difference the function was called last time in milliseconds.
	 */
	public void objectLogic(GameContainer gc, int deltaTime) {
	    position.add(velocity);
	    calculateBoundingbox();
	}
}
