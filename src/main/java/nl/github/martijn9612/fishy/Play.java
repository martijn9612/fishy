package nl.github.martijn9612.fishy;

import org.newdawn.slick.Color;
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
  public Player player;
  private Image background;
  private Spawner spawner;
  public static String score =  "0";
  public String fishpos = "(" + 0 + "," + 0 + ")";

  public Play(int state) {
  }

  /**
   * initialisation of the play screen.
   */
  public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    player = new Player();
    background = new Image("resources/sea.jpg");
    spawner = new Spawner();
  }

  /**
   * render everything in the playscreen.
   */
  public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
      throws SlickException {
    g.setColor(Color.black);
    g.drawString(state, 300, 10);
    g.drawImage(background, 0, 0);
    g.drawString(fishpos, 300, 10);
    g.drawString(score, 450, 10);
    player.renderObject(g);
    spawner.renderFish(g);
  }

  /**
   * update everything in the gamescreen.
   */
  public void update(GameContainer gc, StateBasedGame sbg, int delta)
      throws SlickException {
    player.objectLogic(gc, delta);
    spawner.updateFish(gc, delta);
    player.objectRect.setLocation(player.x,player.y);
    spawner.newFish(player);
    fishpos = "(" + player.x + "," + player.y + ")";
    spawner.collide(player, sbg);
  }

  /**
   * get id.
   */
  public int getID() {
    return Main.PLAY_STATE;
  }
}
