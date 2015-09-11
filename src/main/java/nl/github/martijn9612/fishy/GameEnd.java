package nl.github.martijn9612.fishy;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class GameEnd extends BasicGameState {
	
	Image deadFish;
	
	public GameEnd(int state) {
		
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		deadFish = new Image("resources/dead-fish.png");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.fillRect(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
		g.setColor(Color.black);
		g.drawString("Urr a dead fish! :(", 230, 100);
		g.drawImage(deadFish, 220, 150);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return Main.GAME_END_STATE;
	}

}
