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
public class LevelState extends BasicGameState {
    public String state = "Playing";
    public Player player;
    private Image background;
    public static String score =  "0";
    private OpponentHandler opponentHandler;
    public String fishPosition = "(" + 0 + "," + 0 + ")";

  public LevelState(int state) {

  }

  /**
   * initialisation of the play screen.
   */
  public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
      Main.actionLogger.logLine("Entered level", getClass().getSimpleName());
      player = new Player();
    background = new Image("resources/" + Main.LEVEL_BACKGROUND + ".jpg");
    opponentHandler = new OpponentHandler();
  }

  /**
   * render everything in the playscreen.
   */
  public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
      throws SlickException {
    g.setColor(Color.black);
    g.drawString(state, 300, 10);
    g.drawImage(background, 0, 0);
    g.drawString(fishPosition, 300, 10);
    g.drawString(score, 450, 10);
    player.renderObject(g);
    opponentHandler.renderOpponents(g);
  }


  /**
   * update everything in the gamescreen.
   */
  public void update(GameContainer gc, StateBasedGame sbg, int delta)
      throws SlickException {
    player.objectLogic(gc, delta);
    opponentHandler.collide(player, sbg);
    opponentHandler.updateOpponents(gc, delta);
    opponentHandler.newOpponent(player);
    fishPosition = "(" + player.x + "," + player.y + ")";
  }

  /**
   * get id.
   */
  public int getID() {
    return Main.PLAY_STATE;
  }
}
