/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.LoopMode;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import java.awt.Point;

/**
 *
 * @author Lena
 */
public class Character {
    
    public AnimControl animation;
    public AnimChannel channel;
    private int health;
    private String name;
    private int directAttackPoints;
    public CharacterControl character;
    public Node model;
    
    public Character(String name, int directAttackPoints){
        this.name = name;
        this.directAttackPoints = directAttackPoints;
    }
    
    
    public int directAttack(boolean hits){
//        this.channel.setAnim("DirectAttack", 0.1f);
  //      this.channel.setLoopMode(LoopMode.DontLoop);
        if(hits)
             return this.directAttackPoints;
        else
            return 0;
    }
    
    public boolean isDead(){
        return health > 0;
    }
    
    public void adjustHealth(int adjustement){
        health += adjustement;
    }
    
    public String getName(){
        return name;
    }

public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
        if (channel == this.channel) channel.setAnim("stand");
}
 
public void onAnimChange(AnimControl control, AnimChannel channel, String animName) { }

 
 

    
    
}
