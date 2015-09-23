package nl.github.martijn9612.fishy;

/**
 * Created by martijn on 23-9-15.
 */
public class Opponent extends Entity {
    public double size;

    public boolean isOffScreen() {
        if (x < 0 - width * 5 - 1 || x > Main.WINDOW_WIDTH + width * 5 + 1) {
            return true;
        }
        if (y < 0 - height * 5 - 1 || y > Main.WINDOW_HEIGHT + height * 5 + 1) {
            return true;
        }
        return false;
    }

    public double getSize() {
        return this.size;
    }
}
