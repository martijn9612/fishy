package nl.github.martijn9612.fishy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Ellipse;

public class Entity {

	public Image objectImage;
	public int x, y, width, height, speed;
	public Ellipse ellipse;

	public void loadImage(String imageName) {
		try {
			objectImage = new Image("resources/" + imageName + ".png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

  	public int getSpeed() {
	  	return speed;
  	}

  	public Image getImage() {
  		return objectImage;
  	}
  
 	public void calculateBoundingbox() {
 		ellipse.setCenterX(getX() + getWidth() / 2);
        ellipse.setCenterY(getY() + getHeight() / 2);
        ellipse.setRadii(getWidth() / 2, getHeight() / 2);
 	}

    public void calculateInitialBoundingbox() {
        ellipse = new Ellipse(getX() + getWidth() / 2, getY() + getHeight() / 2, getWidth() / 2, getHeight() / 2);
    }

	public void renderObject(Graphics g) {
        calculateBoundingbox();
		g.drawImage(objectImage.getScaledCopy(width, height), x, y);
        if (Main.DRAWBOUNDINGBOXES) {
          g.drawOval(getX(), getY(), ellipse.getWidth(), ellipse.getHeight());
        }
	}
	
	public void objectLogic(GameContainer gc, int deltaTime) {}
	
}
