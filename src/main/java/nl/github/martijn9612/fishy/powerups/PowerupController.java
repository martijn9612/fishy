package nl.github.martijn9612.fishy.powerups;

import nl.github.martijn9612.fishy.Main;
import nl.github.martijn9612.fishy.models.Player;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class PowerupController {
    private PowerupFactory powerfac = new PowerupFactory(true);
    private Powerup power = null;
    
    public void SpawnPowerup(){
        if(power == null){
        power = powerfac.spawnPowerup();
        }
    }
    
    public void updatePowerup(GameContainer gc, int deltaTime) {
        if (!(power == null)) {
            power.objectLogic(gc, deltaTime);
            if (power.isOffScreen()) {
                Remove();
                
            }
        }
    }
    
    public void renderOpponents(Graphics graph) {
        if (!(power == null)) {
            power.renderObject(graph);
        }
    }
    
    public void collide(Player player, StateBasedGame sbg) {
        if (!(power == null)){
            if (power.intersects(player)) {
                String log = "Player collides with powerup " + power.getClass().getSimpleName();
                power.Effect(player);
                Remove();
                Main.actionLogger.logLine(log, getClass().getSimpleName());
            }
        }
    }
    public void Remove(){
        if (!(power == null)){
        power.destroy();
        power = null;
        }
    }
}
