package nl.github.martijn9612.fishy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Player extends Object {

  private final String OBJECTID = "fish";

  public Player() {
    this.loadImage(OBJECTID);
    this.setPosition(350, 450);
    this.setDimensions(100, 100);
    this.setSpeed(1);
    this.createRectangle();
  }

  @Override
  public void objectLogic(GameContainer gc, int deltaTime) {
    Input input = gc.getInput();

    if (input.isKeyDown(Input.KEY_A)) {
      this.x -= speed;
      this.objectRect.x -= speed;
    }

    if (input.isKeyDown(Input.KEY_D)) {
      this.x += speed;
      this.objectRect.x += speed;
    }

    if (input.isKeyDown(Input.KEY_W)) {
      this.y -= speed;
      this.objectRect.y -= speed;
    }

    if (input.isKeyDown(Input.KEY_S)) {
      this.y += speed;
      this.objectRect.y += speed;
    }

    checkBounds();
  }

  @Override
  public void renderObject(Graphics g) {
    g.drawImage(this.getImage(), this.getX(), this.getY());
  }

  private void checkBounds() {
    if (this.x > 615) {
      this.x = 615;
    }

    if (this.x < 0) {
      this.x = 0;
    }

    if (this.y > 450) {
      this.y = 450;
    }
    
    if (this.y < 0){
      this.y = 0;
    }
  }
}
