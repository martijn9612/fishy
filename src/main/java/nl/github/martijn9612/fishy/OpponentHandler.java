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
	private Whale whale;
	private ArrayList<Whale> whales;
	private WhaleIndicator indicator;
	private boolean whaleEventInProgress = false;
	private MusicPlayer musicPlayer = MusicPlayer.getInstance();

	public OpponentHandler() {
		opponents = new ArrayList<Opponent>();
		toRemove = new ArrayList<Opponent>();
		whales = new ArrayList<Whale>();
	}
	  
	/**
	 * create a new fish.
	 */
	public void newOpponent(Player player) {
		if (opponents.size() < 30) {
			boolean isleft = random.nextBoolean();
			int maxSize = (int) (player.getWidth() * 2.5);
			int minSize = (int) (player.getWidth() * 0.5);
			int size = (random.nextInt((maxSize - minSize)) + minSize);
			int speed = random.nextInt(5) + 1;
			int max = 515 - (int) Math.round(size);
			int min = (int) Math.round(size);
			int ypos = random.nextInt(Math.abs(max - min)) + min;
			int xpos = (isleft ? 0 - ((int) Math.round(size) * 50) : 615 + ((int) Math.round(size) * 50));
			Opponent opponent = new Opponent(isleft, xpos, ypos, size, speed);
			opponents.add(opponent);
		}
	}
  
	/**
	 * render all opponents.
	 * @param graph the graphics.
	 */
	public void renderOpponents(Graphics graph) {
		for (Opponent fish : opponents) {
			fish.renderObject(graph);
		}
	}

	/**
	 * update the opponents.
	 * @param gc the screen.
	 * @param deltaTime no clue.
	 */
	public void updateOpponents(GameContainer gc, int deltaTime, Player player) {
		for (Opponent fish : opponents) {
			fish.objectLogic(gc, deltaTime);
			if(fish.isOffScreen()) {
				destroy(fish);
			}
		}
		for (Opponent fish : toRemove) {
			opponents.remove(fish);
		}
		toRemove.clear();

		if(whaleEventInProgress){
			for(Whale w : whales){
				w.objectLogic(gc, deltaTime);
			}
			indicator.objectLogic(gc, deltaTime, player);
		}
	}

	public void destroy(Opponent fishy) {
		toRemove.add(fishy);
	}

	public void destroyAllOpponents() {
		for (Opponent opponent : opponents) {
			destroy(opponent);
		}
		whales.clear();
		Main.actionLogger.logLine("All opponents destroyed", getClass().getSimpleName());
	}

	public void collide(Player player, StateBasedGame sbg) {
		for (Opponent opponent : opponents) {
			if (opponent.ellipse.intersects(player.ellipse)) {
				Main.actionLogger.logLine("Player collides with opponent of size " + Math.floor(opponent.getSize()), getClass().getSimpleName());
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

		if(whaleEventInProgress) {
			if (player.ellipse.intersects(whale.ellipse)) {
				Main.actionLogger.logLine("Player lost the game because of the whale", getClass().getSimpleName());
				player.resetPlayerVariables();
				destroyAllOpponents();
				sbg.enterState(Main.GAME_LOSE_STATE);
				musicPlayer.stopSound(MusicPlayer.WHALE_EVENT);
			}
		}
	}

	public void startWhaleEvent(Player player, int delta){
		double rand = Math.random();
		if(rand < 0.0006){
			whaleEventInProgress = true;
			int playery = player.getY();
			indicator = new WhaleIndicator(playery);
			whale = new Whale(playery);
			whales.add(whale);
			musicPlayer.playSound(MusicPlayer.WHALE_EVENT);
		}


	}

	public void renderWhaleEvent(Graphics g){
		indicator.renderObject(g);
		for (Whale w : whales) {
			w.renderObject(g);
		}
	}

	public boolean getWhaleEventInProgress(){
		return this.whaleEventInProgress;
	}

	public void setWhaleEventInProgress( boolean status){
		this.whaleEventInProgress = status;
	}
}
