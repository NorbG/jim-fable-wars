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

    public static enum State {

        INVINCIBLE, AGILITY_POWER_UP, ATTACK_POWER_UP, DEFAULT
    };
    
    public static enum Direction {

        LEFT, RIGHT
    };
    
    private State state = State.DEFAULT;
    private int distanceAttack;
    public String distanceAttackAnimation;
    
    private final int HORIZONTAL_SPEED = 7;     // the movement speed to the left and right
    public final float JUMP_DELAY = 1.f;       // the interval between jumps (seconds)
    private final float JUMP_DURATION = 0.5f;   // how long is the player jumping (seconds)?
    private final float JUMP_SPEED = 20;        // the upwards speed when jumping
    private final float GRAVITY = -10.f;        // the downwards speed when falling
    //public boolean moveLeft = false;           // currently moving left?
    //public boolean moveRight = false;          // currently moving right?
    public float currentJumpDelay = 0;         // seconds since last jump
    public boolean jumping = false;            // is currently jumping?
    public boolean canJump = false;            // can currently jump (cannot when falling e.g.)
    public Direction currentDirection = Direction.RIGHT;
    public boolean isMoving = false;
    public GhostControl control;

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
        if (currentDirection == Direction.LEFT && isMoving) {
            model.move(-HORIZONTAL_SPEED * tpf, 0, 0);
        }

        if (currentDirection == Direction.RIGHT && isMoving) {
            model.move(HORIZONTAL_SPEED * tpf, 0, 0);
        }
        
        isMoving = false;

        // count seconds since last jump
        if (currentJumpDelay < JUMP_DELAY) {
            currentJumpDelay += tpf;
        }

        // if jumping duration is over, disable jump
        if (currentJumpDelay > JUMP_DURATION) {
            jumping = false;
        }

        // if currently jumping, move the player upwards
        if (jumping) {
            model.move(0, tpf * JUMP_SPEED, 0);
        }

        // test if player is above ground
        Vector3f start = getPlayerLocation();
        Vector3f end = getPlayerLocation().add(new Vector3f(0, -1.5f, 0));
        List<PhysicsRayTestResult> physRayResults = app.getBulletAppState().getPhysicsSpace().rayTest(start, end);

        // draw helper line
        Geometry g = new Geometry("line", new Line(start, end));
        Material m = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        g.setMaterial(m);
        app.getRootNode().detachChildNamed("line");
        app.getRootNode().attachChild(g);

        // if test failed -> player is falling because he is not above ground
        if (physRayResults.isEmpty()) {
            model.move(0, tpf * GRAVITY, 0);
            canJump = false;    // player can't jump while falling
        } else {
            canJump = true;
        }
        
        //test if player above a upwarts movable cloud 
        end = getPlayerLocation().add(new Vector3f(0, -1.3f, 0));
        physRayResults = app.getBulletAppState().getPhysicsSpace().rayTest(start, end);
        if(!physRayResults.isEmpty())
        {
            model.move(0, tpf * 3f, 0);
        }
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
