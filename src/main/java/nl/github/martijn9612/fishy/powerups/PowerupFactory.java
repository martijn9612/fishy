package nl.github.martijn9612.fishy.powerups;

import java.util.ArrayList;
import java.util.Random;

/**
 * Implements the PowerupFactory.
 * Software Engineering Methods Project - Group 11.
 */
public class PowerupFactory {
    private ArrayList<Powerup> powers = new ArrayList<Powerup>();
    private Random rand = new Random();
    private Powerup powerup;
    private boolean loadResources;

    /**
     * Creates a new PowerupFactory.
     * @param loadResources - true if OpenGL context should be loaded, false if not.
     */
    public PowerupFactory(Boolean loadResources) {
        this.loadResources = loadResources;
    }

    /**
     * Spawns a Powerup.
     * @return Either null if no Powerup should be returned or a new Powerup.
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
     * Sets up the PowerupFactory so that every time the powerups are different.
     */
    public void setupFactory() {
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
     * Clears the PowerupFactory so it doesn't get too big.
     */
    public void clearFactory() {
        powers.clear();
    }

    /**
     * Returns all the powerups in the list.
     * @return powers containing all the powerups.
     */
    public ArrayList<Powerup> getPowerups() {
        return powers;
    }

    /**
     * Sets the Random to a new value.
     * Method for testing purposes.
     * @param random - new random value.
     */
    public void setRandom(Random random) {
        rand = random;
    }

    /**
     * Gets the current Powerup.
     * Method for testing purposes.
     * @return the current Powerup.
     */
    public Powerup getPowerup() {
        return powerup;
    }
}
