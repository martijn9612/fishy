package nl.github.martijn9612.fishy.opponents;

import java.util.Random;

import org.newdawn.slick.GameContainer;

import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.models.Opponent;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.models.Vector;

/**
 * Created by martijn on 23-9-15.
 */
public class SinusOpponent extends Opponent {
    private static final String SINUS_SPRITE_PATH = "resources/squid.png";
    public static final int PIXELS_TO_HALT = 80;
    // PIXELS_TO_HALT / DIVIDER = MAX SPEED THE OPPONENT ACHIEVES
    public static final int DIVIDER = 20;
    private static final int SPAWN_HEIGHT = Main.WINDOW_HEIGHT;

    /**
	 * Constructor for an opponent that moves in a sinusoid motion.
	 * 
	 * @param dimensions size of the new opponent.
	 * @param position vector with the start position of the opponent.
	 * @param velocity initial speed of the opponent.
	 * @param acceleration initial acceleration of the opponent.
	 * @param loadResources whether the sprite resources should be loaded.
	 */
    public SinusOpponent(Vector dimensions, Vector position, Vector velocity, Vector acceleration, boolean loadResources) {
        super(dimensions, position, velocity, acceleration, loadResources);
        loadResources(SINUS_SPRITE_PATH);
    }
    
    /**
	 * Creates an instance of SinusOpponent at the bottom of the screen.
	 * 
	 * @param player an instance of the Player class.
	 * @param random an instance to generate random numbers.
	 * @param loadResources whether the sprite resources should be loaded.
	 */
	public static SinusOpponent createRandom(Player player, Random random, boolean loadResources) {
		Vector velocity = new Vector(0,0);
        Vector acceleration = new Vector(0,0);
		Vector dimensions = getRandomDimensions(player, random);
		Vector position = getRandomPosition(random, dimensions);
		return new SinusOpponent(dimensions, position, velocity, acceleration, loadResources);
	}
	
	/**
	 * Creates a random dimension vector for the SinusOpponent class.
	 * 
	 * @param player an instance of the Player class.
	 * @param random an instance to generate random numbers.
	 * @return Vector with opponent size.
	 */
	private static Vector getRandomDimensions(Player player, Random random) {
		int maxSize = (int) (player.getSize() * 2.0);
		int minSize = (int) (player.getSize() * 0.5);
		int size = (random.nextInt((maxSize - minSize)) + minSize);
		return new Vector(size, size);
	}
	
	/**
	 * Creates a random position vector for the SinusOpponent class.
	 * 
	 * @param player an instance of the Player class.
	 * @param random an instance to generate random numbers.
	 * @return Vector with opponent location.
	 */
    private static Vector getRandomPosition(Random random, Vector dimensions) {
    	int min = (int) Math.round(dimensions.x);
    	int max = 615 - min;
		int xpos = random.nextInt(Math.abs(max - min)) + min;
    	return new Vector(xpos, SPAWN_HEIGHT);
	}

	/**
     * Moves the opponent towards the top of the screen in a sinusoid motion.
     * 
     * @param gc the container holding the game.
     * @param deltaTime time elapsed since method was called in milliseconds.
     */
    @Override
    public void objectLogic(GameContainer gc, int deltaTime) {
        if (position.y <= 0) {
            velocity = new Vector(0, -1);
        } else {
        	velocity = new Vector(0, -((position.y % PIXELS_TO_HALT) / DIVIDER + 1));
        }
        position.add(velocity);
        updateBoundingbox();
    }
}
