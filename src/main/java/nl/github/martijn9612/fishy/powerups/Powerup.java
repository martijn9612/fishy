package nl.github.martijn9612.fishy.powerups;

import org.newdawn.slick.GameContainer;

import nl.github.martijn9612.fishy.models.NonPlayer;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.models.Vector;

public abstract class Powerup extends NonPlayer {
    private int chance;
    private String name;

    public Powerup(Vector dimensions, Vector position, Vector velocity,
            Vector acceleration, boolean hasOpenGL, int chance) {
        super(dimensions, position, velocity, acceleration, hasOpenGL);
    }

    @Override
    public void objectLogic(GameContainer gc, int deltaTime) {
        position.add(velocity);
        updateBoundingbox();
    }

    public int getChance() {
        return this.chance;
    }
    
    public String getName(){
        return name;
    }
    
    public void Effect(Player player) {
        
    }

}
