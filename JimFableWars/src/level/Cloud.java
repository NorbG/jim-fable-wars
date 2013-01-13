/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package level;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;

/**
 *
 * @author David
 */
public class Cloud extends Item {

    public final static int DIRECTION_UP = 0;
    public final static int DIRECTION_DOWN = 1;
    public final static int DIRECTION_LEFT = 2;
    public final static int DIRECTION_RIGHT = 3;
    public int direction;
    public boolean repeat;
    private boolean currentDirection = true;
    public Vector3f startPosition;
    public Vector3f endPosition;

    public Cloud(String name, Vector3f startPosition) {
        super(name);
        this.startPosition = startPosition;
    }

    public void move(float tpf) {
        switch (direction) {
            case DIRECTION_UP:
                up(tpf);
                break;
            case DIRECTION_DOWN:
                fall(tpf);
                break;
            case DIRECTION_RIGHT:
                right(tpf);
                break;
            case DIRECTION_LEFT:
                left(tpf);
                break;
        }
    }

    public void fall(float tpf) {

        Vector3f cloudLocatio = this.model.getControl(RigidBodyControl.class).getPhysicsLocation();
        if (cloudLocatio.y > endPosition.y && currentDirection) {
            this.model.getControl(RigidBodyControl.class).setPhysicsLocation(new Vector3f(cloudLocatio.x, cloudLocatio.y - 3f * tpf, 0));
        } else if (repeat && cloudLocatio.y < startPosition.y) {
            this.model.getControl(RigidBodyControl.class).setPhysicsLocation(new Vector3f(cloudLocatio.x, cloudLocatio.y + 3f * tpf, 0));
            currentDirection = false;
        } else {
            currentDirection = true;
        }
    }

    public void up(float tpf) {

        Vector3f cloudLocatio = this.model.getControl(RigidBodyControl.class).getPhysicsLocation();
        if (cloudLocatio.y < endPosition.y && currentDirection) {
            this.model.getControl(RigidBodyControl.class).setPhysicsLocation(new Vector3f(cloudLocatio.x, cloudLocatio.y + 3f * tpf, 0));
        } else if (repeat && cloudLocatio.y > startPosition.y) {
            this.model.getControl(RigidBodyControl.class).setPhysicsLocation(new Vector3f(cloudLocatio.x, cloudLocatio.y - 3f * tpf, 0));
            currentDirection = false;
        } else {
            currentDirection = true;
        }
    }
    
    public void right(float tpf) {

        Vector3f cloudLocatio = this.model.getControl(RigidBodyControl.class).getPhysicsLocation();
        if (cloudLocatio.x < endPosition.x && currentDirection) {
            this.model.getControl(RigidBodyControl.class).setPhysicsLocation(new Vector3f(cloudLocatio.x + 4f * tpf, cloudLocatio.y, 0));
        } else if (repeat && cloudLocatio.x > startPosition.x) {
            this.model.getControl(RigidBodyControl.class).setPhysicsLocation(new Vector3f(cloudLocatio.x - 4f * tpf, cloudLocatio.y, 0));
            currentDirection = false;
        } else {
            currentDirection = true;
        }
    }
    
    public void left(float tpf) {

        Vector3f cloudLocatio = this.model.getControl(RigidBodyControl.class).getPhysicsLocation();
        if (cloudLocatio.x > endPosition.x && currentDirection) {
            this.model.getControl(RigidBodyControl.class).setPhysicsLocation(new Vector3f(cloudLocatio.x - 3f * tpf, cloudLocatio.y, 0));
        } else if (repeat && cloudLocatio.x < startPosition.x) {
            this.model.getControl(RigidBodyControl.class).setPhysicsLocation(new Vector3f(cloudLocatio.x + 3f * tpf, cloudLocatio.y, 0));
            currentDirection = false;
        } else {
            currentDirection = true;
        }
    }
}
