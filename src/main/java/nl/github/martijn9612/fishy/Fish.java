package nl.github.martijn9612.fishy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Fish extends Object {
  private String left = "fishleft";
  private String right = "fishright";
  private boolean isleft;
  private int size;
  public Spawner spawn;

  /**
   * constructor for fishes.
   * @param isleft whether the fish spawns left or right.
   * @param xpos the x position.
   * @param ypos the y position.
   * @param size the size of the fish.
   * @param speed the speed of the fish.
   */
  public Fish(boolean isleft, int xpos, int ypos, int size, int speed, Spawner spawn) {
    this.isleft = isleft;
    this.size = 50 * size;
    this.spawn = spawn;
    if (isleft) {
      this.loadImage(right);
    } else {
      this.loadImage(left);
    }
    this.setPosition(xpos, ypos);
    this.setDimensions(this.size, this.size);
    this.setSpeed(speed);
    this.createRectangle();
  }
  /**
   * logic.
   */
  public void objectLogic(GameContainer gc, int deltaTime) {
    if (this.isleft) {
      goright();
    } else {
      goleft();
    }
  }
  
  @Override
  public void renderObject(Graphics g) {
    g.drawImage(this.getImage().getScaledCopy(this.getWidth(), this.getHeight()), this.getX(), this.getY());
  }
  /**
   * make the fish go left.
   */
  public void goright() {
    this.x += speed;
    this.objectRect.x += speed;
    if (this.x > (615 + this.size)) {
      destroy();
    }
  }
  /**
   * make the fish go right.
   */
  public void goleft() {
    this.x -= speed;
    this.objectRect.x -= speed;
    if (this.x < (0 - this.size)) {
      destroy();
    }
  }
  
  public void destroy() {
    spawn.destroy(this);
    System.out.println("destroy fish");
  }
}
