package nl.github.martijn9612.fishy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.Random;

public class Spawner {
  private ArrayList<Fish> fishes;
  private ArrayList<Fish> toRemove = new ArrayList<Fish>();

  public Spawner() {
    fishes = new ArrayList<Fish>();
  }

  /**
   * render all fishes.
   * 
   * @param graph
   *          the graphics.
   */
  public void renderFish(Graphics graph) {
    for (Fish f : fishes) {
      f.renderObject(graph);
    }
  }

  /**
   * update the fishes.
   * 
   * @param gc
   *          the screen.
   * @param deltaTime
   *          no clue.
   */
  public void updateFish(GameContainer gc, int deltaTime) {
    for (Fish f : fishes) {
      f.objectLogic(gc, deltaTime);
    }
    for (Fish f : toRemove) {
      fishes.remove(f);
    }
    toRemove.clear();
  }
  /**
   * create a new fish.
   */
  public void newFish(Player player) {
    if (fishes.size() < 30) {
      Random rand = new Random();
      boolean isleft = rand.nextBoolean();
      double size = (Math.random()*3 + 0.2) * player.getWidth();
      int speed = rand.nextInt(5) + 1;
      int max = 515 - (int) Math.round(size);
      int min = (int) Math.round(size);
      int ypos = rand.nextInt(Math.abs(max - min)) + min;
      if (isleft) {
        Fish fish = new Fish(isleft, 0 - ((int) Math.round(size) * 50), ypos, size, speed, this);
        fishes.add(fish);
      } else {
        Fish fish = new Fish(isleft, 615 + ((int) Math.round(size) * 50), ypos, size, speed, this);
        fishes.add(fish);
      }
    }
    for(Fish f : fishes){
      f.objectRect.setLocation(f.x,f.y);

    }
  }
  
  public void destroy(Fish fishy) {
    toRemove.add(fishy);
  }

  public void collide(Player player, StateBasedGame sbg) {
    for (Fish fish : fishes) {
      if (!fish.objectRect.intersects(player.objectRect)) {
      } else {
        if(player.getWidth() > fish.getWidth()){
          player.eat(fish);
          destroy(fish);
          //ADD SCORE
        } else {
          player.die();
          sbg.enterState(Main.GAME_END_STATE);
        }
      }
    }
  }
}
