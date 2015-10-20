package nl.github.martijn9612.fishy.position;

public class MouseRectangle extends MousePosition {
	
	private int height;
	private int width;

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	public MouseRectangle(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}
	
	public MouseRectangle(int positionX, int positionY, int width, int height) {
		super(positionX, positionY);
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Checks whether the given mouse position is within the bounds of this rectangle object.
	 * @param {MousePosition} position coordinates in the Mouse coordinate system
	 * @return {boolean}
	 */
//	public boolean isInRectangle(MousePosition position) {
//		return position.isInRectangle(this);
//	}
	
	/**
	 * Translates the Mouse coordinate system into Draw coordinates. This is achieved by rotating the y-axis around
	 * its center and adding the sprite height to translate the origin from bottom left to the upper left corner.
	 * @return {DrawRectangle}
	 */
	public DrawRectangle getDrawRectangle() {
		DrawRectangle rectangle = new DrawRectangle(positionX, positionY, width, height);
		rectangle.flipYAxis();
		rectangle.setPositionY(rectangle.getPositionY() + height);
		return rectangle;
	}

	/**
	 * Debug method which formats the contents of the DrawRectangle object into a single String.
	 * @return {String}
	 */
	public String toString() {
		return new StringBuilder().append("Mouse rectangle X: ").append(positionX).append(", Y: ").append(positionY).append(", width: ").append(width).append(", height: ").append(height).toString();
	}
}
