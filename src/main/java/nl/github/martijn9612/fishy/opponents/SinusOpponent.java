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
     * Constructor for opponent fishes.
     * @param player the x position.
     * @param random the size of the fish.
     */
    public SinusOpponent(Vector position, Vector dimensions, boolean loadResources) {
        super(loadResources);
        loadResources(SINUS_SPRITE_PATH);
        this.position = position;
        this.dimensions = dimensions;
        updateBoundingbox();
    }

	public static SinusOpponent createRandom(Player player, Random random, boolean loadResources) {
		Vector dimensions = getRandomDimensions(player, random);
		Vector position = getRandomPosition(random, dimensions);
		return new SinusOpponent(position, dimensions, loadResources);
	}

	private static Vector getRandomDimensions(Player player, Random random) {
		int maxSize = (int) (player.getSize() * 2.5);
		int minSize = (int) (player.getSize() * 0.5);
		int size = (random.nextInt((maxSize - minSize)) + minSize);
		return new Vector(size, size);
	}

    private static Vector getRandomPosition(Random random, Vector dimensions) {
    	int min = (int) Math.round(dimensions.x);
    	int max = 615 - min;
		int xpos = random.nextInt(Math.abs(max - min)) + min;
    	return new Vector(xpos, SPAWN_HEIGHT);
	}

	/**
     * Moves the fish towards the left or right side of the screen.
     * @param gc the screen.
     * @param deltaTime time since the method was called last time in milliseconds.
     */
    public void objectLogic(GameContainer gc, int deltaTime) {
        if (position.y <= 0) {
            velocity = new Vector(0, -1);
        } else {
        	velocity = new Vector(0, -((position.y % PIXELS_TO_HALT) / DIVIDER + 1));
        }
        position.add(velocity);
        updateBoundingbox();
    }

	@Override
	public void destroy() {
		// Blank
	}
    
    
}
