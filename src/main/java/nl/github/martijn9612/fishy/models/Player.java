package nl.github.martijn9612.fishy.models;

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
    private static final float PLAYER_MASS = 5;
    private static final float PLAYER_MAX_SPEED = 8;
    private static final float PLAYER_MOVE_FORCE = 4;
    private static final float PLAYER_EAT_GROW_FACTOR = 0.5f;
    private static final float PLAYER_EAT_SCORE_FACTOR = 0.2f;
    private static final String PLAYER_SPRITE_LEFT = "resources/player-" + Main.PLAYER_CHARACTER + "left.png";
    private static final String PLAYER_SPRITE_RIGHT = "resources/player-" + Main.PLAYER_CHARACTER + "right.png";
    
    private double score = 0;
    private static final String[] BITE_SOUNDS = {
		MusicPlayer.BITE_SOUND_1,
		MusicPlayer.BITE_SOUND_2,
		MusicPlayer.BITE_SOUND_3
	};

    /**
     * Creates a new Player instance in the game window.
     * 
     * @param loadResources
     *            whether the player's resources should be loaded.
     */
    public Player(boolean loadResources) {
    	super(loadResources);
    	initPlayerVariables();
        Main.actionLogger.logLine("Player succesfully created", getClass().getSimpleName());
    }
    
    private void initPlayerVariables() {
    	loadResources(PLAYER_SPRITE_LEFT);
        dimensions = new Vector(PLAYER_WIDTH, PLAYER_HEIGHT);
        velocity = new Vector(0, 0);
        acceleration = new Vector(0, 0);
        position = Vector.centerOfScreen();
        calculateBoundingbox();
    }

    /**
     * Updates the object logic, also used for controls.
     * 
     */
    @Override
    public void objectLogic(GameContainer gc, int deltaTime) {
        Input keyboardInput = gc.getInput();
    	movePlayer(keyboardInput);
        applyWaterDrag();
        updatePosition();
        checkEdges();
        calculateBoundingbox();
    }

    /**
     * Handles the keyboard controls so the player is able to move around.
     * @param input
     *            object to access keyboard button states.
     * @param Input object to access keyboard button states.
     */
    private void movePlayer(Input input) {
        boolean moveL = (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT));
        boolean moveR = (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT));
        boolean moveU = (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP));
        boolean moveD = (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN));
        
        if(moveR) {
        	loadResources(PLAYER_SPRITE_RIGHT);
        	applyForce(new Vector(PLAYER_MOVE_FORCE, 0));
        }
        
        if(moveL) {
        	loadResources(PLAYER_SPRITE_LEFT);
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
    private void checkEdges() {
        position.x = limit(position.x, 0, Main.WINDOW_WIDTH - dimensions.x);
        position.y = limit(position.y, 0, Main.WINDOW_HEIGHT - dimensions.y);
    }
    
    /**
     * Apply a force to the player, according to force = mass * acceleration;
     * @param Vector force
     */
    private void applyForce(Vector force) {
		Vector newForce = force.copy();
		newForce.scale(1 / PLAYER_MASS);
		acceleration.add(newForce);
	}

    /**
     * Consume a specific Opponent.
     * @param opponent to eat
     */
    public void eat(Opponent opponent) {
        double opponentSize = opponent.getSize();
    	setScore(score + opponentSize * PLAYER_EAT_SCORE_FACTOR);
        float newDimension = PLAYER_WIDTH + Math.round(score * PLAYER_EAT_GROW_FACTOR);
        dimensions = new Vector(newDimension, newDimension);
        Main.actionLogger.logLine("Player ate opponent", getClass().getSimpleName());
        Main.actionLogger.logLine("Player score is " + Math.floor(score), getClass().getSimpleName());
        playBiteSound();
    }

    /**
     * Reset player values upon dying.
     */
    public void resetPlayerVariables() {
        Main.actionLogger.logLine("Player resetted", getClass().getSimpleName());
        Main.actionLogger.logLine("Score was " + LevelState.score, getClass().getSimpleName());
        position = Vector.centerOfScreen();
        dimensions = new Vector(PLAYER_WIDTH, PLAYER_HEIGHT);
        setScore(0);
    }
    
    /**
     * Plays a random available bite sound from the list.
     */
    private void playBiteSound() {
    	int biteSoundNumber = (int) Math.ceil(BITE_SOUNDS.length * Math.random()); /* Integer between 1 and array length */
    	musicPlayer.playSound(BITE_SOUNDS[biteSoundNumber - 1]); /* Subtract 1 to get array index */
    }

    /**
     * Validates whether the given number is within the given limits. If the number
     * is not within the given bounds, the closest limit value is returned.
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
     * @return score
     */
    public double getScore() {
        return score;
    }

    /**
     * Set the players score.
     * @param score new score value
     */
    public void setScore(double score) {
        LevelState.score = String.valueOf(Math.round(score));
        this.score = score;
    }
}
