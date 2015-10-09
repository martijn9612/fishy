package nl.github.martijn9612.fishy.powerups;

import java.util.ArrayList;
import java.util.Random;

public class PowerupFactory {
    private ArrayList<Powerup> powers = new ArrayList<Powerup>();
    private Random rand = new Random();
    private Powerup powerup;
    private boolean loadResources;
    
    public PowerupFactory(Boolean loadResources) {
        this.loadResources = loadResources;
    }

    public Powerup spawnPowerup() {
        setupFactory();
        int pow = rand.nextInt(2);
        Powerup power = powers.get(pow);
        int chance = power.getChance();
        int random = rand.nextInt(101);
        if (random < chance) {
            powerup = power;
        } else {
            powerup = null;
        }
        //System.out.println("chance: "+power.getChance() +"rand: " + random);
        clearFactory();
        return powerup;
    }
    
    public void setupFactory(){
        Powerup extralife = ExtraLife.createPowerup(rand, loadResources);
        Powerup speedup = Speedup.createPowerup(rand, loadResources);
        Powerup poison = Poison.createPowerup(rand, loadResources);
        powers.add(extralife);
        powers.add(speedup);
        powers.add(poison);
    }
    public void clearFactory(){
        powers.clear();
    }
}
