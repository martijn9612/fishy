package nl.github.martijn9612.fishy;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;

/**
 * Implements an opponent.
 */
public class Opponent extends Entity {

    private String left = Main.OPPONENT_CHARACTER + "left";
    private String right = Main.OPPONENT_CHARACTER + "right";
    private boolean spawnsLeft;
    private double size;

    /**
     * Constructor for opponent fishes.
     * 
     * @param spawnsLeft
     *            - boolean which is true if the fish spawns left, false if fish
     *            spawns right
     * @param xpos
     *            - the x position.
     * @param ypos
     *            - the y position.
     * @param size
     *            - the size of the fish.
     * @param speed
     *            - the speed of the fish.
     */
    public Opponent(boolean spawnsLeft, int xpos, int ypos, double size,
            int speed) {
        this.spawnsLeft = spawnsLeft;
        this.size = size;
        if (spawnsLeft) {
            this.loadImage(right);
        } else {
            this.loadImage(left);
        }
        this.setPosition(xpos, ypos);
        this.setDimensions((int) Math.round(this.size),
                (int) Math.round(this.size));
        this.setSpeed(speed);
        this.calculateInitialBoundingbox();
    }

    /**
     * Moves the fish towards the left or right side of the screen, depending on
     * it's position.
     * 
     * @param gc
     *            - the container holding the game
     * @param deltaTime
     *            - the amount of time that has passed since last update in
     *            milliseconds
     */
    public void objectLogic(GameContainer gc, int deltaTime) {
        if (this.spawnsLeft) {
            this.x += speed;
            ellipse.setCenterX(ellipse.getCenterX() + speed);
        } else {
            this.x -= speed;
            ellipse.setCenterX(ellipse.getCenterX() - speed);
        }
    }

    /**
     * Check whether the enemy fish is off the screen.
     * 
     * @return true if the fish is off the screen, false if not
     */
    public boolean isOffScreen() {
        if (spawnsLeft) {
            return (this.x > (Display.getWidth() + this.size));
        } else {
            return (this.x < (0 - this.size));
        }
    }

    /**
     * Get method for size.
     * 
     * @return the size of the opponent
     */
    public double getSize() {
        return this.size;
    }

}
