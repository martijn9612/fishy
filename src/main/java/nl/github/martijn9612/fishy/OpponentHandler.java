package nl.github.martijn9612.fishy;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class OpponentHandler {

	private ArrayList<Opponent> opponents;
	private ArrayList<Opponent> toRemove;
	private Random random = new Random();

	public OpponentHandler() {
		opponents = new ArrayList<Opponent>();
		toRemove = new ArrayList<Opponent>();
	}
	  
	/**
	 * create a new fish.
	 */
	public void spawnOpponents(Player player) {
		if (opponents.size() < 20) {
			if ((random.nextInt(5) + 1) > 1) {
				newLinearOpponent(player);
			} else {
				newSinusOpponent(player);
			}
		}
	}

	private void newLinearOpponent(Player player) {
		boolean isleft = random.nextBoolean();
		int maxSize = (int) (player.getWidth() * 2);
		int minSize = (int) (player.getWidth() * 0.5);
		int size = (random.nextInt((maxSize - minSize)) + minSize);
		int speed = random.nextInt(4) + 1;
		int max = 515 - size;
		int min = size;
		int ypos = random.nextInt(Math.abs(max - min)) + min;
		int xpos = (isleft ? 0 - size * 5 : 615 + size * 5);
		LinearOpponent linearOpponent = new LinearOpponent(isleft, xpos, ypos, size, speed);
		opponents.add(linearOpponent);
	}

	private void newSinusOpponent(Player player) {
		int maxSize = (int) (player.getWidth() * 2.5);
		int minSize = (int) (player.getWidth() * 0.5);
		int size = (random.nextInt((maxSize - minSize)) + minSize);
		int max = 615 - (int) Math.round(size);
		int min = (int) Math.round(size);
		int xpos = random.nextInt(Math.abs(max - min)) + min;
		SinusOpponent sinusOpponent = new SinusOpponent(xpos, size);
		opponents.add(sinusOpponent);
	}

	/**
	 * render all linearOpponents.
	 * @param graph the graphics.
	 */
	public void renderOpponents(Graphics graph) {
		for (Opponent opponent : opponents) {
			opponent.renderObject(graph);
		}
	}

	/**
	 * update the linearOpponents.
	 * @param gc the screen.
	 * @param deltaTime no clue.
	 */
	public void updateOpponents(GameContainer gc, int deltaTime) {
		for (Opponent opponent : opponents) {
			opponent.objectLogic(gc, deltaTime);
			if(opponent.isOffScreen()) {
				destroy(opponent);
			}
		}
		for (Opponent fish : toRemove) {
			opponents.remove(fish);
		}
		toRemove.clear();
	}

	public void destroy(Opponent opponent) {
		toRemove.add(opponent);
	}

	public void destroyAllOpponents() {
		for (Opponent opponent : opponents) {
			destroy(opponent);
		}
		Main.actionLogger.logLine("All linearOpponents destroyed", getClass().getSimpleName());
	}

	public void collide(Player player, StateBasedGame sbg) {
		for (Opponent opponent : opponents) {
			if (opponent.ellipse.intersects(player.ellipse)) {
				Main.actionLogger.logLine("Player collides with linearOpponent of size " + Math.floor(opponent.getSize()), getClass().getSimpleName());
				if (player.getWidth() > opponent.getWidth()) {
					player.eat(opponent);
					destroy(opponent);
				} else {
					Main.actionLogger.logLine("Player lost the game", getClass().getSimpleName());
					player.resetPlayerVariables();
					destroyAllOpponents();
					sbg.enterState(Main.GAME_LOSE_STATE);
				}
			}
		}
	}
	
}
