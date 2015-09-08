package nl.github.martijn9612.fishy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Skullyhoofd on 08/09/2015.
 */
public class Play extends BasicGameState {

  public String state = "Playing";
  private Player player;
  private Image background;

  public Play(int state) {

  }

  public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    player = new Player();
    background = new Image("resources/sea.jpg");
  }

  public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
      throws SlickException {
    g.drawString(state, 300, 10);
    g.drawImage(background, 0, 0);
    player.renderObject(g);
  }

  public void update(GameContainer gc, StateBasedGame sbg, int delta)
      throws SlickException {
    player.objectLogic(gc, delta);
  }

  public int getID() {
    return 1;
  }
}
