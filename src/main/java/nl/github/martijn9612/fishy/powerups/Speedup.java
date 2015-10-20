package nl.github.martijn9612.fishy.powerups;

import java.util.Random;

import nl.github.martijn9612.fishy.models.Entity;
import nl.github.martijn9612.fishy.models.Moveable;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.models.Vector;

public class Speedup extends Powerup{
    private static final String SPRITE_PATH = "resources/speedUp-fish.png" ;
    private int chance;
    /**
     * the contstructor of speedup.
     * @param dimensions the size of the power up.
     * @param position the starting position.
     * @param velocity the speedUp at which it travels.
     * @param acceleration its acceleration.
     * @param loadResources whether or not to load resources.
     * @param chance the chance it gets returned in the factory.
     */
    public Speedup(Moveable data, boolean loadResources, int chance) {
        super(data, loadResources, chance);
        loadResources(SPRITE_PATH);
        this.chance = chance;
        if(loadResources && data.velocity.x > 0) {
            setImageOrientation(Entity.IMAGE_ORIENTATE_RIGHT);
        }
    }
    /**
     * Creates an instance of LinearOpponent at a random screen side location.
     * 
     * @param player an instance of the Player class.
     * @param random an instance to generate random numbers.
     * @param loadResources whether the sprite resources should be loaded.
     */
    public static Speedup createPowerup(Random random, boolean loadResources) {
    	Moveable data = new Moveable();
    	boolean spawnsLeft = random.nextBoolean();
    	data.dimensions = new Vector(16,16);
    	data.velocity = getRandomVelocity(random, spawnsLeft);
    	data.position = getRandomPosition(random, spawnsLeft, data.dimensions);
        return new Speedup(data, loadResources, 100);
    }
    /**
     * creates a random position vector.
     * @param random the randomizer.
     * @param spawnsLeft whether it spawns left or right.
     * @param dimensions the size.
     * @return a position vector.
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
     * @param spawnsLeft whether it spawns left or right.
     * @return a speedUp vector.
     */
    private static Vector getRandomVelocity(Random random, boolean spawnsLeft) {
        int speed = random.nextInt(4) + 1;
        return new Vector((spawnsLeft ? speed : -speed), 0);
    }
    /**
     * returns the chance it gets returned as powerup in the factory.
     */
    public int getChance(){
        return chance;
    }
    /**
     * activate the speedup effect on the player on collision.
     */
    @Override
    public void Effect(Player player){
        player.speedUp(5000);
    }
}
