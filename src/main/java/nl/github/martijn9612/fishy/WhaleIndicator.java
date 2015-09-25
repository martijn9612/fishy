package nl.github.martijn9612.fishy;

/**
 * The arrow which indicates that a whale is coming
 * Created by Skullyhoofd on 25/09/2015.
 */
public class WhaleIndicator extends Entity {

    private int yPos;
    private String IMAGE_NAME = "arrow";
    private int xPos = 550;

    public WhaleIndicator( int playery) {
        this.yPos = playery;
        this.loadImage(IMAGE_NAME);
        this.setPosition(xPos, yPos);
        this.calculateInitialBoundingbox();

    }


}
