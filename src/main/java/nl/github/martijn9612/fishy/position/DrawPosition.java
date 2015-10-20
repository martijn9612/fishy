package nl.github.martijn9612.fishy.position;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * Graphics coordinate system, which has the upper left corner defined as (0,0).
 * 
 * (0,0) ------ (x,0)
 *   |  .         |
 *   |     .      |
 *   |        .   |
 * (0,y) ------ (x,y)
 * 
 */
public class DrawPosition extends Position {
	public DrawPosition(int positionX, int positionY) {
		super(positionX, positionY);
	}
	
	public void drawImage(Graphics g, Image image) {
		g.drawImage(image, positionX, positionY);
	}
	
	/**
	 * Translates the Draw coordinate system into Mouse coordinates. This is achieved by rotating the y-axis around its center.
	 * @return {MousePosition}
	 */
	public MousePosition getMousePosition() {
		MousePosition position = new MousePosition(positionX, positionY);
		position.flipYAxis();
		return position;
	}

	/**
	 * Debug method which formats the contents of the DrawPosition object into a single String.
	 * @return {String}
	 */
	public String toString() {
		return new StringBuilder().append("Draw position X: ").append(positionX).append(", Y: ").append(positionY).toString();
	}
}
