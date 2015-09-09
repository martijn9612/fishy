package nl.github.martijn9612.fishy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Player extends Object {
  private String left = "fishleft";
  private String right = "fishright";
  private int decreaseLeft, accelerateLeft, speedLeft = 0;
  private int decreaseRight, accelerateRight, speedRight = 0;
  private int decreaseUp, accelerateUp, speedUp = 0;
  private int decreaseDown, accelerateDown, speedDown = 0;

  /**
   * constructor for the player.
   */
  public Player() {
    this.loadImage(left);
    this.setPosition(350, 450);
    this.setDimensions(100, 100);
    this.setSpeed(1);
    this.createRectangle();
  }

  /**
   * updates the object logic, also used for controls.
   */
  @Override
  public void objectLogic(GameContainer gc, int deltaTime) {
    Input input = gc.getInput();

    if (input.isKeyDown(Input.KEY_A)) {
      this.loadImage(left);
      left(1);
    } else {
      left(-1);
    }

    if (input.isKeyDown(Input.KEY_D)) {
      this.loadImage(right);
      right(1);
    } else {
      right(-1);
    }

    if (input.isKeyDown(Input.KEY_W)) {
      up(1);
    } else {
      up(-1);
    }

    if (input.isKeyDown(Input.KEY_S)) {
      down(1);
    } else {
      down(-1);
    }

    checkBounds();
    momentum(accelerateLeft, accelerateRight, accelerateUp, accelerateDown);
  }

  @Override
  public void renderObject(Graphics graph) {
    graph.drawImage(this.getImage(), this.getX(), this.getY());
  }

  private void checkBounds() {
    if (this.x > 615) {
      this.x = 615;
    }

    if (this.x < 0) {
      this.x = 0;
    }

    if (this.y > 515) {
      this.y = 515;
    }

    if (this.y < 0) {
      this.y = 0;
    }
  }

  /**
   * controls the acceleration and deceleration to the left
   * 
   * @param accel
   *          wether to increase or decrease speed.
   */
  private void left(int accel) {
    this.x -= speed + speedLeft;
    this.objectRect.x -= speed + speedLeft;
    decreaseLeft++;
    if (decreaseLeft == 5) {
      decreaseLeft = 0;
      accelerateLeft = accel;
    }
  }

  /**
   * controls the acceleration and deceleration to the right
   * 
   * @param accel
   *          wether to increase or decrease speed.
   */
  private void right(int accel) {
    this.x += speed + speedRight;
    this.objectRect.x += speed + speedRight;
    decreaseRight++;
    if (decreaseRight == 5) {
      decreaseRight = 0;
      accelerateRight = accel;
    }
  }

  /**
   * controls the acceleration and deceleration upward
   * 
   * @param accel
   *          wether to increase or decrease speed.
   */
  private void up(int accel) {
    this.y -= speed + speedUp;
    this.objectRect.y -= speed + speedUp;
    decreaseUp++;
    if (decreaseUp == 5) {
      decreaseUp = 0;
      accelerateUp = accel;
    }
  }

  /**
   * controls the acceleration and deceleration downward
   * 
   * @param accel
   *          wether to increase or decrease speed.
   */
  private void down(int accel) {
    this.y += speed + speedDown;
    this.objectRect.y += speed + speedDown;
    decreaseDown++;
    if (decreaseDown == 5) {
      decreaseDown = 0;
      accelerateDown = accel;
    }
  }

  /**
   * this method increases or decreases the speed to the individual directions.
   * 
   * @param a
   *          whether to increase or decrease the speed to the left
   * @param d
   *          whether to increase or decrease the speed to the right
   * @param w
   *          whether to increase or decrease the speed upward
   * @param s
   *          whether to increase or decrease the speed downward
   */
  private void momentum(int akey, int dkey, int wkey, int skey) {
    if (akey == 1 && speedLeft < 5) {
      speedLeft++;
      accelerateLeft = 0;
    }
    if (akey == -1 && speedLeft > 0) {
      speedLeft--;
      accelerateLeft = 0;
    }
    if (dkey == 1 && speedRight < 5) {
      speedRight++;
      accelerateRight = 0;
    }
    if (dkey == -1 && speedRight > 0) {
      speedRight--;
      accelerateRight = 0;
    }
    if (wkey == 1 && speedUp < 5) {
      speedUp++;
      accelerateUp = 0;
    }
    if (wkey == -1 && speedUp > 0) {
      speedUp--;
      accelerateUp = 0;
    }
    if (skey == 1 && speedDown < 5) {
      speedDown++;
      accelerateDown = 0;
    }
    if (skey == -1 && speedDown > 0) {
      speedDown--;
      accelerateDown = 0;
    }
  }
}
