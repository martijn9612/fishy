package nl.github.martijn9612.fishy.position;

/**
 * Implementation of DrawRectangle class. Software Engineering Methods Project -
 * Group 11.
 */
public class DrawRectangle extends DrawPosition {

    private int width;
    private int height;

    /**
     * Gets the height of the DrawRectangle.
     * 
     * @return height of the DrawRectangle.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the width of the DrawRectangle.
     * 
     * @return width of the DrawRectangle.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Creates a new DrawRectangle.
     * 
     * @param positionX
     *            - x coordinate of the new DrawRectangle.
     * @param positionY
     *            - y coordinate of the new DrawRectangle.
     * @param width
     *            - width of the new DrawRectangle.
     * @param height
     *            - height of the new DrawRectangle.
     */
    public DrawRectangle(int positionX, int positionY, int width, int height) {
        super(positionX, positionY);
        this.width = width;
        this.height = height;
    }

    /**
     * Translates the Draw coordinate system into Mouse coordinates. This is
     * achieved by rotating the y-axis around its center and subtracting the
     * sprite height to translate the origin from upper left to bottom left
     * corner.
     * 
     * @return new MouseRectangle.
     */
    public MouseRectangle getMouseRectangle() {
        MouseRectangle rectangle = new MouseRectangle(positionX, positionY,
                width, height);
        rectangle.flipYAxis();
        rectangle.setPositionY(rectangle.getPositionY() - height);
        return rectangle;
    }

    /**
     * Debug method which formats the contents of the DrawRectangle object into
     * a single String.
     * 
     * @return string representation of the DrawRectangle object.
     */
    public String toString() {
        return new StringBuilder().append("Draw rectangle X: ")
                .append(positionX).append(", Y: ").append(positionY)
                .append(", width: ").append(width).append(", height: ")
                .append(height).toString();
    }
}
