package nl.github.martijn9612.fishy;

/**
 * Created by martijn on 23-9-15.
 */
public class Opponent extends Entity {
    // OPPONENT_FACTOR determines the distance the opponent has to be outside of the level
    // boundaries to be considered out of the screen.
    public static final int OPPONENT_FACTOR = 5;
    public double size;

    /**
     * Checks whether the opponent is visible on the screen.
     * @return returns true if the opponent is not visible on the screen
     */
    public boolean isOffScreen() {
        if (x < 0 - width * OPPONENT_FACTOR - 1 || x > Main.WINDOW_WIDTH + width * OPPONENT_FACTOR + 1) {
            return true;
        }
        if (y < 0 - height * OPPONENT_FACTOR - 1 || y > Main.WINDOW_HEIGHT + height * OPPONENT_FACTOR + 1) {
            return true;
        }
        return false;
    }

    /**
     * Returns the size of the opponent.
     * @return size value
     */
    public double getSize() {
        return this.size;
    }
}
