package nl.github.martijn9612.fishy;

import org.newdawn.slick.GameContainer;

public class LinearOpponent extends Opponent {
	private String left = Main.OPPONENT_CHARACTER + "left";
	private String right = Main.OPPONENT_CHARACTER + "right";
	private boolean spawnsLeft;

	/**
	 * Constructor for opponent fishes.
	 * @param spawnsLeft whether the fish spawns left or right.
	 * @param xpos the x position.
	 * @param ypos the y position.
	 * @param size the size of the fish.
	 * @param speed the speed of the fish.
	 */
	public LinearOpponent(boolean spawnsLeft, int xpos, int ypos, double size, int speed) {
		System.out.println(xpos + " " + ypos + " " + size);
		this.size = size;
		this.spawnsLeft = spawnsLeft;
		this.loadImage(spawnsLeft ? right : left);
		this.setPosition(xpos, ypos);
		this.setDimensions((int) Math.round(this.size), (int) Math.round(this.size));
		this.setSpeed(speed);
		this.calculateInitialBoundingbox();
	}
  
	/**
	 * Moves the fish towards the left or right side of the screen.
	 */
	public void objectLogic(GameContainer gc, int deltaTime) {
	    if (this.spawnsLeft) {
	    	this.x += speed;
		    ellipse.setCenterX(ellipse.getCenterX() + getSpeed());
	    } else {
	    	this.x -= speed;
          ellipse.setCenterX(ellipse.getCenterX() - getSpeed());
	    }
	}
	
//	public boolean isOffScreen() {
//		if (spawnsLeft) {
//			return (this.x > (Display.getWidth() + this.size));
//		} else {
//			return (this.x < (0 - this.size));
//		}
//	}
}
