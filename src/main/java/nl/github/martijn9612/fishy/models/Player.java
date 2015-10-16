package nl.github.martijn9612.fishy.models;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.states.LevelState;
import nl.github.martijn9612.fishy.utils.MusicPlayer;

/**
 * Implements the playable character of the game.
 */
public class Player extends Entity {
    private static final float PLAYER_WIDTH = 16;
    private static final float PLAYER_HEIGHT = 16;
    private static final float WATER_DRAG = 0.3f;
    private static float playerMass = 5;
    private static float playerMaxSpeed = 8;
    private static float playerMoveForce = 4;
    private static final float PLAYER_EAT_GROW_FACTOR = 0.5f;
    private static final float PLAYER_EAT_SCORE_FACTOR = 0.2f;
    private static final String PLAYER_SPRITE = "resources/player-"
            + Main.PLAYER_CHARACTER + ".png";
    private static final String PLAYER_FULL_SHIELD_SPRITE = "resources/shield-full.png";
    private static final String PLAYER_HALF_SHIELD_SPRITE = "resources/shield-half.png";

    private double score = 0;
    private static final String[] BITE_SOUNDS = {
    	MusicPlayer.BITE_SOUND_1,
    	MusicPlayer.BITE_SOUND_2,
    	MusicPlayer.BITE_SOUND_3
    };

    private int lives = 0;
    private boolean poisoned = false;
    private boolean hasShield = false;
    private Timer speedUpTimer = new Timer();
    private Timer poisonTimer = new Timer();
    private Timer shieldTimer = new Timer();
    private Timer shieldRemover = new Timer();
    private HashMap<String, String> shieldImages = new HashMap<String, String>();
    private String key = "none";
    private String prevkey = "none";

    /**
     * Creates a new default Player instance.
     * 
     * @param loadResources
     *            whether the player's resources should be loaded.
     * @return Player instance.
     */
    public Player(Moveable data, boolean loadResources) {
    	super(data, loadResources);
    	Main.actionLogger.logLine("Player succesfully created", getClass().getSimpleName());
    	loadResources(PLAYER_SPRITE);
    	fillHashMap();
    }
    
	/**
	 * Creates a new default Player instance.
	 * 
	 * @param loadResources whether the player's resources should be loaded.
	 * @return Player instance.
	 */
	public static Player createPlayer(boolean loadResources) {
		Moveable data = new Moveable();
		data.dimensions = new Vector(PLAYER_WIDTH, PLAYER_HEIGHT);
		data.position = Vector.centerOfScreen();
		data.mass = playerMass;
		return new Player(data, loadResources);
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
        data.applyWaterDrag(WATER_DRAG);
        data.updatePosition(playerMaxSpeed);
        checkGameEdges();
        updateBoundingbox();
        updateShieldImage(key);
    }

    private void fillHashMap() {
        shieldImages.put("none", PLAYER_SPRITE);
        shieldImages.put("half", PLAYER_HALF_SHIELD_SPRITE);
        shieldImages.put("full", PLAYER_FULL_SHIELD_SPRITE);
    }

