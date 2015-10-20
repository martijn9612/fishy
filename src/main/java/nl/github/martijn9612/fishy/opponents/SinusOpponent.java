package nl.github.martijn9612.fishy.opponents;

import java.util.Random;

import org.newdawn.slick.GameContainer;

import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.models.Moveable;
import nl.github.martijn9612.fishy.models.NonPlayer;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.models.Vector;

/**
 * Implementation of the SinusOpponent class. 
 * A SinusOpponent is a small opponent that moves
 * vertically through the map in a sinusoid motion.
 * Software Engineering Methods Project - Group 11.
 */
public class SinusOpponent extends NonPlayer {
    private static final String SINUS_SPRITE_PATH = "resources/squid.png";
    public static final int PIXELS_TO_HALT = 80;
    // PIXELS_TO_HALT / DIVIDER = MAX SPEED THE OPPONENT ACHIEVES
    public static final int DIVIDER = 20;
    private static final int SPAWN_HEIGHT = Main.WINDOW_HEIGHT;

    /**
	 * Constructor for an opponent that moves in a sinusoid motion.
     * @param data - the moveable data the SinusOpponent will have.
     * @param loadResources - true when OpenGL context should be loaded, false if not.
     */
    public SinusOpponent(Moveable data, boolean loadResources) {
        super(data, loadResources);
        loadResources(SINUS_SPRITE_PATH);
    }
    
    /**
	 * Creates an instance of SinusOpponent at the bottom of the screen.
     * @param player - the current Player in the game.
     * @param random - an instance to generate random numbers.
     * @param loadResources - true when OpenGL context should be loaded, false if not.
     * @return new SinusOpponent
	 */
	public static SinusOpponent createRandom(Player player, Random random, boolean loadResources) {
		Moveable data = new Moveable();
		data.dimensions = getRandomDimensions(player, random);
		data.position = getRandomPosition(random, data.dimensions);
		return new SinusOpponent(data, loadResources);
	}
	
    /**
     * Creates a random dimension vector for the SinusOpponent class.
     * @param player - the current Player in the game.
     * @param random - an instance to generate random numbers.
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
     * @param random - an instance to generate random numbers.
     * @param dimensions - Vector with the dimensions of the LinearOpponent.
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
     * @param gc - the container holding the game.
     * @param deltaTime - time elapsed since method was called in milliseconds.
     */
    @Override
    public void objectLogic(GameContainer gc, int deltaTime) {
        if (data.position.y <= 0) {
        	data.velocity = new Vector(0, -1);
        } else {
        	data.velocity = new Vector(0, -((data.position.y % PIXELS_TO_HALT) / DIVIDER + 1));
        }
        data.updatePosition(100);
        updateBoundingbox();
    }
}
