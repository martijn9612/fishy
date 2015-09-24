package nl.github.martijn9612.fishy;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import nl.github.martijn9612.fishy.models.Vector;

/**
 * Implements the playable character of the game.
 */
public class Player extends Entity {
    private static final int PLAYER_WIDTH = 16;
    private static final int PLAYER_HEIGHT = 16;
    public static final double PLAYER_EAT_SCORE_FACTOR = 0.2;
    public static final double PLAYER_EAT_GROW_FACTOR = 0.8;
    private String left = "player-" + Main.PLAYER_CHARACTER + "left";
    private String right = "player-" + Main.PLAYER_CHARACTER + "right";

    private double score = 0;
    
    private Vector location;
    private Vector velocity;
    private Vector acceleration;
    
    private static final double MAX_SPEED = 5;
    private static final double PLAYER_ACCEL_RATE = 2;
    private static final double PLAYER_DEACCEL_RATE = 0.75;
    
    private MusicPlayer musicPlayer = MusicPlayer.getInstance();
    private static final String[] BITE_SOUNDS = {
		MusicPlayer.BITE_SOUND_1,
		MusicPlayer.BITE_SOUND_2,
		MusicPlayer.BITE_SOUND_3
	};

    /**
     * Creates a new Player instance in the game window and loads its sprite.
     */
    public Player() {
        this(true);
    }

    /**
     * Creates a new Player instance in the game window.
     * @param loadResources whether the player's resources should be loaded.
     */
    public Player(boolean loadResources) {
        if (loadResources) {
            loadImage(left);
        }
        
        setDimensions(PLAYER_WIDTH, PLAYER_HEIGHT);
        calculateInitialBoundingbox();
        
        location = new Vector(Display.getWidth() / 2, Display.getHeight() / 2);
        velocity = new Vector(0, 0);
        acceleration = new Vector(0, 0);
        
        setPosition((int) Math.round(location.x), (int) Math.round(location.y));
        Main.actionLogger.logLine("Player succesfully created", getClass().getSimpleName());
    }

    /**
     * Updates the object logic, also used for controls.
     */
    @Override
    public void objectLogic(GameContainer gc, int deltaTime) {
        keyboardControl(gc.getInput());
        
        velocity.add(acceleration);
        velocity.mult(PLAYER_DEACCEL_RATE);
        velocity.limit(MAX_SPEED);
        location.add(velocity);
        
        setPosition((int) Math.round(location.x), (int) Math.round(location.y));
        acceleration.mult(0);
        checkBounds();
    }

    /**
     * Handles the keyboard controls so the player is able to move around.
     * @param input object to access keyboard button states.
     */
    private void keyboardControl(Input input) {
        boolean moveLeft = (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT));
        boolean moveRight = (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT));
        boolean moveUp = (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP));
        boolean moveDown = (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN));
        
        if(moveRight && !moveLeft) {
        	loadImage(right);
        	acceleration.add(new Vector(PLAYER_ACCEL_RATE, 0));
        }
        
        if(moveLeft && !moveRight) {
        	loadImage(left);
        	acceleration.add(new Vector(-PLAYER_ACCEL_RATE, 0));
        }
        
        if(moveUp && !moveDown) {
        	acceleration.add(new Vector(0, -PLAYER_ACCEL_RATE));
        }
        
        if(moveDown && !moveUp) {
        	acceleration.add(new Vector(0, PLAYER_ACCEL_RATE));
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
     * @param number integer to test
     * @param min lower bound value
     * @param max upper bound value
     * @return integer
     */
    private int getBetweenBounds(int number, int min, int max) {
    	return Math.max(Math.min(number, max), min);
    }

    /**
     * @return score
     */
    public double getScore() {
        return score;
    }

    /**
     * Set score.
     * @param score new score value
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * Consume a specific Opponent.
     * @param opponent to eat
     */
    public void eat(Opponent opponent) {
        double opponentSize = opponent.getSize();
    	setScore(getScore() + opponentSize * PLAYER_EAT_SCORE_FACTOR);
        LevelState.score = String.valueOf(Math.round(getScore()));
        int newDimension = PLAYER_WIDTH + (int) Math.round((getScore() * PLAYER_EAT_GROW_FACTOR));
        setDimensions(newDimension, newDimension);
        Main.actionLogger.logLine("Player ate opponent", getClass().getSimpleName());
        Main.actionLogger.logLine("Player score is " + Math.floor(getScore()), getClass().getSimpleName());
        playBiteSound();
    }

    /**
     * Reset player values upon dying.
     */
    public void resetPlayerVariables() {
        Main.actionLogger.logLine("Player resetted", getClass().getSimpleName());
        Main.actionLogger.logLine("Score was " + LevelState.score, getClass().getSimpleName());
        setScore(0);
        LevelState.score = "0";
        setDimensions(PLAYER_WIDTH, PLAYER_HEIGHT);
        setPosition(Display.getWidth() / 2, Display.getHeight() / 2);
    }
    
    private void playBiteSound() {
    	int biteSoundNumber = (int) Math.ceil(BITE_SOUNDS.length * Math.random()); /* Integer between 1 and array length */
    	musicPlayer.playSound(BITE_SOUNDS[biteSoundNumber - 1]); /* Subtract 1 to get array index */
    }
}
