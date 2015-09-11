package nl.github.martijn9612.fishy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Player extends Entity {
    private static final int PLAYER_START_X = 350;
    private static final int PLAYER_START_Y = 450;
    private static final int PLAYER_WIDTH = 32;
    private static final int PLAYER_HEIGHT = 32;
    private static final int PLAYER_SPEED = 1;
    private String left = Main.PLAYER_CHARACTER + "left";
    private String right = Main.PLAYER_CHARACTER + "right";
    private int decelerateLeft, accelerateLeft, speedLeft = 0;
    private int decelerateRight, accelerateRight, speedRight = 0;
    private int decelerateUp, accelerateUp, speedUp = 0;
    private int decelerateDown, accelerateDown, speedDown = 0;
    public double score = 0;

    /**
     * Creates a new Player instance in the game window and loads its sprite.
     */
    public Player() {
        this(true);
    }

    /**
     * Creates a new Player instance in the game window.
     * @param loadSprite loadSprite whether the player sprite should be loaded or not.
     */
    public Player(boolean loadSprite) {
        if(loadSprite) {
            this.loadImage(left);
        }
        this.setPosition(PLAYER_START_X, PLAYER_START_Y);
        this.setDimensions(PLAYER_WIDTH, PLAYER_HEIGHT);
        this.setSpeed(PLAYER_SPEED);
        this.calculateRectangle();
    }

    /**
     * updates the object logic, also used for controls.
     */
    @Override
    public void objectLogic(GameContainer gc, int deltaTime) {
        keyboardControl(gc);
        checkBounds();
        momentum(accelerateLeft, accelerateRight, accelerateUp, accelerateDown);
    }

    private void keyboardControl(GameContainer gc) {
        Input input = gc.getInput();

        if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)) {
            loadImage(left);
            left(1);
        } else {
            left(-1);
        }

        if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)) {
            loadImage(right);
            right(1);
        } else {
            right(-1);
        }

        if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP)) {
            up(1);
        } else {
            up(-1);
        }

        if (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN)) {
            down(1);
        } else {
            down(-1);
        }
    }

    @Override
    public void renderObject(Graphics g) {
        g.drawImage(getImage().getScaledCopy(getWidth(), getHeight()), getX(), getY());
    }

    private void checkBounds() {
        if (this.x > Main.WINDOW_WIDTH - getWidth()) {
            this.x = Main.WINDOW_WIDTH - getWidth();
        }

        if (this.x < 0) {
            this.x = 0;
        }

        if (this.y > Main.WINDOW_HEIGHT - getHeight()) {
            this.y = Main.WINDOW_HEIGHT - getHeight();
        }

        if (this.y < 0) {
            this.y = 0;
        }
    }

    /**
     * controls the acceleration and deceleration to the left.
     * @param accel wether to increase or decrease speed.
     */
    public void left(int accel) {
        this.x -= speed + speedLeft;
        this.objectRect.x -= speed + speedLeft;
        decelerateLeft++;
        if (decelerateLeft == 5) {
            decelerateLeft = 0;
            accelerateLeft = accel;
        }
    }

    /**
     * controls the acceleration and deceleration to the right.
     * @param accel wether to increase or decrease speed.
     */
    public void right(int accel) {
        this.x += speed + speedRight;
        this.objectRect.x += speed + speedRight;
        decelerateRight++;
        if (decelerateRight == 5) {
            decelerateRight = 0;
            accelerateRight = accel;
        }
    }

    /**
     * controls the acceleration and deceleration upward.
     * @param accel wether to increase or decrease speed.
     */
    public void up(int accel) {
        this.y -= speed + speedUp;
        this.objectRect.y -= speed + speedUp;
        decelerateUp++;
        if (decelerateUp == 5) {
            decelerateUp = 0;
            accelerateUp = accel;
        }
    }

    /**
     * controls the acceleration and deceleration downward.
     * @param accel wether to increase or decrease speed.
     */
    public void down(int accel) {
        y += speed + speedDown;
        objectRect.y += speed + speedDown;
        decelerateDown++;
        if (decelerateDown == 5) {
            decelerateDown = 0;
            accelerateDown = accel;
        }
    }

    /**
     * this method increases or decreases the speed to the individual directions.
     * @param left whether to increase or decrease the speed to the left
     * @param right whether to increase or decrease the speed to the right
     * @param up whether to increase or decrease the speed upward
     * @param down whether to increase or decrease the speed downward
     */
    private void momentum(int left, int right, int up, int down) {
        if (left == 1 && speedLeft < 5) {
            speedLeft++;
            accelerateLeft = 0;
        }
        if (left == -1 && speedLeft > 0) {
            speedLeft--;
            accelerateLeft = 0;
        }
        if (right == 1 && speedRight < 5) {
            speedRight++;
            accelerateRight = 0;
        }
        if (right == -1 && speedRight > 0) {
            speedRight--;
            accelerateRight = 0;
        }
        if (up == 1 && speedUp < 5) {
            speedUp++;
            accelerateUp = 0;
        }
        if (up == -1 && speedUp > 0) {
            speedUp--;
            accelerateUp = 0;
        }
        if (down == 1 && speedDown < 5) {
            speedDown++;
            accelerateDown = 0;
        }
        if (down == -1 && speedDown > 0) {
            speedDown--;
            accelerateDown = 0;
        }
    }

    public double getScore(){
        return this.score;
    }

    public void setScore(double sc){
        score = sc;
    }

    public void addScore(double sc){
        score += sc;
    }

    public void eat(Opponent fish) {
        addScore(fish.getSize() * 0.2);
        LevelState.score = String.valueOf(Math.round(getScore()));
        int newDim = 32 + (int) Math.round(getScore());
        setDimensions(newDim,newDim);
        calculateRectangle();
    }

    public void die() {
        setScore(0);
        LevelState.score = "0";
        setDimensions(32, 32);
    }
}
