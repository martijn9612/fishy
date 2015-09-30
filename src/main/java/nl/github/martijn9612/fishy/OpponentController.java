package nl.github.martijn9612.fishy;

import java.util.ArrayList;
import java.util.Random;

import nl.github.martijn9612.fishy.opponents.BigOpponent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import nl.github.martijn9612.fishy.models.Opponent;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.models.BigOpponentIndicator;
import nl.github.martijn9612.fishy.opponents.LinearOpponent;
import nl.github.martijn9612.fishy.opponents.SinusOpponent;
import nl.github.martijn9612.fishy.utils.MusicPlayer;

/**
 * Implements the Opponent Handler of the game.
 */
public class OpponentController {

	private MusicPlayer musicPlayer;
	private BigOpponentIndicator indicator;
	private boolean whaleEventInProgress = false;
	private ArrayList<Opponent> opponents = new ArrayList<Opponent>();
	private ArrayList<Opponent> toRemove = new ArrayList<Opponent>();
	private final Random random = new Random();
	private boolean loadResources;
	
	private static final int MAX_OPPONENTS = 20;

	public OpponentController(boolean loadResources) {
		this.loadResources = loadResources;
		if (loadResources) {
			musicPlayer = MusicPlayer.getInstance();
		}
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
			opponents.add(newOpponent);
		}
	}

	/**
	 * render all Opponents.
	 * @param graph the graphics.
	 */
	public void renderOpponents(Graphics graph) {
		for (Opponent opponent : opponents) {
			opponent.renderObject(graph);
		}
		if (whaleEventInProgress) {
			indicator.renderObject(graph);
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
				destroy(opponent);
			}
		}
		for (Opponent opponent : toRemove) {
			opponents.remove(opponent);
		}
		if (whaleEventInProgress) {
			indicator.objectLogic(gc, deltaTime);
		}
		toRemove.clear();
	}

	/**
	 * Destroy an opponent.
	 *
	 * @param opponent to destroy
	 */
	public void destroy(Opponent opponent) {
		toRemove.add(opponent);
	}

	/**
	 * Destroy all the opponents.
	 */
	public void destroyAllOpponents() {
		for (Opponent opponent : opponents) {
			destroy(opponent);
		}
		whaleEventInProgress = false;
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
					destroy(opponent);
				} else {
					Main.actionLogger.logLine("Player lost the game", getClass().getSimpleName());
					sbg.enterState(Main.GAME_LOSE_STATE);
				}
			}
		}
	}

	public void startWhaleEvent(Player player) {
		double rand = Math.random();
		if(rand < 0.0006) {
			whaleEventInProgress = true;
			indicator = new BigOpponentIndicator(player, true);
			BigOpponent bigOpponent = new BigOpponent(player, true);
			opponents.add(bigOpponent);
			musicPlayer.playSound(MusicPlayer.WHALE_EVENT);
		}
	}

	public boolean isWhaleEventInProgress() {
		return this.whaleEventInProgress;
	}

	public void setWhaleEventInProgress(boolean status) {
		this.whaleEventInProgress = status;
	}
}
