package nl.github.martijn9612.fishy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.Random;

public class OpponentHandler {
  private ArrayList<Opponent> opponents;
  private ArrayList<Opponent> toRemove = new ArrayList<Opponent>();
  private Random random = new Random();

  public OpponentHandler() {
    opponents = new ArrayList<Opponent>();
  }

  /**
   * render all opponents.
   * @param graph the graphics.
   */
  public void renderOpponents(Graphics graph) {
    for (Opponent f : opponents) {
      f.renderObject(graph);
    }
  }

  /**
   * update the opponents.
   * @param gc the screen.
   * @param deltaTime no clue.
   */
  public void updateOpponents(GameContainer gc, int deltaTime) {
    for (Opponent f : opponents) {
      f.objectLogic(gc, deltaTime);
    }
    for (Opponent f : toRemove) {
      opponents.remove(f);
    }
    toRemove.clear();
  }
  /**
   * create a new fish.
   */
  public void newOpponent(Player player) {
    if (opponents.size() < 30) {
      boolean isleft = random.nextBoolean();
      double size = (random.nextDouble() * 3 + 0.2) * player.getWidth();
      int speed = random.nextInt(5) + 1;
      int max = 515 - (int) Math.round(size);
      int min = (int) Math.round(size);
      int ypos = random.nextInt(Math.abs(max - min)) + min;
      if (isleft) {
        Opponent opponent = new Opponent(isleft, 0 - ((int) Math.round(size) * 50), ypos, size, speed, this);
        opponents.add(opponent);
      } else {
        Opponent opponent = new Opponent(isleft, 615 + ((int) Math.round(size) * 50), ypos, size, speed, this);
        opponents.add(opponent);
      }
    }
  }

  public void destroy(Opponent fishy) {
    toRemove.add(fishy);
  }

  public void destroyAllOpponents() {
	  for (Opponent opponent : opponents) {
		  opponent.destroy();
	  }
  }

  public void collide(Player player, StateBasedGame sbg) {
    for (Opponent opponent : opponents) {
      if (!opponent.objectRect.intersects(player.objectRect)) {
      } else {
        if(player.getWidth() > opponent.getWidth()){
          player.eat(opponent);
          destroy(opponent);
          //ADD SCORE
        } else {
          player.die();
          destroyAllOpponents();
          sbg.enterState(Main.GAME_END_STATE);
        }
      }
    }
  }
}
