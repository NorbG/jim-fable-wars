/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import com.jme3.bullet.collision.PhysicsRayTestResult;
import com.jme3.bullet.control.GhostControl;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Line;
import java.util.List;
import mygame.Game;

/**
 *
 * @author Lena
 */
public class Player extends Character {
    private float JUMP_HEIGHT = 14;

    public static enum State {

        INVINCIBLE, AGILITY_POWER_UP, ATTACK_POWER_UP, DEFAULT
    };
    
    public static enum Direction {

        LEFT, RIGHT, UP, NONE
    };
    
    private State state = State.DEFAULT;
    private int distanceAttack;
    public String distanceAttackAnimation;
    
    private final float HORIZONTAL_SPEED = 4f;     // the movement speed to the left and right
    //public boolean moveLeft = false;           // currently moving left?
    //public boolean moveRight = false;          // currently moving right?
    public Direction currentDirection = Direction.NONE;
    public Direction currentDirectionUp = Direction.NONE;

    public Player(String name, int distanceAttack, int directAttack) {
        super(name, directAttack);
        this.distanceAttack = distanceAttack;
    }

    public int distanceAttack() {
        //       this.channel.setAnim("DistanceAttack", 0.1f);
        return this.distanceAttack;
    }

    public void changeState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void handleMovement(float tpf, Game app) {
        // move player left or right if necessary
         Vector3f tmp = character.getPhysicsLocation();
        if (currentDirection == Direction.LEFT) {
           tmp.x -=HORIZONTAL_SPEED * tpf;
           currentDirection = Direction.NONE;
        }

        if (currentDirection == Direction.RIGHT ) {
             tmp.x +=HORIZONTAL_SPEED * tpf;
             currentDirection = Direction.NONE;
        }
        
        if (currentDirectionUp == Direction.UP) {
             tmp.y += JUMP_HEIGHT * tpf;
             currentDirectionUp = Direction.NONE;
        }
        
     tmp.z = 0f;
     character.setPhysicsLocation(tmp);
    }

    public Projectile handleRangedAttack() {
        Vector3f direction = null;
        
        if (currentDirection == Direction.LEFT)
            direction = new Vector3f(-1,0,0);
        else if (currentDirection == Direction.RIGHT)
            direction = new Vector3f(1,0,0);
        
        return new Projectile(direction, getPlayerLocation());
    }

    public void handleMeleeAttack() {
        System.out.println("MeleeAttack");
    }

    public Vector3f getPlayerLocation() {
        return model.getLocalTranslation();
    }
}
