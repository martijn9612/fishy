package nl.github.martijn9612.fishy.position;

import org.lwjgl.input.Mouse;

/**
 * Mouse coordinate system, which has the bottom left corner defined as (0,0)
 * 
 * (0,y) ------ (x,y)
 *   |        .   |
 *   |     .      |
 *   |  .         |
 * (0,0) ------ (x,0)
 * 
 */
public class MousePosition extends Position {

	public MousePosition() {
		updatePosition();
	}
	
	public MousePosition(int positionX, int positionY) {
		super(positionX, positionY);
	}
	
	public void updatePosition() {
		positionX = Mouse.getX();
		positionY = Mouse.getY();
	}
	
	public boolean isInBetweenX(int lowerBound, int upperBound) {
		return (positionX > lowerBound && positionX < upperBound);
	}
	
	public boolean isInBetweenY(int lowerBound, int upperBound) {
		return (positionY > lowerBound && positionY < upperBound);
	}
	
	public boolean isLeftButtonDown() {
		return Mouse.isButtonDown(0);
	}
	
	public boolean isInRectangle(int lowerBoundX, int upperBoundX, int lowerBoundY, int upperBoundY) {
		return (isInBetweenX(lowerBoundX, upperBoundX) && isInBetweenY(lowerBoundY, upperBoundY));
	}
	
	/**
	 * Checks whether this Mouse position is within the bounds of the given rectangle object.
	 * @param {MouseRectangle} rectangle object to verify
	 * @return {boolean}
	 */
	public boolean isInRectangle(MouseRectangle rectangle) {
		int lowerBoundX = rectangle.getPositionX();
		int upperBoundX = lowerBoundX + rectangle.getWidth();
		int lowerBoundY = rectangle.getPositionY();
		int upperBoundY = lowerBoundY + rectangle.getHeight();
		return (isInRectangle(lowerBoundX, upperBoundX, lowerBoundY, upperBoundY));
	}
	
	/**
	 * Translates the Mouse coordinate system into Draw coordinates. This is achieved by rotating the y-axis around its center.
	 * @return {DrawPosition}
	 */
	public DrawPosition getDrawPosition() {
		DrawPosition position = new DrawPosition(positionX, positionY);
		position.flipYAxis();
		return position;
	}

	/**
	 * Debug method which formats the contents of the MousePosition object into a single String.
	 * @return {String}
	 */
	public String toString() {
		return new StringBuilder().append("Mouse position X: ").append(positionX).append(", Y: ").append(positionY).toString();
	}

}
