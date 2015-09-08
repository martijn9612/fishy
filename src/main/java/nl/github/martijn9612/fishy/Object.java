package nl.github.martijn9612.fishy;

import java.awt.Rectangle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;

public class Object {

  public Image objectImage;
  public int x, y, width, height, speed;
  public Rectangle objectRect;

  public void loadImage(String imageName) {
    try {
      objectImage = new Image("C:/Users/xox/Documents/fishy1/resources/" + imageName + ".png");
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

  public void createRectangle() {
    objectRect = new Rectangle(getX(), getY(), getWidth(), getHeight());
  }

  public void objectLogic(GameContainer gc, int deltaTime) {}

  public void renderObject(Graphics g) {}
}
