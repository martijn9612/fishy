package nl.github.martijn9612.fishy.models;

import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import nl.github.martijn9612.fishy.states.LevelState;
import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.utils.MusicPlayer;

/**
 * Implements the playable character of the game.
 */
public class Player extends Entity {
    private static final float PLAYER_WIDTH = 16;
    private static final float PLAYER_HEIGHT = 16;
    private static final float WATER_DRAG = 0.3f;
    private static float PLAYER_MASS = 5;
    private static float PLAYER_MAX_SPEED = 8;
    private static float PLAYER_MOVE_FORCE = 4;
    private static final float PLAYER_EAT_GROW_FACTOR = 0.5f;
    private static final float PLAYER_EAT_SCORE_FACTOR = 0.2f;
    private static final String PLAYER_SPRITE = "resources/player-" + Main.PLAYER_CHARACTER + ".png";
    
    private double score = 0;
    private static final String[] BITE_SOUNDS = {
		MusicPlayer.BITE_SOUND_1,
		MusicPlayer.BITE_SOUND_2,
		MusicPlayer.BITE_SOUND_3
	};
    private int lives = 0;
    private Timer timer = new Timer();

    /**
     * Creates a new Player instance in the game window.
     * 
     * @param dimensions size of the new player.
	 * @param position vector with the start position of the player.
	 * @param velocity initial speed of the player.
	 * @param acceleration initial acceleration of the player.
	 * @param loadResources whether the sprite resources should be loaded.
     */
    public Player(Vector dimensions, Vector position, Vector velocity, Vector acceleration, boolean loadResources) {
    	super(dimensions, position, velocity, acceleration, loadResources);
    	Main.actionLogger.logLine("Player succesfully created", getClass().getSimpleName());
    	loadResources(PLAYER_SPRITE);
    }
    
	/**
	 * Creates a new default Player instance.
	 * 
	 * @param loadResources whether the player's resources should be loaded.
	 * @return Player instance.
	 */
	public static Player createPlayer(boolean loadResources) {
		Vector dimensions = new Vector(PLAYER_WIDTH, PLAYER_HEIGHT);
		Vector velocity = new Vector(0, 0);
		Vector acceleration = new Vector(0, 0);
		Vector position = Vector.centerOfScreen();
		return new Player(dimensions, position, velocity, acceleration, loadResources);
	}
    
	/**
	 * Updates the player movement logic and polls the keyboard.
	 * 
	 * The player will be moved realistically in an environment of
	 * water. Also the user input on the keyboard is handled here.
	 * 
	 * @param gc the container holding the game.
     * @param deltaTime time elapsed since method was called in milliseconds.
	 */
    public void objectLogic(GameContainer gc, int deltaTime) {
        Input keyboardInput = gc.getInput();
    	movePlayer(keyboardInput);
        applyWaterDrag();
        updatePosition();
        checkGameEdges();
        updateBoundingbox();
    }

