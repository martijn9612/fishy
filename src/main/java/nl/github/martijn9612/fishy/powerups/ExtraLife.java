package nl.github.martijn9612.fishy.powerups;

import java.util.Random;

import nl.github.martijn9612.fishy.models.Entity;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.models.Vector;
import nl.github.martijn9612.fishy.opponents.LinearOpponent;

public class ExtraLife extends Powerup{
    private static final String SPRITE_PATH = "resources/Mushroom.png";
    private int chance;
    private String name;

    public ExtraLife(Vector dimensions, Vector position, Vector velocity,
            Vector acceleration, boolean loadResources, int chance) {
        super(dimensions, position, velocity, acceleration, loadResources, chance);
        loadResources(SPRITE_PATH);
        name = "Extra life";
        this.chance = chance;
        if(loadResources && velocity.x > 0) {
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
    public static ExtraLife createPowerup(Random random, boolean loadResources) {
        boolean spawnsLeft = random.nextBoolean();
        Vector acceleration = new Vector(0,0);
        Vector dimensions = new Vector(16,16);
        Vector velocity = getRandomVelocity(random, spawnsLeft);
        Vector position = getRandomPosition(random, spawnsLeft, dimensions);
        return new ExtraLife(dimensions, position, velocity, acceleration, loadResources, 10);
    }
    
    private static Vector getRandomPosition(Random random, boolean spawnsLeft, Vector dimensions) {
        int min = Math.round(dimensions.x);
        int max = 515 - min;
        int ypos = random.nextInt(Math.abs(max - min)) + min;
        int xpos = (spawnsLeft ? 0 - min * 5 : 615 + min * 5);
        return new Vector(xpos, ypos);
    }
    
    private static Vector getRandomVelocity(Random random, boolean spawnsLeft) {
        int speed = random.nextInt(4) + 1;
        return new Vector((spawnsLeft ? speed : -speed), 0);
    }
    
    public int getChance(){
        return chance;
    }
    
    public void Effect(Player player){
        player.Extralife();
    }
}