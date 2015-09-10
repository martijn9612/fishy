package nl.github.martijn9612.fishy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

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
  public void renderFish(Graphics graph) {
    for (Opponent f : opponents) {
      f.renderObject(graph);
    }
  }

  /**
   * update the opponents.
   * @param gc the screen.
   * @param deltaTime no clue.
   */
  public void updateFish(GameContainer gc, int deltaTime) {
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
  public void newFish() {
    if (opponents.size() < 10) {
      boolean isleft = random.nextBoolean();
      int size = random.nextInt(3) + 1;
      int speed = random.nextInt(5) + 1;
      int max = 515 - (50 * size);
      int min = 0 + (50 * size);
      int ypos = random.nextInt(max - min) + min;
      if (isleft) {
        Opponent opponent = new Opponent(isleft, 0 - (size * 50), ypos, size, speed, this);
        opponents.add(opponent);
      } else {
        Opponent opponent = new Opponent(isleft, 615 + (size * 50), ypos, size, speed, this);
        opponents.add(opponent);
      }
    }
  }
  
  public void destroy(Opponent fishy) {
    toRemove.add(fishy);
  }
}
