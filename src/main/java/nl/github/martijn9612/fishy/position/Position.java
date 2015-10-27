package nl.github.martijn9612.fishy.position;

import nl.github.martijn9612.fishy.Main;

/**
 * Abstract class that defines the position of an entity.
 * The exact meaning of position depends on the subclass.
 * Software Engineering Methods Project - Group 11.
 */
public abstract class Position {
	protected int positionX = 0;
	protected int positionY = 0;
	
	/**
	 * Creates a new Position.
	 */
	public Position() {	
	}
	
	/**
	 * Creates a new Position.
	 * @param positionX - x coordinate of the new Position.
	 * @param positionY - y coordinate of the new Position.
	 */
	public Position(int positionX, int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	/**
	 * Gets the x coordinate of the current Position.
	 * @return x coordinate of the Position.
	 */
	public int getPositionX() {
		return positionX;
	}
	
	/**
	 * Gets the y coordinate of the current Position.
	 * @return y coordinate of the Position.
	 */
	public int getPositionY() {
		return positionY;
	}
	
	/** 
	 * Sets the x coordinate of the current Position
	 * to a new value.
	 * @param positionX - new x coordinate of Position.
	 */
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	
	/**
	 * Sets the y coordinate of the current Position
	 * to a new value.
	 * @param positionY - new y coordinate of Position.
	 */
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	/**
	 * Flips the position around the X axis.
	 */
	public void flipXAxis() {
		positionX = -positionX + Main.WINDOW_WIDTH;
	}
	
	/** 
	 * Flips the position around the Y axis.
	 */
	public void flipYAxis() {
		positionY = -positionY + Main.WINDOW_HEIGHT;
	}
}
