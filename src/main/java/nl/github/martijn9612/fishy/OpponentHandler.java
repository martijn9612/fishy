package nl.github.martijn9612.fishy;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Implements the Opponent Handler of the game.
 */
public class OpponentHandler {

    private ArrayList<Opponent> opponents;
    private ArrayList<Opponent> toRemove;
    private Random random = new Random();

    private static final int MAX_OPPLIST_SIZE = 30;
    private static final double MAX_SCALE_FACTOR = 2.5;
    private static final double MIN_SCALE_FACTOR = 0.5;
    private static final int MAX_SPEED = 5;
    private static final int MAX_YPOS = 515;
    private static final int MAX_XPOS = 615;
    private static final int SIZE_SCALE_FACTOR = 50;

    /**
     * Constructor for the OpponentHandler.
     */
    public OpponentHandler() {
        opponents = new ArrayList<Opponent>();
        toRemove = new ArrayList<Opponent>();
    }

    /**
     * Create a new opponent.
     * 
     * @param player
     *            - the fish the user is playing with
     */
    public void newOpponent(Player player) {
        if (opponents.size() < MAX_OPPLIST_SIZE) {
            boolean isleft = random.nextBoolean();
            int maxSize = (int) (player.getWidth() * MAX_SCALE_FACTOR);
            int minSize = (int) (player.getWidth() * MIN_SCALE_FACTOR);
            int size = (random.nextInt((maxSize - minSize)) + minSize);
            int speed = random.nextInt(MAX_SPEED) + 1;
            int max = MAX_YPOS - (int) Math.round(size);
            int min = (int) Math.round(size);
            int ypos = random.nextInt(Math.abs(max - min)) + min;
            int xpos = 0;
            if (isleft) {
                xpos = 0 - ((int) Math.round(size) * SIZE_SCALE_FACTOR);
            } else {
                xpos = MAX_XPOS + ((int) Math.round(size) * SIZE_SCALE_FACTOR);
            }
            Opponent opponent = new Opponent(isleft, xpos, ypos, size, speed);
            opponents.add(opponent);
        }
    }

    /**
     * Render all the opponents.
     * 
     * @param graph
     *            - the graphic content used to render
     */
    public void renderOpponents(Graphics graph) {
        for (Opponent opponent : opponents) {
            opponent.renderObject(graph);
        }
    }

    /**
     * Update the opponents.
     * 
     * @param gc
     *            - the container holding the game
     * @param deltaTime
     *            - the amount of time that has passed since last update in
     *            milliseconds
     */
    public void updateOpponents(GameContainer gc, int deltaTime) {
        for (Opponent opponent : opponents) {
            opponent.objectLogic(gc, deltaTime);
            if (opponent.isOffScreen()) {
                destroy(opponent);
            }
        }
        for (Opponent opponent : toRemove) {
            opponents.remove(opponent);
        }
        toRemove.clear();
    }

    /**
     * Destroy an opponent.
     * 
     * @param fishy
     *            - opponent to destroy
     */
    public void destroy(Opponent fishy) {
        toRemove.add(fishy);
    }

    /**
     * Destroy all the opponents.
     */
    public void destroyAllOpponents() {
        for (Opponent opponent : opponents) {
            destroy(opponent);
        }
        Main.actionLogger.logLine("All opponents destroyed", getClass()
                .getSimpleName());
    }

    /**
     * Checking for collisions.
     * 
     * @param player
     *            - Player in the game.
     * @param sbg
     *            - the game holding the state
     */
    public void collide(Player player, StateBasedGame sbg) {
        for (Opponent opponent : opponents) {
            if (opponent.ellipse.intersects(player.ellipse)) {
                String log = "Player collides with opponent of size "
                        + Math.floor(opponent.getSize());
                Main.actionLogger.logLine(log, getClass().getSimpleName());
                if (player.getWidth() > opponent.getWidth()) {
                    player.eat(opponent);
                    destroy(opponent);
                } else {
                    Main.actionLogger.logLine("Player lost the game",
                            getClass().getSimpleName());
                    player.resetPlayerVariables();
                    destroyAllOpponents();
                    sbg.enterState(Main.GAME_LOSE_STATE);
                }
            }
        }
    }

}
