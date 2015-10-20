package nl.github.martijn9612.fishy.powerups;

import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.models.Player;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Implements the PowerupController which utilizes a
 * PowerupFactory for creating the powerups.
 * Software Engineering Methods Project - Group 11.
 */
public class PowerupController {
    private PowerupFactory powerfac = new PowerupFactory(true);
    private Powerup power = null;

    /**
     * Spawns a Powerup when none are present.
     */
    public void SpawnPowerup() {
        if (power == null) {
            power = powerfac.spawnPowerup();
        }
    }

    /**
     * Updates the Powerup.
     * @param gc - the container holding the game.
     * @param deltaTime - time elapsed since method was called in milliseconds.
     */
    public void updatePowerup(GameContainer gc, int deltaTime) {
        if (!(power == null)) {
            power.objectLogic(gc, deltaTime);
            if (power.isOffScreen()) {
                Remove();

            }
        }
    }

    /**
     * Renders the Powerups.
     * @param graph - the graphics content used to render the powerups.
     */
    public void renderOpponents(Graphics graph) {
        if (!(power == null)) {
            power.renderObject(graph);
        }
    }

    /**
     * Check if the powerup collides with the Player.
     * @param player - the current Player in the game.
     * @param sbg - the game content.
     */
    public void collide(Player player, StateBasedGame sbg) {
        if (!(power == null)) {
            if (power.intersects(player)) {
                String log = "Player collides with powerup "
                        + power.getClass().getSimpleName();
                player.playBiteSound();
                power.Effect(player);
                Remove();
                Main.actionLogger.logLine(log, getClass().getSimpleName());
            }
        }
    }

    /**
     * Removes the powerup.
     */
    public void Remove() {
        if (!(power == null)) {
            power.destroy();
            power = null;
        }
    }

    /**
     * Gets power.
     * Method for testing purposes.
     * @return power
     */
    public Powerup getPower() {
        return power;
    }

    /**
     * Sets the power.
     * Method for testing purposes.
     * @param powerup - the new powerup.
     */
    public void setPower(Powerup powerup) {
        power = powerup;
    }

    /**
     * Sets the PowerupFactory.
     * Method for testing purposes.
     * @param factory - the new PowerupFactory.
     */
    public void setPowerupFactory(PowerupFactory factory) {
        powerfac = factory;
    }
}
