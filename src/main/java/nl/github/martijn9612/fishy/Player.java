package nl.github.martijn9612.fishy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class Player extends Object {

    private String left = "fishleft";
    private String right = "fishright";
    private int counterA = 0;
    private int accelA = 0;
    private int speedA = 0;
    private int counterD = 0;
    private int accelD = 0;
    private int speedD = 0;
    private int counterW = 0;
    private int accelW = 0;
    private int speedW = 0;
    private int counterS = 0;
    private int accelS = 0;
    private int speedS = 0;

    public Player() {
        this.loadImage(left);
        this.setPosition(350, 450);
        this.setDimensions(100, 100);
        this.setSpeed(1);
        this.createRectangle();
    }

    @Override
    public void objectLogic(GameContainer gc, int deltaTime) {
        Input input = gc.getInput();

        if (input.isKeyDown(Input.KEY_A)) {
            this.loadImage(left);
            this.x -= speed + speedA;
            this.objectRect.x -= speed + speedA;
            counterA++;
            if (counterA == 5) {
                counterA = 0;
                accelA = 1;
            }
        } else {
            this.x -= speed + speedA;
            this.objectRect.x -= speed + speedA;
            counterA++;
            if (counterA == 5) {
                counterA = 0;
                accelA = -1;
            }
        }

        if (input.isKeyDown(Input.KEY_D)) {
            this.loadImage(right);
            this.x += speed + speedD;
            this.objectRect.x += speed + speedD;
            counterD++;
            if (counterD == 5) {
                counterD = 0;
                accelD = 1;
            }
        } else {
            this.x += speed + speedD;
            this.objectRect.x += speed + speedD;
            counterD++;
            if (counterD == 5) {
                counterD = 0;
                accelD = -1;
            }
        }

        if (input.isKeyDown(Input.KEY_W)) {
            this.y -= speed + speedW;
            this.objectRect.y -= speed + speedW;
            counterW++;
            if (counterW == 5) {
                counterW = 0;
                accelW = 1;
            } 
        }else {
                this.y -= speed + speedW;
                this.objectRect.y -= speed + speedW;
                counterW++;
                if (counterW == 5) {
                    counterW = 0;
                    accelW = -1;
                }
            }

            if (input.isKeyDown(Input.KEY_S)) {
                this.y += speed + speedS;
                this.objectRect.y += speed + speedS;
                counterS++;
                if (counterS == 5) {
                    counterS = 0;
                    accelS = 1;
                }
            } else {
                this.y += speed + speedS;
                this.objectRect.y += speed + speedS;
                counterS++;
                if (counterS == 5) {
                    counterS = 0;
                    accelS = -1;
                }
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

    private void momentum(int a, int d, int w, int s) {
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
