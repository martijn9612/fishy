package nl.github.martijn9612.fishy;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * Implements the playable character of the game.
 */
public class Player extends Entity {
    private static final int PLAYER_START_X = 350;
    private static final int PLAYER_START_Y = 450;
    private static final int PLAYER_WIDTH = 16;
    private static final int PLAYER_HEIGHT = 16;
    private static final int PLAYER_SPEED = 1;
    public static final int SPEED_TRESHOLD = 5;
    public static final double PLAYER_EAT_SCORE_FACTOR = 0.2;
    public static final double PLAYER_EAT_GROW_FACTOR = 0.8;
    private String left = "player-" + Main.PLAYER_CHARACTER + "left";
    private String right = "player-" + Main.PLAYER_CHARACTER + "right";
    private int decelerateLeft, accelerateLeft, speedLeft = 0;
    private int decelerateRight, accelerateRight, speedRight = 0;
    private int decelerateUp, accelerateUp, speedUp = 0;
    private int decelerateDown, accelerateDown, speedDown = 0;
    private double score = 0;

    ArrayList<Sound> biteSounds = new ArrayList<Sound>();

    /**
     * Creates a new Player instance in the game window and loads its sprite.
     */
    public Player() {
        this(true);
    }

    /**
     * Creates a new Player instance in the game window.
     * 
     * @param loadResources
     *            whether the player's resources should be loaded.
     */
    public Player(boolean loadResources) {
        if (loadResources) {
            loadImage(left);
            loadBiteSounds();
        }
        setPosition(PLAYER_START_X, PLAYER_START_Y);
        setDimensions(PLAYER_WIDTH, PLAYER_HEIGHT);
        setSpeed(PLAYER_SPEED);
        calculateInitialBoundingbox();
        Main.actionLogger.logLine("Player succesfully created", getClass()
                .getSimpleName());
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
     * 
     * @param input
     *            object to access keyboard button states.
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
     * Checks whether the player is within the screen bounds and corrects them
     * if necessary.
     */
    private void checkBounds() {
        x = getBetweenBounds(x, 0, Main.WINDOW_WIDTH - getWidth());
        y = getBetweenBounds(y, 0, Main.WINDOW_HEIGHT - getHeight());
    }

    /**
     * Validates whether the given number is within the given bounds. If the
     * number is not within the given bounds, the closest bound value is
     * returned.
     * 
     * @param number
     *            integer to test
     * @param min
     *            lower bound value
     * @param max
     *            upper bound value
     * @return integer
     */
    private int getBetweenBounds(int number, int min, int max) {
        return Math.max(Math.min(number, max), min);
    }

    /**
     * controls the acceleration and deceleration to the left.
     * 
     * @param acceleration
     *            wether to increase or decrease speed.
     */
    public void left(int acceleration) {
        x -= speed + speedLeft;
        ellipse.setCenterX(ellipse.getCenterX() - (speed + speedLeft));
        decelerateLeft++;
        if (decelerateLeft == SPEED_TRESHOLD) {
            decelerateLeft = 0;
            accelerateLeft = acceleration;
        }
    }

    /**
     * controls the acceleration and deceleration to the right.
     * 
     * @param acceleration
     *            wether to increase or decrease speed.
     */
    public void right(int acceleration) {
        x += speed + speedRight;
        ellipse.setCenterX(ellipse.getCenterX() + speed + speedLeft);
        decelerateRight++;
        if (decelerateRight == SPEED_TRESHOLD) {
            decelerateRight = 0;
            accelerateRight = acceleration;
        }
    }

    /**
     * controls the acceleration and deceleration upward.
     * 
     * @param acceleration
     *            wether to increase or decrease speed.
     */
    public void up(int acceleration) {
        y -= speed + speedUp;
        ellipse.setCenterY(ellipse.getCenterY() - (speed + speedLeft));
        decelerateUp++;
        if (decelerateUp == SPEED_TRESHOLD) {
            decelerateUp = 0;
            accelerateUp = acceleration;
        }
    }

    /**
     * controls the acceleration and deceleration downward.
     * 
     * @param acceleration
     *            wether to increase or decrease speed.
     */
    public void down(int acceleration) {
        y += speed + speedDown;
        ellipse.setCenterY(ellipse.getCenterY() + speed + speedLeft);
        decelerateDown++;
        if (decelerateDown == SPEED_TRESHOLD) {
            decelerateDown = 0;
            accelerateDown = acceleration;
        }
    }

    @SuppressWarnings("checkstyle:methodlength")
    /**
     * This method increases or decreases the speed to the individual directions.
     */
    private void momentum() {
        if (accelerateLeft == 1 && speedLeft < SPEED_TRESHOLD) {
            speedLeft++;
            accelerateLeft = 0;
        }
        if (accelerateLeft == -1 && speedLeft > 0) {
            speedLeft--;
            accelerateLeft = 0;
        }
        if (accelerateRight == 1 && speedRight < SPEED_TRESHOLD) {
            speedRight++;
            accelerateRight = 0;
        }
        if (accelerateRight == -1 && speedRight > 0) {
            speedRight--;
            accelerateRight = 0;
        }
        if (accelerateUp == 1 && speedUp < SPEED_TRESHOLD) {
            speedUp++;
            accelerateUp = 0;
        }
        if (accelerateUp == -1 && speedUp > 0) {
            speedUp--;
            accelerateUp = 0;
        }
        if (accelerateDown == 1 && speedDown < SPEED_TRESHOLD) {
            speedDown++;
            accelerateDown = 0;
        }
        if (accelerateDown == -1 && speedDown > 0) {
            speedDown--;
            accelerateDown = 0;
        }
    }

    /**
     * @return score
     */
    public double getScore() {
        return score;
    }

    /**
     * Set score.
     * 
     * @param score
     *            new score value
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * Consume a specific Opponent.
     * 
     * @param opponent
     *            to eat
     */
    public void eat(Opponent opponent) {
        double opponentSize = opponent.getSize();
        setScore(getScore() + opponentSize * PLAYER_EAT_SCORE_FACTOR);
        LevelState.score = String.valueOf(Math.round(getScore()));
        int newDimension = PLAYER_WIDTH
                + (int) Math.round((getScore() * PLAYER_EAT_GROW_FACTOR));
        setDimensions(newDimension, newDimension);
        Main.actionLogger.logLine("Player ate opponent", getClass()
                .getSimpleName());
        Main.actionLogger.logLine("Player score is " + Math.floor(getScore()),
                getClass().getSimpleName());
        playBiteSound();
    }

    /**
     * Reset player values upon dying.
     */
    public void resetPlayerVariables() {
        Main.actionLogger
                .logLine("Player resetted", getClass().getSimpleName());
        Main.actionLogger.logLine("Score was " + LevelState.score, getClass()
                .getSimpleName());
        setScore(0);
        LevelState.score = "0";
        setDimensions(PLAYER_WIDTH, PLAYER_HEIGHT);
        setPosition(PLAYER_START_X, PLAYER_START_Y);
    }

    private void loadBiteSounds() {
        try {
            biteSounds.add(new Sound("resources/sounds/bite1.wav"));
            biteSounds.add(new Sound("resources/sounds/bite2.wav"));
            biteSounds.add(new Sound("resources/sounds/bite3.wav"));
        } catch (SlickException e) {
            Main.actionLogger.logLine("Unable to load bite sounds!", getClass()
                    .getSimpleName(), true);
            e.printStackTrace();
        }
    }

    private void playBiteSound() {
        int randomNumber = (int) Math.ceil(3 * Math.random()); /*
                                                                * Number between
                                                                * 1 and 3
                                                                */
        biteSounds.get(randomNumber - 1).play();
    }
}
