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
 * Implementation of DrawPosition class.
 * Software Engineering Methods Project - Group 11;
 */
public class DrawPosition extends Position {
	
    /**
     * Creates a new DrawPosition, based on Position class.
     * @param positionX - x coordinate of the new DrawPosition.
     * @param positionY - y coordinate of the new DrawPosition.
     */
	public DrawPosition(int positionX, int positionY) {
		super(positionX, positionY);
	}
	
	/**
	 * Draws the image on positionX and positionY.
	 * @param g - graphics content used.
	 * @param image - image to be drawn.
	 */
	public void drawImage(Graphics g, Image image) {
		g.drawImage(image, positionX, positionY);
	}
	
	/**
	 * Translates the Draw coordinate system into Mouse coordinates. 
	 * This is achieved by rotating the y-axis around its center.
	 * @return the MousePosition.
	 */
	public MousePosition getMousePosition() {
		MousePosition position = new MousePosition(positionX, positionY);
		position.flipYAxis();
		return position;
	}
	
	/**
	 * Debug method which formats the contents of 
	 * the DrawPosition object into a single String.
	 * @return string representation of the DrawPosition.
	 */
	public String toString() {
		return "Draw position X: " + positionX + ", Y: " + positionY;
	}
	
}
