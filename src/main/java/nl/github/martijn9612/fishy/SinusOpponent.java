package nl.github.martijn9612.fishy;

import org.newdawn.slick.GameContainer;

/**
 * Created by martijn on 23-9-15.
 */
public class SinusOpponent extends Opponent {
    private static final String SINUS_OPPONENT_IMAGE = "squid";
    public static final int PIXELS_TO_HALT = 80;
    // PIXELS_TO_HALT / DIVIDER = MAX SPEED THE OPPONENT ACHIEVES
    public static final int DIVIDER = 20;
    private double size;
    private static final int SPAWN_HEIGHT = Main.WINDOW_HEIGHT;

    /**
     * Constructor for opponent fishes.
     * @param xpos the x position.
     * @param size the size of the fish.
     */
    public SinusOpponent(int xpos, double size) {
        this.size = size;
        this.loadImage(SINUS_OPPONENT_IMAGE);
        this.setPosition(xpos, SPAWN_HEIGHT);
        this.setDimensions((int) Math.round(this.size), (int) Math.round(this.size));
        this.setSpeed(speed);
        this.calculateInitialBoundingbox();
    }

    /**
     * Moves the fish towards the left or right side of the screen.
     * @param gc the screen.
     * @param deltaTime time since the method was called last time in milliseconds.
     */
    public void objectLogic(GameContainer gc, int deltaTime) {
        if (this.y <= 0) {
            speed = 1;
        } else {
            speed = (this.y % PIXELS_TO_HALT) / DIVIDER + 1;
        }
        this.y -= getSpeed();
        ellipse.setCenterY(ellipse.getCenterY() - getSpeed());
    }
}
