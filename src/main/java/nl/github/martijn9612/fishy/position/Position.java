package nl.github.martijn9612.fishy.position;

import org.lwjgl.opengl.Display;

/**
 * Abstract class that defines the position of an entity.
 * The exact meaning of position depends on the subclass.
 */
public abstract class Position {
	
	protected int positionX = 0;
	protected int positionY = 0;
	
	public Position() {
		
	}
	
	public Position(int positionX, int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	public int getPositionX() {
		return positionX;
	}
	
	public int getPositionY() {
		return positionY;
	}
	
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	public void flipXAxis() {
		positionX = -positionX + Display.getWidth();
	}
	
	public void flipYAxis() {
		positionY = -positionY + Display.getHeight();
	}
}
