package nl.github.martijn9612.fishy.position;

import org.lwjgl.input.Mouse;

/**
 * Mouse coordinate system, which has the bottom left corner defined as (0,0)
 * 
 * (0,y) ------ (x,y) 
 *   |  .         | 
 *   |     .      | 
 *   |        .   | 
 * (0,0) ------ (x,0)
 * 
 * Implements the MousePosition class. Software Engineering Methods Project -
 * Group 11.
 */
public class MousePosition extends Position {

    /**
     * Creates a new MousePosition by using the method updatePosition.
     */
    public MousePosition() {
        updatePosition();
    }

    /**
     * Creates a new MousePosition.
     * 
     * @param positionX
     *            - x coordinate of the new MousePosition.
     * @param positionY
     *            - y coordinate of the new MousePosition.
     */
    public MousePosition(int positionX, int positionY) {
        super(positionX, positionY);
    }

    /**
     * Updates the mousePosition to the current location of the mouse.
     */
    public void updatePosition() {
        positionX = Mouse.getX();
        positionY = Mouse.getY();
    }

    /**
     * Checks if the x coordinate of the MousePosition is in between two bounds.
     * 
     * @param lowerBound
     *            - lower bound of the x coordinate.
     * @param upperBound
     *            - upper bound of the x coordinate.
     * @return true if the x coordinate of the MousePosition is in between the
     *         two bounds, false if not.
     */
    public boolean isInBetweenX(int lowerBound, int upperBound) {
        return (positionX > lowerBound && positionX < upperBound);
    }

    /**
     * Checks if the y coordinate of the MousePosition is in between two bounds.
     * 
     * @param lowerBound
     *            - lower bound of the y coordinate.
     * @param upperBound
     *            - upper bound of the y coordinate.
     * @return true if the y coordinate of the MousePosition is in between the
     *         two bounds, false if not.
     */
    public boolean isInBetweenY(int lowerBound, int upperBound) {
        return (positionY > lowerBound && positionY < upperBound);
    }

    /**
     * Checks whether the left button of the Mouse is down.
     * 
     * @return true if the button is down, false if not.
     */
    public boolean isLeftButtonDown() {
        return Mouse.isButtonDown(0);
    }

    /**
     * Checks if the MousePosition is within the bounds of a certain Rectangle.
     * 
     * @param lowerBoundX
     *            - lower bound of the x coordinate.
     * @param upperBoundX
     *            - upper bound of the x coordinate.
     * @param lowerBoundY
     *            - lower bound of the y coordinate.
     * @param upperBoundY
     *            - upper bound of the y coordinate.
     * @return true if the MousePosition is in the rectangle, false if not.
     */
    public boolean isInRectangle(int lowerBoundX, int upperBoundX,
            int lowerBoundY, int upperBoundY) {
        return (isInBetweenX(lowerBoundX, upperBoundX) && isInBetweenY(
                lowerBoundY, upperBoundY));
    }

    /**
     * Checks whether this MousePosition is within the bounds of the given
     * rectangle object.
     * 
     * @param rectangle
     *            - rectangle object to verify.
     * @return true if the MousePosition is in the rectangle, false if not.
     */
    public boolean isInRectangle(MouseRectangle rectangle) {
        int lowerBoundX = rectangle.getPositionX();
        int upperBoundX = lowerBoundX + rectangle.getWidth();
        int lowerBoundY = rectangle.getPositionY();
        int upperBoundY = lowerBoundY + rectangle.getHeight();
        return (isInRectangle(lowerBoundX, upperBoundX, lowerBoundY,
                upperBoundY));
    }

    /**
     * Translates the Mouse coordinate system into Draw coordinates. This is
     * achieved by rotating the y-axis around its center.
     * 
     * @return the DrawPosition of the current MousePosition.
     */
    public DrawPosition getDrawPosition() {
        DrawPosition position = new DrawPosition(positionX, positionY);
        position.flipYAxis();
        return position;
    }

    /**
     * Debug method which formats the contents of the MousePosition object into
     * a single String.
     * 
     * @return String representation of the MousePosition.
     */
    public String toString() {
        return new StringBuilder().append("Mouse position X: ")
                .append(positionX).append(", Y: ").append(positionY).toString();

    }

}
