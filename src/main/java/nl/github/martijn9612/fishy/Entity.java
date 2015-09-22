package nl.github.martijn9612.fishy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Ellipse;
/**
 * Implements the basic object in the game on which players and opponents are based.
 */
public class Entity {
	public Image objectImage;
	public int x, y, width, height, speed;
	public Ellipse ellipse;

	/**
	 * Loads the sprite of the entity.
	 * @param imageName the name of the file in which the sprite is stored
	 */
	public void loadImage(String imageName) {
		try {
			objectImage = new Image("resources/" + imageName + ".png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Setter method for the position of the entity.
	 * @param x - x position of the entity on the screen
	 * @param y - y position of the entity on the screen
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Setter method for the dimensions of the entity.
	 * @param width - width of the entity
	 * @param height - height of the entity
	 */
	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Setter method for the speeds of the entity.
	 * @param speed - speed of the entity
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * Get method for variable x.
	 * @return the x position of the entity
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Get method for variable y.
	 * @return the y position of the entity
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Get method for variable width.
	 * @return the width of the entity
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Get method for variable height.
	 * @return the height of the entity
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Get method for variable speed.
	 * @return the speed of the entity
	 */
  	public int getSpeed() {
	  	return speed;
  	}

  	/**
  	 * Get method for variable objectImage.
  	 * @return the Image objectImage
  	 */
  	public Image getImage() {
  		return objectImage;
  	}
  
  	/**
  	 * Calculates the boundary box around the entity with the right size and center.
  	 */
 	public void calculateBoundingbox() {
 		ellipse.setCenterX(getX() + getWidth() / 2);
        ellipse.setCenterY(getY() + getHeight() / 2);
        ellipse.setRadii(getWidth() / 2, getHeight() / 2);
 	}

 	/**
 	 * Set up the initial boundary box around the entity.
 	 */
    public void calculateInitialBoundingbox() {
        ellipse = new Ellipse(getX() + getWidth() / 2, getY() + getHeight() / 2, 
                getWidth() / 2, getHeight() / 2);
    }

    /**
     * Renders the object and the boundary box around it.
     * @param g - the graphics content used to render
     */
	public void renderObject(Graphics g) {
        calculateBoundingbox();
		g.drawImage(objectImage.getScaledCopy(width, height), x, y);
        if (Main.DRAWBOUNDINGBOXES) {
          g.drawOval(getX(), getY(), ellipse.getWidth(), ellipse.getHeight());
        }
	}
	/**
	 * Initial setup for the objectLogic of every object.
	 * @param gc - the container holding the game
	 * @param deltaTime - timeframe
	 */
	public void objectLogic(GameContainer gc, int deltaTime) { }
	
}
