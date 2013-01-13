/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import com.jme3.app.state.AppState;
import com.jme3.math.Vector3f;

/**
 *
 * @author Lena
 */
public class Opponent extends Character{
    
    private final float ATTACK_DELAY = 1.f;
    private float currentAttackDelay = 0.f;
    
    public Opponent(String name, int directAttack){
        super(name, directAttack);
    }    

    public Projectile handleRangedAttack(Vector3f direction, Vector3f position) {
        
        if (currentAttackDelay <= 0.f)
        {
            currentAttackDelay = ATTACK_DELAY;
            return new Projectile(direction, position);
        }
        else
        {
            return null;
        }
    }
    
    public void advanceAttack(float tpf){
        if (currentAttackDelay > 0.f){
            currentAttackDelay -= tpf;
        }
    }
}
