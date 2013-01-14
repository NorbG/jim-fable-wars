/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.audio.AudioNode;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.scene.Node;

/**
 *
 * @author Lena
 */
public class Character {

    public AnimControl animation;
    public AnimChannel channel;
    protected int health;
    private String name;
    private int directAttackPoints;
    public CharacterControl character = null;
    public Node model;
    public String jumpAnimation = "";
    public String walkAnimation = "";
    public String flyAnimation = "";
    public String directAttackAnimation = "";
    public String standAnimation = "";
    public AudioNode attack = null;
    public AudioNode on_death = null;
    public AudioNode on_hit = null;
    public AudioNode walk = null;

    public Character(String name, int directAttackPoints) {
        this.name = name;
        this.directAttackPoints = directAttackPoints;
    }
    
    public void setHealth(int health){
        this.health = health;
    }

    public int directAttack(boolean hits) {
//        this.channel.setAnim("DirectAttack", 0.1f);
        //      this.channel.setLoopMode(LoopMode.DontLoop);
        if (hits) {
            return this.directAttackPoints;
        } else {
            return 0;
        }
    }

    public boolean isDead() {
        return health <= 0;
    }

    public void adjustHealth(int adjustment) {
        health += adjustment;
                    if(isDead() && this.on_death != null){
                this.on_death.setLooping(false);
                this.on_death.play();
            }
            else if(adjustment < 0 && this.on_hit != null){
              this.on_hit.setLooping(false);
              this.on_hit.play();
        }
    }

    public int getHealth() {
        return this.health;
    }

    public String getName() {
        return name;
    }

    
}
