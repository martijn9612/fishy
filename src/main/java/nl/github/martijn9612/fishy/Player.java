package nl.github.martijn9612.fishy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class Player extends Entity {
	
    private static final int PLAYER_START_X = 350;
    private static final int PLAYER_START_Y = 450;
    private static final int PLAYER_WIDTH = 16;
    private static final int PLAYER_HEIGHT = 16;
    private static final int PLAYER_SPEED = 1;
    
    private String left = Main.PLAYER_CHARACTER + "left";
    private String right = Main.PLAYER_CHARACTER + "right";
    private int decelerateLeft, accelerateLeft, speedLeft = 0;
    private int decelerateRight, accelerateRight, speedRight = 0;
    private int decelerateUp, accelerateUp, speedUp = 0;
    private int decelerateDown, accelerateDown, speedDown = 0;
    
    private double score = 0;

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
     * Updates the object logic, also used for controls.
     */
    @Override
    public void objectLogic(GameContainer gc, int deltaTime) {
        keyboardControl(gc.getInput());
        checkBounds();
        momentum();
    }
    
    /**
     * Handles the keyboard controls so the player is able to move around.
     * @param Input object to access keyboard button states.
     */
    private void keyboardControl(Input input) {
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
    
    /**
     * Checks whether the player is within the screen bounds and corrects them if necessary.
     */
    private void checkBounds() {
        x = getBetweenBounds(x, 0, Main.WINDOW_WIDTH - getWidth());
        y = getBetweenBounds(y, 0, Main.WINDOW_HEIGHT - getHeight());
    }
    
    /**
     * Validates whether the given number is within the given bounds. If the number
     * is not within the given bounds, the closest bound value is returned.
     * @param int number integer to test
     * @param int min lower bound value
     * @param int max upper bound value
     * @return integer
     */
    private int getBetweenBounds(int number, int min, int max) {
    	return Math.max(Math.min(number, max), min);
    }

    /**
     * Controls the acceleration and deceleration to the left.
     * @param accel whether to increase or decrease speed.
     */
    public void left(int accel) {
        this.x -= speed + speedLeft;
        decelerateLeft++;
        if (decelerateLeft == 5) {
            decelerateLeft = 0;
            accelerateLeft = accel;
        }
    }

    /**
     * Controls the acceleration and deceleration to the right.
     * @param accel whether to increase or decrease speed.
     */
    public void right(int accel) {
        this.x += speed + speedRight;
        decelerateRight++;
        if (decelerateRight == 5) {
            decelerateRight = 0;
            accelerateRight = accel;
        }
    }

    /**
     * Controls the acceleration and deceleration upward.
     * @param accel whether to increase or decrease speed.
     */
    public void up(int accel) {
        this.y -= speed + speedUp;
        decelerateUp++;
        if (decelerateUp == 5) {
            decelerateUp = 0;
            accelerateUp = accel;
        }
    }

    /**
     * Controls the acceleration and deceleration downward.
     * @param accel whether to increase or decrease speed.
     */
    public void down(int accel) {
        y += speed + speedDown;
        decelerateDown++;
        if (decelerateDown == 5) {
            decelerateDown = 0;
            accelerateDown = accel;
        }
    }

    /**
     * This method increases or decreases the speed to the individual directions.
     */
    private void momentum() {
        if (accelerateLeft == 1 && speedLeft < 5) {
            speedLeft++;
            accelerateLeft = 0;
        }
        if (accelerateLeft == -1 && speedLeft > 0) {
            speedLeft--;
            accelerateLeft = 0;
        }
        if (accelerateRight == 1 && speedRight < 5) {
            speedRight++;
            accelerateRight = 0;
        }
        if (accelerateRight == -1 && speedRight > 0) {
            speedRight--;
            accelerateRight = 0;
        }
        if (accelerateUp == 1 && speedUp < 5) {
            speedUp++;
            accelerateUp = 0;
        }
        if (accelerateUp == -1 && speedUp > 0) {
            speedUp--;
            accelerateUp = 0;
        }
        if (accelerateDown == 1 && speedDown < 5) {
            speedDown++;
            accelerateDown = 0;
        }
        if (accelerateDown == -1 && speedDown > 0) {
            speedDown--;
            accelerateDown = 0;
        }
    }

    public void eat(Opponent fish) {
    	score += fish.getSize() * 0.2;
        LevelState.score = String.valueOf(Math.round(score));
        int newDim = PLAYER_WIDTH + (int) Math.round((score * 0.8));
        setDimensions(newDim, newDim);
        calculateRectangle();
    }

    public void die() {
        score = 0;
        LevelState.score = "0";
        setDimensions(PLAYER_WIDTH, PLAYER_HEIGHT);
    }
}
