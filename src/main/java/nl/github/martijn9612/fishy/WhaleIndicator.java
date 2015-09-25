package nl.github.martijn9612.fishy;

import org.newdawn.slick.GameContainer;

/**
 * The arrow which indicates that a whale is coming
 * Created by Skullyhoofd on 25/09/2015.
 */
public class WhaleIndicator extends Entity {

    private int yPos;
    private String IMAGE_NAME = "whale";
    private int xPos = 500;

    public WhaleIndicator( int playery) {
        this.yPos = playery;
        this.loadImage(IMAGE_NAME);
        this.setPosition(xPos, yPos);
        this.setDimensions(this.width, this.height);
        this.calculateInitialBoundingbox();
        this.setSpeed(0);

    }

    public void objectLogic(GameContainer gc, int deltaTime, Player player) {
        this.y = player.getY();
        ellipse.setCenterX(ellipse.getCenterX());
    }


}
