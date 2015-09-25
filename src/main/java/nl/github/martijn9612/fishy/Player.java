package nl.github.martijn9612.fishy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import nl.github.martijn9612.fishy.models.Vector;

/**
 * Implements the playable character of the game.
 */
public class Player extends Entity {
    private static final int PLAYER_WIDTH = 16;
    private static final int PLAYER_HEIGHT = 16;
    private static final double WATER_DRAG = 0.3;
    private static final double PLAYER_MASS = 5;
    private static final double PLAYER_MAX_SPEED = 8;
    private static final double PLAYER_MOVE_FORCE = 4;
    private static final double PLAYER_EAT_GROW_FACTOR = 0.5;
    private static final double PLAYER_EAT_SCORE_FACTOR = 0.2;
    private static final String PLAYER_SPRITE_LEFT = "player-" + Main.PLAYER_CHARACTER + "left";
    private static final String PLAYER_SPRITE_RIGHT = "player-" + Main.PLAYER_CHARACTER + "right";
    
    private MusicPlayer musicPlayer;
    private static final String[] BITE_SOUNDS = {
		MusicPlayer.BITE_SOUND_1,
		MusicPlayer.BITE_SOUND_2,
		MusicPlayer.BITE_SOUND_3
	};
    
    private Vector location;
    private Vector velocity;
    private Vector acceleration;
    private double score = 0;

    /**
     * Creates a new Player instance and loads the player sprite.
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
            loadImage(PLAYER_SPRITE_LEFT);
            musicPlayer = MusicPlayer.getInstance();
        }
        setDimensions(PLAYER_WIDTH, PLAYER_HEIGHT);
        calculateInitialBoundingbox();
        velocity = new Vector(0, 0);
        acceleration = new Vector(0, 0);
        location = Vector.centerOfScreen();
        setPosition(location);
        Main.actionLogger.logLine("Player succesfully created", getClass().getSimpleName());
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
        	loadImage(PLAYER_SPRITE_RIGHT);
        	applyForce(new Vector(PLAYER_MOVE_FORCE, 0));
        }
        
        if(moveL) {
        	loadImage(PLAYER_SPRITE_LEFT);
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
        location.add(velocity);
        setPosition(location);
        acceleration.mult(0);
    }
    
    /**
     * Calculates the drag force the water applies to the player.
     */
    private void applyWaterDrag() {
		double speed = velocity.mag();
		double dragMagnitude = WATER_DRAG * speed * speed;
		Vector drag = velocity.get();
		drag.mult(-1);
		drag.normalize();
		drag.mult(dragMagnitude);
		applyForce(drag);
	}
    
    /**
     * Checks whether the player is within the screen bounds and corrects them if necessary.
     */
    private void checkEdges() {
        x = limit(x, 0, Main.WINDOW_WIDTH - getWidth());
        y = limit(y, 0, Main.WINDOW_HEIGHT - getHeight());
    }
    
    /**
     * Apply a force to the player, according to force = mass * acceleration;
     * @param Vector force
     */
    private void applyForce(Vector force) {
		Vector newForce = force.get();
		newForce.div(PLAYER_MASS);
		acceleration.add(newForce);
	}

    /**
     * Consume a specific Opponent.
     * @param opponent to eat
     */
    public void eat(Opponent opponent) {
        double opponentSize = opponent.getSize();
    	setScore(score + opponentSize * PLAYER_EAT_SCORE_FACTOR);
        int newDimension = PLAYER_WIDTH + (int) Math.round(score * PLAYER_EAT_GROW_FACTOR);
        setDimensions(newDimension, newDimension);
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
        setPosition(Vector.centerOfScreen());
        setDimensions(PLAYER_WIDTH, PLAYER_HEIGHT);
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
     * @param number integer to test
     * @param min lower limit value
     * @param max upper limit value
     * @return int
     */
    private int limit(int number, int min, int max) {
    	return Math.max(Math.min(number, max), min);
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

    /**
     * Set the position of the entity.
     * TODO: Replace x and y of superclass with an instance of Vector.
     * @param Vector position update the player his location
     */
    private void setPosition(Vector position) {
    	 setPosition((int) Math.round(position.x), (int) Math.round(position.y));
    }
}
