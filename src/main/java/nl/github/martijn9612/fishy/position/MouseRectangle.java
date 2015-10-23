package nl.github.martijn9612.fishy.position;

/**
 * Implementation of the MouseRectangle class. Software Engineering Methods
 * Project - Group 11.
 */
public class MouseRectangle extends MousePosition {

    private int height;
    private int width;

    /**
     * Gets the height of the current MouseRectangle.
     * 
     * @return the height of the MouseRectangle.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the width of the current MouseRectangle.
     * 
     * @return the width of the MouseRectangle.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Creates a new MouseRectangle.
     * 
     * @param width
     *            - width of the new MouseRectangle.
     * @param height
     *            - height of the new MouseRectangle.
     */
    public MouseRectangle(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }

    /**
     * Creates a new MouseRectangle.
     * 
     * @param positionX
     *            - x coordinate of the MouseRectangle.
     * @param positionY
     *            - y coordinate of the MouseRectangle.
     * @param width
     *            - width of the new MouseRectangle.
     * @param height
     *            - height of the new MouseRectangle.
     */
    public MouseRectangle(int positionX, int positionY, int width, int height) {
        super(positionX, positionY);
        this.width = width;
        this.height = height;
    }

    /**
     * Checks whether the given mouse position is within the bounds of this
     * rectangle object.
     * 
     * @param position
     *            - position coordinates in the Mouse coordinate system
     * @return true if the MousePosition is within the bounds of the rectangle,
     *         false if not.
     */
    // public boolean isInRectangle(MousePosition position) {
    // return position.isInRectangle(this);
    // }

    /**
     * Translates the Mouse coordinate system into Draw coordinates. This is
     * achieved by rotating the y-axis around its center and adding the sprite
     * height to translate the origin from bottom left to the upper left corner.
     * 
     * @return the DrawRectangle of the MouseRectangle.
     */
    public DrawRectangle getDrawRectangle() {
        DrawRectangle rectangle = new DrawRectangle(positionX, positionY,
                width, height);
        rectangle.flipYAxis();
        rectangle.setPositionY(rectangle.getPositionY() + height);
        return rectangle;
    }

    /**
     * Debug method which formats the contents of the DrawRectangle object into
     * a single String.
     * 
     * @return String representation of the MouseRectangle.
     */
    public String toString() {
        return new StringBuilder().append("Mouse rectangle X: ")
                .append(positionX).append(", Y: ").append(positionY)
                .append(", width: ").append(width).append(", height: ")
                .append(height).toString();
    }
}
