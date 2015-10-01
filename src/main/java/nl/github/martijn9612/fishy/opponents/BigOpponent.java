package nl.github.martijn9612.fishy.opponents;

import org.newdawn.slick.GameContainer;

import nl.github.martijn9612.fishy.models.Opponent;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.models.Vector;

/**
 * The whale class. The whale is a special opponent which has a small chance to spawn and takes up a large portion of the screen.
 * Created by Skullyhoofd on 25/09/2015.
 */
public class BigOpponent extends Opponent {

    private static final String SPRITE_PATH = "resources/whale.png";
    private static final float WHALE_START_POS_X = 930;
    private static final float WHALE_SIZE = 350;
    private static final float WHALE_SPEED = 1;

    public BigOpponent(Player player, boolean loadResources) {
    	super(loadResources);
    	this.loadResources(SPRITE_PATH);
        position = new Vector(WHALE_START_POS_X, player.position.y - WHALE_SIZE / 2);
        dimensions = new Vector(WHALE_SIZE * 1.15f, WHALE_SIZE);
        velocity = new Vector(-WHALE_SPEED, 0);
        calculateBoundingbox();
    }

    public void objectLogic(GameContainer gc, int deltaTime) {
        position.add(velocity);
        calculateBoundingbox();
    }

}
