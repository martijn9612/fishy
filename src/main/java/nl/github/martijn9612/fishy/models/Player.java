package nl.github.martijn9612.fishy.models;

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
    private static final String PLAYER_SPRITE = "resources/player-" + Main.PLAYER_CHARACTER + ".png";
    private static final String PLAYER_FULL_SHIELD_SPRITE = "resources/shield-full.png";
    private static final String PLAYER_HALF_SHIELD_SPRITE = "resources/shield-half.png";
    
    private double score = 0;
    private static final String[] BITE_SOUNDS = {
		MusicPlayer.BITE_SOUND_1,
		MusicPlayer.BITE_SOUND_2,
		MusicPlayer.BITE_SOUND_3
	};

    private int lives = 0;
    private int poisoned = 1;
    private boolean hasShield;
    private Timer speedUpTimer = new Timer();
    private Timer poisonTimer = new Timer();
    private Timer shieldRemove = new Timer();

    /**
     * Creates a new Player instance in the game window.
     * 
     * @param dimensions size of the new player.
	 * @param position vector with the start position of the player.
	 * @param velocity initial speed of the player.
	 * @param acceleration initial acceleration of the player.
	 * @param loadResources whether the sprite resources should be loaded.
     */
    public Player(Moveable data, boolean loadResources) {
    	super(data, loadResources);
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
        	data.applyForce(new Vector( poisoned * playerMoveForce, 0));
        }
        
        if(moveL) {
        	setImageOrientation(Entity.IMAGE_ORIENTATE_LEFT);
        	data.applyForce(new Vector(poisoned * -playerMoveForce, 0));
        }
        
        if(moveU) {
        	data.applyForce(new Vector(0, poisoned * -playerMoveForce));
        }
        
        if(moveD) {
        	data.applyForce(new Vector(0, poisoned * playerMoveForce));
        }
    }
    
    /**
     * Checks whether the player is within the screen bounds and corrects them if necessary.
     */
    private void checkGameEdges() {
    	data.position.x = limit(data.position.x, 0, Main.WINDOW_WIDTH - data.dimensions.x);
    	data.position.y = limit(data.position.y, 0, Main.WINDOW_HEIGHT - data.dimensions.y);
    }

    /**
     * Consume a specific Opponent.
     * 
     * @param opponent to eat
     */
    public void eat(NonPlayer opponent) {
        double opponentSize = opponent.getSize();
    	setScore( score + opponentSize * PLAYER_EAT_SCORE_FACTOR);
        float newDimension = PLAYER_WIDTH + Math.round(score * PLAYER_EAT_GROW_FACTOR);
        data.dimensions = new Vector(newDimension, newDimension);
        Main.actionLogger.logLine("Player ate opponent", getClass().getSimpleName());
        Main.actionLogger.logLine("Player score is " + Math.floor(score), getClass().getSimpleName());
        playBiteSound();
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
        LevelState.setScore(String.valueOf(Math.round(score)));
        this.score = score;
    }

    public void Speedup(int time){
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
        poisoned = -1;
        poisonTimer = new Timer();

        TimerTask action = new TimerTask() {
            public void run() {
                poisoned = 1;
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

	public boolean hasShield() {
		return hasShield;
	}

	public void removeShield(int time) {
		shieldRemove = new Timer();
		loadImage(PLAYER_HALF_SHIELD_SPRITE);

		TimerTask action = new TimerTask() {
			public void run() {
				hasShield = false;
				loadImage(PLAYER_SPRITE);
			}
		};
		shieldRemove.schedule(action, time);
	}

	public void addShield() {
		loadImage(PLAYER_FULL_SHIELD_SPRITE);
		hasShield = true;
	}
}

