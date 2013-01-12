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
public class Cloud extends Item{

    
     public Cloud(String name) {
        super(name);
    }
     
    public void fall(Vector3f endPoint, float tpf)
    {
        
        Vector3f cloudLocatio = this.model.getControl(RigidBodyControl.class).getPhysicsLocation();
        this.model.getControl(RigidBodyControl.class).setPhysicsLocation(new Vector3f(cloudLocatio.x, cloudLocatio.y - 3f * tpf, 0));
        cloudLocatio = this.model.getControl(RigidBodyControl.class).getPhysicsLocation();
    }
    
}
