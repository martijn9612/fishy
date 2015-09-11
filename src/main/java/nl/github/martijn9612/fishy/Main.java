package nl.github.martijn9612.fishy;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {
  public static final String GAME_NAME = "Fishy";
  public static final int WINDOW_WIDTH = 650;
  public static final int WINDOW_HEIGHT = 550;
  public static final int MENU_STATE = 0;
  public static final int PLAY_STATE = 1;
  public static final int GAME_END_STATE = 2;
  public static final String PLAYER_CHARACTER = "fish";

  public Main(String gameName) {
    super(gameName);
    this.addState(new Menu(MENU_STATE));
    this.addState(new Play(PLAY_STATE));
    this.addState(new GameEnd(GAME_END_STATE));
  }

  @Override
  public void initStatesList(GameContainer gc) throws SlickException {
    this.getState(MENU_STATE).init(gc, this);
    this.getState(PLAY_STATE).init(gc, this);
    this.getState(GAME_END_STATE).init(gc, this);
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
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}