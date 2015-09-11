package nl.github.martijn9612.fishy;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class GameEnd extends BasicGameState {
	
	Image play;
	Image deadFish;
	int playButtonX = 150;
	int playButtonY = 350;
	
	public GameEnd(int state) {
		// Blank
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		play = new Image("resources/play-button.gif");
		deadFish = new Image("resources/dead-fish.png");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.fillRect(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
		g.setColor(Color.black);
		g.drawString("Urr a dead fish! :(", 230, 100);
		g.drawImage(deadFish, 220, 150);
		g.drawImage(play, playButtonX, playButtonY);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
        int xPos = Mouse.getX();
        int yPos = Mouse.getY();
        int playButtonTop = Main.WINDOW_HEIGHT - playButtonY;
        
        if((xPos > playButtonX && xPos < (playButtonX + 350)) && (yPos > playButtonTop - 150 && yPos < playButtonTop)) {
            if(input.isMouseButtonDown(0)) {
            	game.enterState(Main.PLAY_STATE);
            }
        }
	}

	@Override
	public int getID() {
		return Main.GAME_END_STATE;
	}

}
