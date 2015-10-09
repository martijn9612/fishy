package nl.github.martijn9612.fishy.powerups;

import org.newdawn.slick.GameContainer;

import nl.github.martijn9612.fishy.models.NonPlayer;
import nl.github.martijn9612.fishy.models.Player;
import nl.github.martijn9612.fishy.models.Vector;

public abstract class Powerup extends NonPlayer {
    private int chance;
    /**
     * the contructor of powerups.
     * @param dimensions the size.
     * @param position the starting position.
     * @param velocity the speed at which it travels trough the screen.
     * @param acceleration its acceleration. usually 0. 
     * @param hasOpenGL
     * @param chance the chance it gets returned in the factory.
     */
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
    
    public void Effect(Player player) {
        
    }

}
