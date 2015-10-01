package nl.github.martijn9612.fishy.opponents;

import org.newdawn.slick.GameContainer;

import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.models.Opponent;
import nl.github.martijn9612.fishy.models.Vector;

/**
 * Created by martijn on 23-9-15.
 */
public class SinusOpponent extends Opponent {
    private static final String SINUS_OPPONENT_IMAGE = "resources/squid.png";
    public static final int PIXELS_TO_HALT = 80;
    // PIXELS_TO_HALT / DIVIDER = MAX SPEED THE OPPONENT ACHIEVES
    public static final int DIVIDER = 20;
    private static final int SPAWN_HEIGHT = Main.WINDOW_HEIGHT;

    /**
     * Constructor for opponent fishes.
     * @param xpos the x position.
     * @param size the size of the fish.
     */
    public SinusOpponent(int xpos, float size, boolean loadResources) {
        super(loadResources);
        this.loadResources(SINUS_OPPONENT_IMAGE);
        position = new Vector(xpos, SPAWN_HEIGHT);
        dimensions = new Vector(size, size);
        calculateBoundingbox();
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
        calculateBoundingbox();
    }
}
