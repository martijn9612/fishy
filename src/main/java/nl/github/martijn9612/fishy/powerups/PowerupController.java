package nl.github.martijn9612.fishy.powerups;

import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.models.Player;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

/**
 * the controller for powerups.
 * it utilize a powerupfactory for creating the powerups.
 *
 */
public class PowerupController {
    private PowerupFactory powerfac = new PowerupFactory(true);
    private Powerup power = null;
    
    /**
     * spawn a powerup if none are present.
     */
    public void SpawnPowerup(){
        if(power == null){
        power = powerfac.spawnPowerup();
        }
    }
    /**
     * update the powerup object.
     * @param gc the gamecontainer
     * @param deltaTime
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
     * render the power up.
     * @param graph the graphics of slick2d
     */
    public void renderOpponents(Graphics graph) {
        if (!(power == null)) {
            power.renderObject(graph);
        }
    }
    /**
     * method for handling collisions between powerups and players.
     * @param player the player fish.
     * @param sbg the game.
     */
    public void collide(Player player, StateBasedGame sbg) {
        if (!(power == null)){
            if (power.intersects(player)) {
                String log = "Player collides with powerup " + power.getClass().getSimpleName();
                player.playBiteSound();
                power.Effect(player);
                Remove();
                Main.actionLogger.logLine(log, getClass().getSimpleName());
            }
        }
    }
    /**
     * remove the power up.
     */
    public void Remove(){
        if (!(power == null)){
        power.destroy();
        power = null;
        }
    }
    
    
    /**
     * Method to get power, makes testing easier.
     * @return power;
     */
    public Powerup getPower() {
        return power;
    }
    
    /**
     * Method to make testing easier.
     * sets power.
     * @param powerup - powerup to which power has to be set.
     */
    public void setPower(Powerup powerup) {
        power = powerup;
    }
    
    /**
     * Method to make testing easier.
     * sets the powerupfactory
     * @param factory - factory to be set.
     */
    public void setPowerupFactory(PowerupFactory factory) {
        powerfac = factory;
    }
}
