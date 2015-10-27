package nl.github.martijn9612.fishy.powerups;

import nl.github.martijn9612.fishy.models.Entity;
import nl.github.martijn9612.fishy.models.Moveable;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.models.Vector;

import java.util.Random;

/**
 * Implements the Shield powerup.
 * Software Engineering Methods Project - Group 11.
 */
public class Shield extends Powerup {
    private static final String SPRITE_PATH = "resources/shield.png";
    private int chance;

    /**
     * Creates a new Shield powerup.
     * @param data - the moveable data of the new Shield.
     * @param loadResources - true if OpenGL content should be loaded, false if not.
     * @param chance - the chance its gets returned in powerupfactary, in promille.
     */
    public Shield(Moveable data, boolean loadResources, int chance) {
        super(data, loadResources, chance);
        loadResources(SPRITE_PATH);
        this.chance = chance;
        if(loadResources && data.getVelocity().x > 0) {
            setImageOrientation(Entity.IMAGE_ORIENTATE_RIGHT);
        }
    }

    /**
     * Creates an instance of Shield at a random screen side location.
     * @param random an instance to generate random numbers.
     * @param loadResources whether the sprite resources should be loaded.
     * @return a new Shield powerup at a random screen side location.
     */
    public static Shield createPowerup(Random random, boolean loadResources) {
    	Moveable data = new Moveable();
    	boolean spawnsLeft = random.nextBoolean();
    	data.setDimensions(new Vector(32,32));
    	data.setVelocity(getRandomVelocity(random, spawnsLeft));
    	data.setPosition(getRandomPosition(random, spawnsLeft, data.getDimensions()));
        return new Shield(data, loadResources, 30);
    }

    /**
     * Creates a random position vector for the Shield class.
     * @param random - an instance to generate random numbers.
     * @param spawnsLeft - boolean which tells if the opponent spawns left or right.
     * @param dimensions - Vector with the dimensions of the Shield.
     * @return Vector with the Shield location.
     */
    private static Vector getRandomPosition(Random random, boolean spawnsLeft, Vector dimensions) {
        int min = Math.round(dimensions.x);
        int max = 515 - min;
        int ypos = random.nextInt(Math.abs(max - min)) + min;
        int xpos = (spawnsLeft ? 0 - min * 5 : 615 + min * 5);
        return new Vector(xpos, ypos);
    }

    /**
     * Creates a random velocity vector for the Shield class.
     * @param random - an instance to generate random numbers.
     * @param spawnsLeft - boolean which tells if the opponent spawns left or right.
     * @return Vector with Shield velocity.
     */
    private static Vector getRandomVelocity(Random random, boolean spawnsLeft) {
        int speed = random.nextInt(4) + 1;
        return new Vector((spawnsLeft ? speed : -speed), 0);
    }

    /**
     * Gets the chance that Poison is chosen in the PowerupFactory.
     * @return the chance of Poison.
     */
    public int getChance(){
        return chance;
    }

    /**
     * Starts the effect of the Shield powerup 
     * upon collision with a player.
     * @param player - the current Player in the game.
     */
    @Override
    public void Effect(Player player){
        player.addShield(5000,2000);
    }
}
