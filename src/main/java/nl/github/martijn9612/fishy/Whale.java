package nl.github.martijn9612.fishy;

import org.newdawn.slick.GameContainer;

/**
 * The whale class. The whale is a special opponent which has a small chance to spawn and takes up a large portion of the screen.
 * Created by Skullyhoofd on 25/09/2015.
 */
public class Whale extends Entity {

    private int yPos;
    private String IMAGE_NAME = "whale";
    private int xPos = 930;
    private double size = 350;
    private int speed = 1;


    public Whale( int playery) {
        this.yPos = playery - (int) Math.floor(size/2);
        this.loadImage(IMAGE_NAME);
        this.setPosition(xPos, yPos);
        this.setDimensions((int) Math.round(this.size*1.15), (int) Math.round(this.size));
        this.setSpeed(speed);
        this.calculateInitialBoundingbox();
    }

    public void objectLogic(GameContainer gc, int deltaTime) {
        this.x -= speed;
        ellipse.setCenterX(ellipse.getCenterX() - speed);
    }




}
