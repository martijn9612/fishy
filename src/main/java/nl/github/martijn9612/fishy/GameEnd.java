package nl.github.martijn9612.fishy;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import nl.github.martijn9612.fishy.models.DrawRectangle;
import nl.github.martijn9612.fishy.models.MousePosition;
import nl.github.martijn9612.fishy.models.MouseRectangle;


public class GameEnd extends BasicGameState {
	
	private Image play;
	private Image deadFish;
	private MousePosition mouse;
	private DrawRectangle playButtonDR;
	private MouseRectangle playButtonMR;
	
	private static int PLAY_BUTTON_DRAW_X = 150;
	private static int PLAY_BUTTON_DRAW_Y = 350;
	private static int DEAD_FISH_DRAW_X = 220;
	private static int DEAD_FISH_DRAW_Y = 150;
	private static int DEAD_TEXT_DRAW_X = 230;
	private static int DEAD_TEXT_DRAW_Y = 100;
	private static String DEAD_TEXT_STRING = "Urr a dead fish! :(";
	
	public GameEnd(int state) {
		// Blank
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		play = new Image("resources/play-button.gif");
		deadFish = new Image("resources/dead-fish.png");
		playButtonDR = new DrawRectangle(PLAY_BUTTON_DRAW_X, PLAY_BUTTON_DRAW_Y, play.getWidth(), play.getHeight());
		playButtonMR = playButtonDR.getMouseRectangle();
		mouse = new MousePosition();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.fillRect(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
		g.drawImage(play, playButtonDR.getPositionX(), playButtonDR.getPositionY());
		g.setColor(Color.black);
		g.drawString(DEAD_TEXT_STRING, DEAD_TEXT_DRAW_X, DEAD_TEXT_DRAW_Y);
		g.drawImage(deadFish, DEAD_FISH_DRAW_X, DEAD_FISH_DRAW_Y);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        mouse.updatePosition();
        
        if(mouse.isInRectangle(playButtonMR)) {
        	if(mouse.isLeftButtonDown()) {
        		game.enterState(Main.PLAY_STATE);
        	}
        }
	}

	@Override
	public int getID() {
		return Main.GAME_END_STATE;
	}

}
