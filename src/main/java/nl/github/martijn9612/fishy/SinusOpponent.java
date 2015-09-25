package nl.github.martijn9612.fishy;

import org.newdawn.slick.GameContainer;

/**
 * Created by martijn on 23-9-15.
 */
public class SinusOpponent extends Opponent {
    private static final String SINUS_OPPONENT_IMAGE = "squid";
    private double size;
    private static final int SPAWN_HEIGHT = Main.WINDOW_HEIGHT;

    /**
     * Constructor for opponent fishes.
     * @param xpos the x position.
     * @param size the size of the fish.
     * @param speed the speed of the fish.
     */
    public SinusOpponent(int xpos, double size, int speed) {
        this.size = size;
        this.loadImage(SINUS_OPPONENT_IMAGE);
        this.setPosition(xpos, SPAWN_HEIGHT);
        this.setDimensions((int) Math.round(this.size), (int) Math.round(this.size));
        this.setSpeed(speed);
        this.calculateInitialBoundingbox();
    }

    /**
     * Moves the fish towards the left or right side of the screen.
     */
    public void objectLogic(GameContainer gc, int deltaTime) {
        double sin = Math.sin(Main.WINDOW_HEIGHT - this.y);
        if (sin < 0) {
            sin *= -1;
        }
        System.out.println(sin);
        speed = (int) Math.floor(4 * sin) + 1;
        this.y -= getSpeed();
        ellipse.setCenterY(ellipse.getCenterY() - getSpeed());
    }

//    public boolean isOffScreen() {
//        return this.y < 0;
//    }
}
