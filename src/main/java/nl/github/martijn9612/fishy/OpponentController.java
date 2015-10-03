package nl.github.martijn9612.fishy;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import nl.github.martijn9612.fishy.models.Opponent;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.opponents.BigOpponent;
import nl.github.martijn9612.fishy.opponents.LinearOpponent;
import nl.github.martijn9612.fishy.opponents.SinusOpponent;

/**
 * Implements the Opponent Handler of the game.
 */
public class OpponentController {

	private boolean loadResources;
	private final Random random = new Random();
	private ArrayList<Opponent> opponents = new ArrayList<Opponent>();
	private ArrayList<Opponent> toRemove = new ArrayList<Opponent>();
	private static final int MAX_OPPONENTS = 20;
	
	/**
	 * Constructor to create a new OpponentController.
	 * @param loadResources whether resources using OpenGL should be loaded.
	 */
	public OpponentController(boolean loadResources) {
		this.loadResources = loadResources;
	}
	  
	/**
	 * create a new fish.
	 */
	public void spawnOpponents(Player player) {
		if (opponents.size() < MAX_OPPONENTS) {
			Opponent newOpponent;
			if (random.nextInt(5) > 0) {
				newOpponent = LinearOpponent.createRandom(player, random, loadResources);
			} else {
				newOpponent = SinusOpponent.createRandom(player, random, loadResources);
			}
			addOpponent(newOpponent);
		}
		startBigOpponentEvent(player);
	}

	/**
	 * render all Opponents.
	 * @param graph the graphics.
	 */
	public void renderOpponents(Graphics graph) {
		for (Opponent opponent : opponents) {
			opponent.renderObject(graph);
		}
	}

	/**
	 * Update the opponents.
	 *
	 * @param gc the container holding the game
	 * @param deltaTime the amount of time that has passed since last update in milliseconds
	 */
	public void updateOpponents(GameContainer gc, int deltaTime) {
		for (Opponent opponent : opponents) {
			opponent.objectLogic(gc, deltaTime);
			if (opponent.isOffScreen()) {
				remove(opponent);
			}
		}
		updateRemoveOpponents();
	}
	
	private void updateRemoveOpponents() {
		for (Opponent opponent : toRemove) {
			opponents.remove(opponent);
		}
		toRemove.clear();
	}

	/**
	 * Destroy an opponent.
	 *
	 * @param opponent to destroy
	 */
	public void remove(Opponent opponent) {
		opponent.destroy();
		toRemove.add(opponent);
	}

	/**
	 * Destroy all the opponents.
	 */
	public void removeAllOpponents() {
		for (Opponent opponent : opponents) {
			remove(opponent);
		}
		Main.actionLogger.logLine("All opponents destroyed", getClass().getSimpleName());
	}

	/**
	 * Checking for collisions.
	 *
	 * @param player Player in the game.
	 * @param sbg the game holding the state
	 */
	public void collide(Player player, StateBasedGame sbg) {
		for (Opponent opponent : opponents) {
			if (opponent.intersects(player)) {
				String log = "Player collides with opponent of size " + Math.floor(opponent.getSize());
				Main.actionLogger.logLine(log, getClass().getSimpleName());
				if (player.getSize() > opponent.getSize()) {
					player.eat(opponent);
					remove(opponent);
				} else {
					Main.actionLogger.logLine("Player lost the game", getClass().getSimpleName());
					sbg.enterState(Main.GAME_LOSE_STATE);
				}
			}
		}
	}

	private void startBigOpponentEvent(Player player) {
		double rand = Math.random();
		if(rand < 0.0106 && !bigOpponentInstanceExists()) {
			BigOpponent bigOpponent = BigOpponent.createBigOpponent(player, loadResources);
			addOpponent(bigOpponent);
		}
	}

	private boolean bigOpponentInstanceExists() {
		for (Opponent opponent : opponents) {
			if(opponent instanceof BigOpponent) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Opponent> getToRemove() {
		return toRemove;
	}
	
	public ArrayList<Opponent> getOpponents() {
		return opponents;
	}

	public void addOpponent(Opponent opponent) {
		opponents.add(opponent);
	}
}
