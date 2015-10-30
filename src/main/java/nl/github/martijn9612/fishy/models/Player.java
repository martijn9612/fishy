package nl.github.martijn9612.fishy.models;

import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Ellipse;

import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.powerups.Shield;
import nl.github.martijn9612.fishy.states.LevelState;
import nl.github.martijn9612.fishy.utils.MusicPlayer;

/**
 * Implements the playable character of the game.
 * Software Engineering Methods Project - Group 11.
 */
public class Player extends Entity {
    private static final float PLAYER_WIDTH = 16;
    private static final float PLAYER_HEIGHT = 16;
    private static final float WATER_DRAG = 0.3f;
    private static final float PLAYER_DEFAULT_MASS = 5;
    private static final float PLAYER_DEFAULT_FORCE = 4;
    private static final float PLAYER_DEFAULT_MAX_SPEED = 8;
    private static final float PLAYER_SPEEDUP_MASS = 3;
    private static final float PLAYER_SPEEDUP_FORCE = 30;
    private static final float PLAYER_SPEEDUP_MAX_SPEED = 40;
    private static final float PLAYER_EAT_GROW_FACTOR = 0.5f;
    private static final float PLAYER_EAT_SCORE_FACTOR = 0.2f;
    private static final String PLAYER_SPRITE =
    	"resources/player-" + Main.PLAYER_CHARACTER + ".png";
    private static final String[] BITE_SOUNDS = {
    	MusicPlayer.BITE_SOUND_1,
    	MusicPlayer.BITE_SOUND_2,
    	MusicPlayer.BITE_SOUND_3
    };

    private int lives = 0;
    private double score = 0;
    private boolean poisoned = false;
    private float playerMaxSpeed = PLAYER_DEFAULT_MAX_SPEED;
    private float playerMoveForce = PLAYER_DEFAULT_FORCE;
    private int shieldState = Shield.STATE_NONE;
    private Timer speedUpTimer = new Timer();
    private Timer poisonTimer = new Timer();
    private Timer shieldTimer = new Timer();
    private Timer shieldRemover = new Timer();

    /**
     * Creates a new default Player instance, based on the Entity class.
     * @param data - the moveable data of the new Player.
     * @param loadResources - true if OpenGL context should be loaded, false if not.
     * @return Player - a new Player instance.
     */
    public Player(Moveable data, boolean loadResources) {
    	super(data, loadResources);
    	Main.actionLogger.logLine("Player succesfully created", getClass().getSimpleName());
    	loadResources(PLAYER_SPRITE);
    }
    
	/**
	 * Creates a new Player instance.
	 * @param loadResources - true if OpenGL context should be loaded, false if not.
	 * @return Player - a new Player instance
	 */
	public static Player createPlayer(boolean loadResources) {
		Moveable data = new Moveable();
		data.setDimensions(new Vector(PLAYER_WIDTH, PLAYER_HEIGHT));
		data.setPosition(Vector.centerOfScreen());
		data.setMass(PLAYER_DEFAULT_MASS);
		return new Player(data, loadResources);
	}
	
	/**
	 * Extends the super method which renders the object and
	 * its boundary box by drawing the shield when necessary.
	 * @param g - the graphics content used to render.
	 */
	public void renderObject(Graphics g) {
        super.renderObject(g);
        if(shieldState != Shield.STATE_NONE) {
        	g.setLineWidth(2.0f);
        	g.setColor(shieldState == Shield.STATE_FULL ? Color.red : Color.yellow);
        	Ellipse boundingBox = getBoundingBox();
        	g.drawOval(boundingBox.getX(), boundingBox.getY(), boundingBox.getWidth()+1, boundingBox.getHeight()+1);
        	g.setLineWidth(1.0f);
        }
	}
    
	/**
	 * Updates the player movement logic and polls the keyboard.
	 * The player will be moved realistically in an environment of
	 * water. Also the user input on the keyboard is handled here.
	 * @param gc - the container holding the game.
     * @param deltaTime - time elapsed since method was called in milliseconds.
	 */
    public void objectLogic(GameContainer gc, int deltaTime) {
        Input keyboardInput = gc.getInput();
    	movePlayer(keyboardInput);
        getData().applyWaterDrag(WATER_DRAG);
        getData().updatePosition(playerMaxSpeed);
        checkGameEdges();
        updateBoundingbox();
    }

