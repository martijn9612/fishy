package nl.github.martijn9612.fishy.powerups;

import java.util.Random;

import nl.github.martijn9612.fishy.models.Entity;
import nl.github.martijn9612.fishy.models.Moveable;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.models.Vector;

/**
 * Implements the ExtraLife powerup.
 * Software Engineering Methods Project - Group 11.
 */
public class ExtraLife extends Powerup {
    private static final String SPRITE_PATH = "resources/ExtraLife-fish.png";
    private int chance;
    
    /**
     * Creates a new ExtraLife powerup.
     * @param data - the moveable data of the new ExtraLife.
     * @param loadResources - true if OpenGL content should be loaded, false if not.
     * @param chance - the chance its gets returned in powerupfactary, in promille.
     */
    public ExtraLife(Moveable data, boolean loadResources, int chance) {
        super(data, loadResources, chance);
        loadResources(SPRITE_PATH);
        this.chance = chance;
        if (loadResources && data.getVelocity().x > 0) {
            setImageOrientation(Entity.IMAGE_ORIENTATE_RIGHT);
        }
    }

    /**
     * Creates an instance of ExtraLife at a random screen side location.
     * @param random an instance to generate random numbers.
     * @param loadResources whether the sprite resources should be loaded.
     * @return a new ExtraLife powerup at a random screen side location.
     */
    public static ExtraLife createPowerup(Random random, boolean loadResources) {
        Moveable data = new Moveable();
    	boolean spawnsLeft = random.nextBoolean();
    	data.setDimensions(new Vector(16,16));
    	data.setVelocity(getRandomVelocity(random, spawnsLeft));
    	data.setPosition(getRandomPosition(random, spawnsLeft, data.getDimensions()));
        return new ExtraLife(data, loadResources, 30);
    }
    
    /**
     * Creates a random position vector for the ExtraLife class.
     * @param random - an instance to generate random numbers.
     * @param spawnsLeft - boolean which tells if the opponent spawns left or right.
     * @param dimensions - Vector with the dimensions of the ExtraLife.
     * @return Vector with the ExtraLife location.
     */
    private static Vector getRandomPosition(Random random, boolean spawnsLeft, Vector dimensions) {
        int min = Math.round(dimensions.x);
        int max = 515 - min;
        int ypos = random.nextInt(Math.abs(max - min)) + min;
        int xpos = (spawnsLeft ? 0 - min * 5 : 615 + min * 5);
        return new Vector(xpos, ypos);
    }
    
    /**
     * Creates a random velocity vector for the ExtraLife class.
     * @param random - an instance to generate random numbers.
     * @param spawnsLeft - boolean which tells if the opponent spawns left or right.
     * @return Vector with ExtraLife velocity.
     */
    private static Vector getRandomVelocity(Random random, boolean spawnsLeft) {
        int speed = random.nextInt(4) + 1;
        return new Vector((spawnsLeft ? speed : -speed), 0);
    }
    
    /**
     * Gets the chance that ExtraLife is chosen in the PowerupFactory.
     * @return the chance of ExtraLife.
     */
    public int getChance() {
        return chance;
    }
    
    /**
     * Starts the effect of the ExtraLife powerup 
     * upon collision with a player.
     * @param player - the current Player in the game.
     */
    public void Effect(Player player) {
        player.Extralife();
    }
}
