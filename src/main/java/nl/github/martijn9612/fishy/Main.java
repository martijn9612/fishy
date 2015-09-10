package nl.github.martijn9612.fishy;

import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {
  private static final int MENU_STATE = 0;
  private static final int PLAY_STATE = 1;
  private static final String GAME_NAME = "Fishy";
  public static final int WINDOW_WIDTH = 650;
  public static final int WINDOW_HEIGHT = 550;
  public static final String PLAYER_CHARACTER = "fish";
  public static final String OPPONENT_CHARACTER = "fish";
  public static final String LEVEL_BACKGROUND = "seabed";

  public Main(String gameName) {
    super(gameName);
    this.addState(new MenuState(MENU_STATE));
    this.addState(new LevelState(PLAY_STATE));
  }

  @Override
  public void initStatesList(GameContainer gc) throws SlickException {
    this.getState(MENU_STATE).init(gc, this);
    this.getState(PLAY_STATE).init(gc, this);
    this.enterState(MENU_STATE);
  }

  public static void main(String[] args) {
    try {
      AppGameContainer appgc;
      appgc = new AppGameContainer(new Main(GAME_NAME));
      appgc.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, false);
      appgc.setTargetFrameRate(60);
      appgc.start();
    } catch (SlickException ex) {
      Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
  }
}