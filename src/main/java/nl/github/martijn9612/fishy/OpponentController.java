package nl.github.martijn9612.fishy;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import nl.github.martijn9612.fishy.models.NonPlayer;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.opponents.BigOpponent;
import nl.github.martijn9612.fishy.opponents.LinearOpponent;
import nl.github.martijn9612.fishy.opponents.SinusOpponent;
import nl.github.martijn9612.fishy.states.LevelState;

/**
 * Implements the Opponent Handler of the game.
 */
public class OpponentController {

	private boolean loadResources;
	private final Random random = new Random();
	private ArrayList<NonPlayer> opponents = new ArrayList<NonPlayer>();
	private ArrayList<NonPlayer> toRemove = new ArrayList<NonPlayer>();
	private static final double BIG_OPPONENT_SPAWN_CHANCE = 0.001;
	private int bigOpponentDelay = 15000;
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
			NonPlayer newOpponent;
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
		for (NonPlayer opponent : opponents) {
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
		for (NonPlayer opponent : opponents) {
			opponent.objectLogic(gc, deltaTime);
			if (opponent.isOffScreen()) {
				remove(opponent);
			}
		}
		updateRemoveOpponents();
	}
	
	private void updateRemoveOpponents() {
		for (NonPlayer opponent : toRemove) {
			opponents.remove(opponent);
		}
		toRemove.clear();
	}

	/**
	 * Destroy an opponent.
	 *
	 * @param opponent to destroy
	 */
	public void remove(NonPlayer opponent) {
		opponent.destroy();
		toRemove.add(opponent);
	}

	/**
	 * Destroy all the opponents.
	 */
	public void removeAllOpponents() {
		for (NonPlayer opponent : opponents) {
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
		for (NonPlayer opponent : opponents) {
			if (opponent.intersects(player)) {
				String log = "Player collides with opponent of size " + Math.floor(opponent.getSize());
				Main.actionLogger.logLine(log, getClass().getSimpleName());
				if (player.getSize() > opponent.getSize()) {
					player.eat(opponent.getSize());
					player.playBiteSound();
					remove(opponent);
				} else {
					if (player.hasShield()) {
                        player.removeShield(2000);
                    } else if (player.getLives() > 0){
				        remove(opponent);
				        player.Loselife();
				    } else {
						Main.actionLogger.logLine("Player lost the game", getClass().getSimpleName());
						player.resetPlayerVariables();
						LevelState.getOC().removeAllOpponents();
						LevelState.getPC().Remove();
						sbg.enterState(Main.GAME_LOSE_STATE);

					if (!player.hasShield()) {
						if (player.getLives() > 0) {
							player.addShield(1000, 1000);
							player.Loselife();
						} else {
							Main.actionLogger.logLine("Player lost the game", getClass().getSimpleName());
							sbg.enterState(Main.GAME_LOSE_STATE);
						}
					}
				}
			}
		}
		}
	}

	private void startBigOpponentEvent(Player player) {
		double rand = Math.random();
		bigOpponentDelay -= 10;
        if(rand < BIG_OPPONENT_SPAWN_CHANCE && !bigOpponentInstanceExists() && bigOpponentDelay < 0) {
			BigOpponent bigOpponent = BigOpponent.createBigOpponent(player.data, loadResources);
			addOpponent(bigOpponent);
		}
	}

	private boolean bigOpponentInstanceExists() {
		for (NonPlayer opponent : opponents) {
			if(opponent instanceof BigOpponent) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<NonPlayer> getToRemove() {
		return toRemove;
	}
	
	public ArrayList<NonPlayer> getOpponents() {
		return opponents;
	}

	public void addOpponent(NonPlayer opponent) {
		opponents.add(opponent);
	}
}