    /**
     * Handles the keyboard controls so the player is able to move around.
     * 
     * @param input
     *            object to access keyboard button states.
     */
    private void movePlayer(Input input) {
        boolean moveL = (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT));
        boolean moveR = (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT));
        boolean moveU = (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP));
        boolean moveD = (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN));
        float moveForce = poisoned ? -playerMoveForce : playerMoveForce;
        
        if(moveR) {
        	setImageOrientation(poisoned ? Entity.IMAGE_ORIENTATE_LEFT : Entity.IMAGE_ORIENTATE_RIGHT);
        	data.applyForce(new Vector(moveForce, 0));
        }
        
        if(moveL) {
        	setImageOrientation(poisoned ? Entity.IMAGE_ORIENTATE_RIGHT : Entity.IMAGE_ORIENTATE_LEFT);
        	data.applyForce(new Vector(-moveForce, 0));
        }
        
        if(moveU) {
        	data.applyForce(new Vector(0, -moveForce));
        }
        
        if(moveD) {
        	data.applyForce(new Vector(0, moveForce));
        }
    }
    
    /**
     * Checks whether the player is within the screen bounds and corrects them
     * if necessary.
     */
    private void checkGameEdges() {
    	data.position.x = limit(data.position.x, 0, Main.WINDOW_WIDTH - data.dimensions.x);
    	data.position.y = limit(data.position.y, 0, Main.WINDOW_HEIGHT - data.dimensions.y);
    }

    /**
     * Consume a specific Opponent.
     * 
     * @param opponent
     *            to eat
     */
    public void eat(double opponentSize) {
        setScore(score + opponentSize * PLAYER_EAT_SCORE_FACTOR);
        float newDimension = PLAYER_WIDTH + Math.round(score * PLAYER_EAT_GROW_FACTOR);
        data.dimensions = new Vector(newDimension, newDimension);
        Main.actionLogger.logLine("Player ate opponent", getClass().getSimpleName());
        Main.actionLogger.logLine("Player score is " + Math.floor(score), getClass().getSimpleName());
    }

    /**
     * Reset player values, can be used when the player dies.
     */
    public void resetPlayerVariables() {
        Main.actionLogger.logLine("Player resetted", getClass().getSimpleName());
		Main.actionLogger.logLine("Score was " + LevelState.getScore(), getClass().getSimpleName());
        data.position = Vector.centerOfScreen();
        data.dimensions = new Vector(PLAYER_WIDTH, PLAYER_HEIGHT);
        playerMaxSpeed = 8;
        playerMoveForce = 4;
        playerMass = 5;
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
     * 
     * @param x
     *            integer to test
     * @param min
     *            lower limit value
     * @param f
     *            upper limit value
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
     * @param score
     *            new score value
     */
    public void setScore(double score) {
        LevelState.setScore(String.valueOf(Math.round(score)));
        this.score = score;
    }

    public void Speedup(int time) {
        speedUpTimer.cancel();
        playerMaxSpeed = 40;
        playerMoveForce = 30;
        playerMass = 3;
        speedUpTimer = new Timer();

        TimerTask action = new TimerTask() {
            public void run() {
                playerMaxSpeed = 8;
                playerMoveForce = 4;
                playerMass = 5;
            }
        };
        speedUpTimer.schedule(action, time);
    }

    public void Poison(int time) {
        poisonTimer.cancel();
        poisoned = true;
        poisonTimer = new Timer();

        TimerTask action = new TimerTask() {
            public void run() {
                poisoned = false;
            }
        };

        poisonTimer.schedule(action, time);
    }

    public void Extralife() {
        lives++;
    }

    public int getLives() {
        return lives;
    }

    public void Loselife() {
        lives--;
    }

    public String getLivesAsString() {
        return "lives: (" + lives + ")";
    }

    public void addShield(int activeTime, int fadeTime) {
        key = "full";
        hasShield = true;
        final int fade = fadeTime;
        shieldTimer.cancel();
        shieldRemover.cancel();
        shieldTimer = new Timer();

        TimerTask action = new TimerTask() {
            public void run() {
                removeShield(fade);
            }
        };
        shieldTimer.schedule(action, activeTime);
    }

    public void removeShield(int fadeTime) {
        key = "half";
        shieldRemover.cancel();
        shieldRemover = new Timer();
        
        TimerTask action = new TimerTask() {
            public void run() {
                hasShield = false;
                key = "none";
            }
        };
        shieldRemover.schedule(action, fadeTime);
    }

    public void updateShieldImage(String key) {
        if (!key.equals(prevkey)) {
            String image = shieldImages.get(key);
            loadImage(image);
            prevkey = key;
        }
    }

	public boolean hasShield() {
		return hasShield;
	}
	
	public void setKey(String newKey) {
	    key = newKey;
	}
	
	public String getKey() {
	    return key;
	}
}
