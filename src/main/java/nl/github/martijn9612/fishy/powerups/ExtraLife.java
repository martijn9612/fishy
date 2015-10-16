package nl.github.martijn9612.fishy.powerups;

import java.util.Random;

import nl.github.martijn9612.fishy.models.Entity;
import nl.github.martijn9612.fishy.models.Moveable;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.models.Vector;

public class ExtraLife extends Powerup{
    private static final String SPRITE_PATH = "resources/ExtraLife-fish.png";
    private int chance;
    /**
     * the constructor of extra life.
     * @param dimensions the size of the power up.
     * @param position the starting position.
     * @param velocity the speed it travels.
     * @param acceleration the speed at which it speeds up.
     * @param loadResources whether or not to load resources.
     * @param chance the chance its gets returned in powerupfactary, in promille.
     */
    public ExtraLife(Moveable data, boolean loadResources, int chance) {
        super(data, loadResources, chance);
        loadResources(SPRITE_PATH);
        this.chance = chance;
        if(loadResources && data.velocity.x > 0) {
            setImageOrientation(Entity.IMAGE_ORIENTATE_RIGHT);
        }
    }

    /**
     * Creates an instance of ExtraLife at a random screen side location.
     * 
     * @param player an instance of the Player class.
     * @param random an instance to generate random numbers.
     * @param loadResources whether the sprite resources should be loaded.
     */
    public static ExtraLife createPowerup(Random random, boolean loadResources) {
        Moveable data = new Moveable();
    	boolean spawnsLeft = random.nextBoolean();
    	data.dimensions = new Vector(16,16);
    	data.velocity = getRandomVelocity(random, spawnsLeft);
    	data.position = getRandomPosition(random, spawnsLeft, data.dimensions);
        return new ExtraLife(data, loadResources, 30);
    }
    /**
     * creates a random position vector
     * @param random the randomizer.
     * @param spawnsLeft whether it spawns left or right.
     * @param dimensions the size of the powerup.
     * @return the start position of the power up.
     */
    private static Vector getRandomPosition(Random random, boolean spawnsLeft, Vector dimensions) {
        int min = Math.round(dimensions.x);
        int max = 515 - min;
        int ypos = random.nextInt(Math.abs(max - min)) + min;
        int xpos = (spawnsLeft ? 0 - min * 5 : 615 + min * 5);
        return new Vector(xpos, ypos);
    }
    /**
     * create a random speed vector.
     * @param random the randomizer
     * @param spawnsLeft whether it spawns left or right.
     * @return the speed vector.
     */
    private static Vector getRandomVelocity(Random random, boolean spawnsLeft) {
        int speed = random.nextInt(4) + 1;
        return new Vector((spawnsLeft ? speed : -speed), 0);
    }
    /**
     * returns the chance that extralife gets chosen in the factory.
     */
    public int getChance(){
        return chance;
    }
    /**
     * the effect of this powerup upon collision.
     */
    public void Effect(Player player){
        player.Extralife();
    }
}
