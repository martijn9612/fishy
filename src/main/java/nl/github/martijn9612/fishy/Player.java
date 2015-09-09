package nl.github.martijn9612.fishy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class Player extends Object {

    private String left = "fishleft";
    private String right = "fishright";
    public int counterA, accelA, speedA = 0;
    public int counterD, accelD, speedD = 0;
    public int counterW, accelW, speedW = 0;
    public int counterS, accelS, speedS = 0;

    public Player() {
        this.loadImage(left);
        this.setPosition(350, 450);
        this.setDimensions(100, 100);
        this.setSpeed(1);
        this.createRectangle();
    }
    /**
     * updates the object logic, also used for controls
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
        momentum(accelA, accelD, accelW, accelS);
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

        if (this.y > 515) {
            this.y = 515;
        }

        if (this.y < 0) {
            this.y = 0;
        }
    }
    /**
     * controls the acceleration and deceleration to the left
     * @param accel wether to increase or decrease speed.
     */
    public void left(int accel) {
        this.x -= speed + speedA;
        this.objectRect.x -= speed + speedA;
        counterA++;
        if (counterA == 5) {
            counterA = 0;
            accelA = accel;
        }
    }
    /**
     * controls the acceleration and deceleration to the right
     * @param accel wether to increase or decrease speed.
     */
    public void right(int accel) {
        this.x += speed + speedD;
        this.objectRect.x += speed + speedD;
        counterD++;
        if (counterD == 5) {
            counterD = 0;
            accelD = accel;
        }
    }
    /**
     * controls the acceleration and deceleration upward
     * @param accel wether to increase or decrease speed.
     */
    public void up(int accel) {
        this.y -= speed + speedW;
        this.objectRect.y -= speed + speedW;
        counterW++;
        if (counterW == 5) {
            counterW = 0;
            accelW = accel;
        }
    }
    /**
     * controls the acceleration and deceleration downward
     * @param accel wether to increase or decrease speed.
     */
    public void down(int accel) {
        this.y += speed + speedS;
        this.objectRect.y += speed + speedS;
        counterS++;
        if (counterS == 5) {
            counterS = 0;
            accelS = accel;
        }
    }
    /**
     * this method increases or decreases the speed to the individual directions
     * @param a whether to increase or decrease the speed to the left
     * @param d whether to increase or decrease the speed to the right
     * @param w whether to increase or decrease the speed upward
     * @param s whether to increase or decrease the speed downward
     */
    public void momentum(int a, int d, int w, int s) {
        if (a == 1 && speedA < 5) {
            speedA++;
            accelA = 0;
        }
        if (a == -1 && speedA > 0) {
            speedA--;
            accelA = 0;
        }
        if (d == 1 && speedD < 5) {
            speedD++;
            accelD = 0;
        }
        if (d == -1 && speedD > 0) {
            speedD--;
            accelD = 0;
        }
        if (w == 1 && speedW < 5) {
            speedW++;
            accelW = 0;
        }
        if (w == -1 && speedW > 0) {
            speedW--;
            accelW = 0;
        }
        if (s == 1 && speedS < 5) {
            speedS++;
            accelS = 0;
        }
        if (s == -1 && speedS > 0) {
            speedS--;
            accelS = 0;
        }

    }
}
