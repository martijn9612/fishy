package nl.github.martijn9612.fishy.powerups;

import org.newdawn.slick.GameContainer;

import nl.github.martijn9612.fishy.models.Moveable;
import nl.github.martijn9612.fishy.models.NonPlayer;
import nl.github.martijn9612.fishy.models.Player;

/**
 * Implements the PowerUp
 * Software Engineering Methods Project - Group 11.
 */
public abstract class Powerup extends NonPlayer {
    private int chance;
    
    /**
     * Creates a new PowerUp.
     * @param data - the moveable data of the new PowerUp.
     * @param hasOpenGL - true if OpenGL content should be loaded, false if not.
     * @param chance - the chance its gets returned in powerupfactary, in promille.
     */
    public Powerup(Moveable data, boolean hasOpenGL, int chance) {
        super(data, hasOpenGL);
    }

    /**
     * Executes the object logic of the Powerup.
     * @param gc - the container the game is in.
     * @param deltaTime - time elapsed since method was called in milliseconds.
     */
    @Override
    public void objectLogic(GameContainer gc, int deltaTime) {
    	getData().getPosition().add(getData().getVelocity());
        updateBoundingbox();
    }

    /**
     * Gets the chance that the powerup is chosen in the PowerupFactory.
     * @return the chance of the powerup.
     */
    public int getChance() {
        return this.chance;
    }
    /**
     * Starts the effect of the powerup 
     * upon collision with a player.
     * @param player - the current Player in the game.
     */
    public void Effect(Player player) {  
    }

}
