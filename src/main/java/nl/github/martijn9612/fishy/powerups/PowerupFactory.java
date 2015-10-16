package nl.github.martijn9612.fishy.powerups;

import java.util.ArrayList;
import java.util.Random;
/**
 * used to create the powerups
 *
 */
public class PowerupFactory {
    private ArrayList<Powerup> powers = new ArrayList<Powerup>();
    private Random rand = new Random();
    private Powerup powerup;
    private boolean loadResources;
    /**
     * constructor for the factory.
     * @param loadResources a boolean wether to load resources or not.
     */
    public PowerupFactory(Boolean loadResources) {
        this.loadResources = loadResources;
    }
/**
 * spawn a powerup or nothing at all.
 * @return either null if no powerup should be returned or an powerup.
 */
    public Powerup spawnPowerup() {
        setupFactory();
        int pow = rand.nextInt(powers.size());
        Powerup power = powers.get(pow);
        int chance = power.getChance();
        int random = rand.nextInt(1001);
        if (random < chance) {
            powerup = power;
        } else {
            powerup = null;
        }
      
        clearFactory();
        return powerup;
    }
    /**
     * set up the factory so that everytime the powerups are random.
     */
    public void setupFactory(){
        Powerup extralife = ExtraLife.createPowerup(rand, loadResources);
        Powerup speedup = Speedup.createPowerup(rand, loadResources);
        Powerup poison = Poison.createPowerup(rand, loadResources);
        Powerup shield = Shield.createPowerup(rand, loadResources);
        powers.add(extralife);
        powers.add(speedup);
        powers.add(poison);
        powers.add(shield);
    }
    /**
     * clear the factory so it doesn't get to big.
     */
    public void clearFactory(){
        powers.clear();
    }
    /**
     * return the arraylist containing the powerups.
     * @return
     */
    public ArrayList<Powerup> getPowerups(){
        return powers;
    }
    
    
    /**
     * Method to make testing easier.
     * Sets the current random.
     * @param random - random value to be set to.
     */
    public void setRandom(Random random) {
        rand = random;
    }
    
    /**
     * Method to make testing easier.
     * Returns the current powerup.
     * @return the current powerup.
     */
    public Powerup getPowerup() {
        return powerup;
    }
}

