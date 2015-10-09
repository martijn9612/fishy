package nl.github.martijn9612.fishy.opponents;

import java.util.Random;

import org.newdawn.slick.GameContainer;

import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.models.Entity;
import nl.github.martijn9612.fishy.models.NonPlayer;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.models.Vector;

/**
 * An opponent that moves horizontally with a constant speed.
 */
public class LinearOpponent extends NonPlayer {
	private static final String SPRITE_PATH = "resources/opponent-" + Main.OPPONENT_CHARACTER + ".png";

	/**
	 * Constructor for an opponent that moves in a linear motion.
	 * 
	 * @param dimensions size of the new opponent.
	 * @param position vector with the start position of the opponent.
	 * @param velocity initial speed of the opponent.
	 * @param acceleration initial acceleration of the opponent.
	 * @param loadResources whether the sprite resources should be loaded.
	 */
	public LinearOpponent(Vector dimensions, Vector position, Vector velocity, Vector acceleration, boolean loadResources) {
		super(dimensions, position, velocity, acceleration, loadResources);
		loadResources(SPRITE_PATH);
		if(loadResources && velocity.x > 0) {
			setImageOrientation(Entity.IMAGE_ORIENTATE_RIGHT);
		}
	}
	
	/**
	 * Creates an instance of LinearOpponent at a random screen side location.
	 * 
	 * @param player an instance of the Player class.
	 * @param random an instance to generate random numbers.
	 * @param loadResources whether the sprite resources should be loaded.
	 */
	public static LinearOpponent createRandom(Player player, Random random, boolean loadResources) {
		boolean spawnsLeft = random.nextBoolean();
		Vector acceleration = new Vector(0,0);
		Vector dimensions = getRandomDimensions(player, random);
		Vector velocity = getRandomVelocity(random, spawnsLeft);
		Vector position = getRandomPosition(random, spawnsLeft, dimensions);
		return new LinearOpponent(dimensions, position, velocity, acceleration, loadResources);
	}
	
	/**
	 * Creates a random dimension vector for the LineairOpponent class.
	 * 
	 * @param player an instance of the Player class.
	 * @param random an instance to generate random numbers.
	 * @return Vector with opponent size.
	 */
	private static Vector getRandomDimensions(Player player, Random random) {
		int maxSize = (int) (player.getSize() * 2);
		int minSize = (int) (player.getSize() * 0.5);
		int size = (random.nextInt((maxSize - minSize)) + minSize);
		return new Vector(size, size);
	}
	
	/**
	 * Creates a random position vector for the LineairOpponent class.
	 * 
	 * @param player an instance of the Player class.
	 * @param random an instance to generate random numbers.
	 * @return Vector with opponent location.
	 */
	private static Vector getRandomPosition(Random random, boolean spawnsLeft, Vector dimensions) {
		int min = Math.round(dimensions.x);
		int max = 515 - min;
		int ypos = random.nextInt(Math.abs(max - min)) + min;
		int xpos = (spawnsLeft ? 0 - min * 5 : 615 + min * 5);
		return new Vector(xpos, ypos);
	}
	
	/**
	 * Creates a random velocity vector for the LineairOpponent class.
	 * 
	 * @param player an instance of the Player class.
	 * @param random an instance to generate random numbers.
	 * @return Vector with opponent velocity.
	 */
	private static Vector getRandomVelocity(Random random, boolean spawnsLeft) {
		int speed = random.nextInt(4) + 1;
		return new Vector((spawnsLeft ? speed : -speed), 0);
	}

	/**
	 * Moves the fish towards the left or right side of the screen.
	 * 
	 * @param gc the container holding the game.
	 * @param deltaTime time elapsed since method was called in milliseconds.
	 */
	@Override
	public void objectLogic(GameContainer gc, int deltaTime) {
	    position.add(velocity);
	    updateBoundingbox();
	}
}
