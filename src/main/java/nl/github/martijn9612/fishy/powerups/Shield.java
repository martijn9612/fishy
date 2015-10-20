package nl.github.martijn9612.fishy.powerups;

import nl.github.martijn9612.fishy.models.Entity;
import nl.github.martijn9612.fishy.models.Moveable;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.models.Vector;

import java.util.Random;

/**
 * Created by martijn on 9-10-15.
 */
public class Shield extends Powerup{
    private static final String SPRITE_PATH = "resources/shield.png";
    private int chance;

    public Shield(Moveable data, boolean loadResources, int chance) {
        super(data, loadResources, chance);
        loadResources(SPRITE_PATH);
        this.chance = chance;
        if(loadResources && data.velocity.x > 0) {
            setImageOrientation(Entity.IMAGE_ORIENTATE_RIGHT);
        }
    }
    /**
     * Creates an instance of shield at a random screen side location.
     *
     * @param random an instance to generate random numbers.
     * @param loadResources whether the sprite resources should be loaded.
     */
    public static Shield createPowerup(Random random, boolean loadResources) {
    	Moveable data = new Moveable();
    	boolean spawnsLeft = random.nextBoolean();
    	data.dimensions = new Vector(32,32);
    	data.velocity = getRandomVelocity(random, spawnsLeft);
    	data.position = getRandomPosition(random, spawnsLeft, data.dimensions);
        return new Shield(data, loadResources, 30);
    }

    /**
     * create a random position vector.
     * @param random the randomizer.
     * @param spawnsLeft whether it starts left or right.
     * @param dimensions size of the instance.
     * @return the position vector.
     */
    private static Vector getRandomPosition(Random random, boolean spawnsLeft, Vector dimensions) {
        int min = Math.round(dimensions.x);
        int max = 515 - min;
        int ypos = random.nextInt(Math.abs(max - min)) + min;
        int xpos = (spawnsLeft ? 0 - min * 5 : 615 + min * 5);
        return new Vector(xpos, ypos);
    }
    /**
     * create a random speedUp vector.
     * @param random the randomizer.
     * @param spawnsLeft whether the instance is starting left or right.
     * @return a speedUp vector.
     */
    private static Vector getRandomVelocity(Random random, boolean spawnsLeft) {
        int speed = random.nextInt(4) + 1;
        return new Vector((spawnsLeft ? speed : -speed), 0);
    }
    /**
     * return the spawn chance.
     */
    public int getChance(){
        return chance;
    }
    /**
     * start the effect on the player.
     */
    @Override
    public void Effect(Player player){
        player.addShield(5000,2000);
    }
}
