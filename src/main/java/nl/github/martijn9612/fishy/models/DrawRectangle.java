package nl.github.martijn9612.fishy.models;

public class DrawRectangle extends DrawPosition {

	private int width;
	private int height;

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public DrawRectangle(int positionX, int positionY, int width, int height) {
		super(positionX, positionY);
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Translates the Draw coordinate system into Mouse coordinates. This is achieved by rotating the y-axis around
	 * its center and subtracting the sprite height to translate the origin from upper left to bottom left corner.
	 * @return {MouseRectangle}
	 */
	public MouseRectangle getMouseRectangle() {
		MouseRectangle rectangle = new MouseRectangle(positionX, positionY, width, height);
		rectangle.flipYAxis();
		rectangle.setPositionY(rectangle.getPositionY() - height);
		return rectangle;
	}
	
	/**
	 * Debug method which formats the contents of the DrawRectangle object into a single String.
	 * @return {String}
	 */
	public String toString() {
		return new String("Draw rectangle X: "+positionX+", Y: "+positionY+", width: "+width+", height: "+height);
	}
}