    /**
     * Handles the keyboard controls so the player is able to move around.
     * @param input - object to access keyboard button states.
     */
    private void movePlayer(Input input) {
        boolean moveL = (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT));
        boolean moveR = (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT));
        boolean moveU = (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP));
        boolean moveD = (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN));
        float moveForce = poisoned ? -playerMoveForce : playerMoveForce;
        
        if(moveR) {
        	setImageOrientation(poisoned ? Entity.IMAGE_ORIENTATE_LEFT : Entity.IMAGE_ORIENTATE_RIGHT);
        	getData().applyForce(new Vector(moveForce, 0));
        }
        
        if(moveL) {
        	setImageOrientation(poisoned ? Entity.IMAGE_ORIENTATE_RIGHT : Entity.IMAGE_ORIENTATE_LEFT);
        	getData().applyForce(new Vector(-moveForce, 0));
        }
        
        if(moveU) {
        	getData().applyForce(new Vector(0, -moveForce));
        }
        
        if(moveD) {
        	getData().applyForce(new Vector(0, moveForce));
        }
    }
    
    /**
     * Checks whether the player is within the screen bounds and corrects them
     * if necessary.
     */
    private void checkGameEdges() {
    	getData().getPosition().x = limit(getData().getPosition().x, 0, Main.WINDOW_WIDTH - getData().getDimensions().x);
    	getData().getPosition().y = limit(getData().getPosition().y, 0, Main.WINDOW_HEIGHT - getData().getDimensions().y);
    }

    /**
     * Consume a specific Opponent. Player's score is updated.
     * @param opponentSize - size of the consumed opponent.
     */
    public void eat(double opponentSize) {
        setScore(score + opponentSize * PLAYER_EAT_SCORE_FACTOR);
        float newDimension = PLAYER_WIDTH + Math.round(score * PLAYER_EAT_GROW_FACTOR);
        getData().setDimensions(new Vector(newDimension, newDimension));
        Main.actionLogger.logLine("Player ate opponent", getClass().getSimpleName());
        Main.actionLogger.logLine("Player score is " + Math.floor(score), getClass().getSimpleName());
    }

    /**
     * Reset player values, can be used when the player dies.
     */
    public void resetPlayerVariables() {
        Main.actionLogger.logLine("Player resetted", getClass().getSimpleName());
		Main.actionLogger.logLine("Score was " + LevelState.getScore(), getClass().getSimpleName());
        getData().setPosition(Vector.centerOfScreen());
        getData().setDimensions(new Vector(PLAYER_WIDTH, PLAYER_HEIGHT));
        getData().setMass(PLAYER_DEFAULT_MASS);
        playerMaxSpeed = PLAYER_DEFAULT_MAX_SPEED;
        playerMoveForce = PLAYER_DEFAULT_FORCE;
        shieldState = Shield.STATE_NONE;
        poisoned = false;
        setScore(0);
    }

    /**
     * Plays a random available bite sound from the list.
     */
    public void playBiteSound() {
    	/* Integer between 1 and array length */
    	int biteSoundNumber = (int) Math.ceil(BITE_SOUNDS.length * Math.random());
    	 /* Subtract 1 to get array index */
        musicPlayer.playSound(BITE_SOUNDS[biteSoundNumber - 1]);
    }

    /**
     * Validates whether the given number is within the given limits. If the
     * number is not within the given bounds, the closest limit value is
     * returned.
     * @param x - integer to test.
     * @param min - lower limit value.
     * @param f - upper limit value.
     * @return float - the closest limit value.
     */
    private float limit(float x, float min, float f) {
        return Math.max(Math.min(x, f), min);
    }

    /**
     * Get the players score and returns it.
     * @return score - the current score.
     */
    public double getScore() {
        return score;
    }

    /**
     * Set the players score.
     * @param score - new score value.
     */
    public void setScore(double score) {
        LevelState.setScore(String.valueOf(Math.round(score)));
        this.score = score;
    }

    /**
     * Does the logic for the Speedup Powerup. Player's speed
     * is set higher for a certain amount of time.
     * @param time - how long the powerup has to be active.
     */
    public void Speedup(int time) {
        speedUpTimer.cancel();
        playerMaxSpeed = PLAYER_SPEEDUP_MAX_SPEED;
        playerMoveForce = PLAYER_SPEEDUP_FORCE;
        getData().setMass(PLAYER_SPEEDUP_MASS);
        speedUpTimer = new Timer();
        speedUpTimer.schedule(new TimerTask() {
            public void run() {
                playerMaxSpeed = PLAYER_DEFAULT_MAX_SPEED;
                playerMoveForce = PLAYER_DEFAULT_FORCE;
                getData().setMass(PLAYER_DEFAULT_MASS);
            }
        }, time);
    }

    /**
     * Does the logic for the Poison powerup. 
     * @param time - how long the poison has to be active.
     */
    public void Poison(int time) {
        poisonTimer.cancel();
        poisoned = true;
        poisonTimer = new Timer();
        poisonTimer.schedule(new TimerTask() {
            public void run() {
                poisoned = false;
            }
        }, time);
    }

    /**
     * Adds an extra life.
     */
    public void Extralife() {
        lives++;
    }

    /**
     * Gets the number of lives the player has left.
     * @return the number of lives.
     */
    public int getLives() {
        return lives;
    }
    /** 
     * Removes 1 life.
     */
    public void Loselife() {
        lives--;
    }

    /**
     * Gets the number of lives and represents them as a string.
     * @return the number of lives in string representation.
     */
    public String getLivesAsString() {
        return "lives: (" + lives + ")";
    }

    /**
     * Adds a shield to the player.
     * @param activeTime - time the shield is active.
     * @param fadeTime - time the shield takes to remove.
     */
    public void addShield(int activeTime, final int fadeTime) {
        shieldState = Shield.STATE_FULL;
        shieldTimer.cancel();
        shieldRemover.cancel();
        shieldTimer = new Timer();
        shieldTimer.schedule(new TimerTask() {
            public void run() {
                removeShield(fadeTime);
            }
        }, activeTime);
    }

    /**
     * Removes the shield from the player.
     * @param fadeTime - time the shield takes to remove.
     */
    public void removeShield(int fadeTime) {
        shieldState = Shield.STATE_HALF;
        shieldTimer.cancel();
        shieldRemover.cancel();
        shieldRemover = new Timer();
        shieldRemover.schedule(new TimerTask() {
            public void run() {
                shieldState = Shield.STATE_NONE;
            }
        }, fadeTime);
    }
    
    /**
     * Checks if the player has a shield.
     * @return boolean, true if the player has a shield, false if not.
     */
	public boolean hasShield() {
		return shieldState != Shield.STATE_NONE;
	}
	
	/**
	 * Sets the state for the shield.
	 * @param newKey - new state for the shield.
	 */
	public void setShieldState(int newState) {
	    shieldState = newState;
	}
	
	/**
	 * Gets the current shield state. Used for testing purposes.
	 * @return the current state.
	 */
	public int getShieldState() {
	    return shieldState;
	}
}
