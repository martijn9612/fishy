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
  public void newFish() {
    if (fishes.size() < 10) {
      Random rand = new Random();
      boolean isleft = rand.nextBoolean();
      int size = rand.nextInt(3) + 1;
      int speed = rand.nextInt(5) + 1;
      int max = 515 - (50 * size);
      int min = 0 + (50 * size);
      int ypos = rand.nextInt(max - min) + min;
      if (isleft) {
        Fish fish = new Fish(isleft, 0 - (size * 50), ypos, size, speed, this);
        fishes.add(fish);
        System.out.println(fish.getHeight());

      } else {
        Fish fish = new Fish(isleft, 615 + (size * 50), ypos, size, speed, this);
        fishes.add(fish);
      }
      System.out.println("new fish");
    }
    for(Fish f : fishes){
      f.objectRect.setLocation(f.x,f.y);

    }
  }
  
  public void destroy(Fish fishy) {
    toRemove.add(fishy);
  }

  public Fish collide(Player player, StateBasedGame sbg) {
    for (Fish fish : fishes) {
      if (fish.objectRect.intersects(player.objectRect)) {
        System.out.println("Collision");
        Play.collide = "yes";
        if(player.getWidth() > fish.getWidth()){
          destroy(fish);
          //ADD SCORE

        } else {
          sbg.enterState(0);
          //NEEDS TO BE LOSING SCREEN, NOT MENU

          for(Fish f : fishes){
            destroy(f);
          }
        }
        return fish;
      }
    }
    return null;
  }
}