    /**
     * Handles the keyboard controls so the player is able to move around.
     * 
     * @param input object to access keyboard button states.
     */
    private void movePlayer(Input input) {
        boolean moveL = (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT));
        boolean moveR = (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT));
        boolean moveU = (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP));
        boolean moveD = (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN));
        
        if(moveR) {
        	setImageOrientation(Entity.IMAGE_ORIENTATE_RIGHT);
        	applyForce(new Vector(PLAYER_MOVE_FORCE, 0));
        }
        
        if(moveL) {
        	setImageOrientation(Entity.IMAGE_ORIENTATE_LEFT);
        	applyForce(new Vector(-PLAYER_MOVE_FORCE, 0));
        }
        
        if(moveU) {
        	applyForce(new Vector(0, -PLAYER_MOVE_FORCE));
        }
        
        if(moveD) {
        	applyForce(new Vector(0, PLAYER_MOVE_FORCE));
        }
    }

    /**
     * Updates the position of the player according to grandpa Newton.
     */
    private void updatePosition() {
    	velocity.add(acceleration);
        velocity.limit(PLAYER_MAX_SPEED);
        position.add(velocity);
        acceleration.scale(0);
    }
    
    /**
     * Calculates the drag force the water applies to the player.
     */
    private void applyWaterDrag() {
		float speed = velocity.length();
		float dragMagnitude = WATER_DRAG * speed * speed;
		Vector drag = velocity.copy();
		drag.negateLocal();
		drag.normalise();
		drag.scale(dragMagnitude);
		applyForce(drag);
	}
    
    /**
     * Checks whether the player is within the screen bounds and corrects them if necessary.
     */
    private void checkGameEdges() {
        position.x = limit(position.x, 0, Main.WINDOW_WIDTH - dimensions.x);
        position.y = limit(position.y, 0, Main.WINDOW_HEIGHT - dimensions.y);
    }
    
    /**
     * Apply a force to the player, according to force = mass * acceleration;
     * 
     * @param Vector force
     */
    private void applyForce(Vector force) {
		Vector newForce = force.copy();
		newForce.scale(1 / PLAYER_MASS);
		acceleration.add(newForce);
	}

    /**
     * Consume a specific Opponent.
     * 
     * @param opponent to eat
     */
    public void eat(NonPlayer opponent) {
        double opponentSize = opponent.getSize();
    	setScore(score + opponentSize * PLAYER_EAT_SCORE_FACTOR);
        float newDimension = PLAYER_WIDTH + Math.round(score * PLAYER_EAT_GROW_FACTOR);
        dimensions = new Vector(newDimension, newDimension);
        Main.actionLogger.logLine("Player ate opponent", getClass().getSimpleName());
        Main.actionLogger.logLine("Player score is " + Math.floor(score), getClass().getSimpleName());
        playBiteSound();
    }

    /**
     * Reset player values, can be used when the player dies.
     */
    public void resetPlayerVariables() {
        Main.actionLogger.logLine("Player resetted", getClass().getSimpleName());
        Main.actionLogger.logLine("Score was " + LevelState.score, getClass().getSimpleName());
        position = Vector.centerOfScreen();
        dimensions = new Vector(PLAYER_WIDTH, PLAYER_HEIGHT);
        PLAYER_MAX_SPEED = 8;
        PLAYER_MOVE_FORCE = 4;
        PLAYER_MASS = 5;
        setScore(0);
    }
    
    /**
     * Plays a random available bite sound from the list.
     */
    public void playBiteSound() {
    	int biteSoundNumber = (int) Math.ceil(BITE_SOUNDS.length * Math.random()); /* Integer between 1 and array length */
    	musicPlayer.playSound(BITE_SOUNDS[biteSoundNumber - 1]); /* Subtract 1 to get array index */
    }

    /**
     * Validates whether the given number is within the given limits. If the number
     * is not within the given bounds, the closest limit value is returned.
     * 
     * @param x integer to test
     * @param min lower limit value
     * @param f upper limit value
     * @return float
     */
    private float limit(float x, float min, float f) {
    	return Math.max(Math.min(x, f), min);
    }

    /**
     * Get the players score.
     * 
     * @return score
     */
    public double getScore() {
        return score;
    }

    /**
     * Set the players score.
     * 
     * @param score new score value
     */
    public void setScore(double score) {
        LevelState.score = String.valueOf(Math.round(score));
        this.score = score;
    }
    
    public void Speedup(int time){
        timer.cancel();
        PLAYER_MAX_SPEED = 40;
        PLAYER_MOVE_FORCE = 30;
        PLAYER_MASS = 3;
        timer = new Timer();

        TimerTask action = new TimerTask() {
            public void run() {
                PLAYER_MAX_SPEED = 8;
                PLAYER_MOVE_FORCE = 4;
                PLAYER_MASS = 5;
            }
        };
        this.timer.schedule(action, time);
        }
    
    public void Extralife(){
     lives++;   
    }
    public int getLives(){
        return lives;
    }
    public void Loselife(){
        lives--;
    }
    public String getLivesAsString(){
        return "lives: (" + lives + ")";
    }
    }

