package nl.github.martijn9612.fishy;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;

public class Opponent extends Entity {
	
	private String left = Main.OPPONENT_CHARACTER + "left";
	private String right = Main.OPPONENT_CHARACTER + "right";
	private boolean spawnsLeft;
	private double size;

	/**
	 * Constructor for opponent fishes.
	 * @param spawnsLeft whether the fish spawns left or right.
	 * @param xpos the x position.
	 * @param ypos the y position.
	 * @param size the size of the fish.
	 * @param speed the speed of the fish.
	 */
	public Opponent(boolean spawnsLeft, int xpos, int ypos, double size, int speed) {
		this.spawnsLeft = spawnsLeft;
		this.size = size;
		this.loadImage(spawnsLeft ? right : left);
		this.setPosition(xpos, ypos);
		this.setDimensions((int) Math.round(this.size), (int) Math.round(this.size));
		this.setSpeed(speed);
		this.calculateRectangle();
	}
  
	/**
	 * Moves the fish towards the left or right side of the screen.
	 */
	public void objectLogic(GameContainer gc, int deltaTime) {
	    if (this.spawnsLeft) {
	    	this.x += speed;
		    this.objectRect.x += speed;
	    } else {
	    	this.x -= speed;
			this.objectRect.x -= speed;
	    }
	}
	
	public boolean isOffScreen() {
		if(spawnsLeft) {
			return (this.x > (Display.getWidth() + this.size));
		} else {
			return (this.x < (0 - this.size));
		}
	}

	public double getSize() {
		return this.size;
	}

}
