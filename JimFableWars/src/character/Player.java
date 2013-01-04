/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

/**
 *
 * @author Lena
 */
public class Player extends Character {
    
  public static enum State {INVINCIBLE, AGIKITY_POWER_UP, ATTACK_POWER_UP, DEFAULT};
    
  private State state = State.DEFAULT;
  private int distanceAttack;
  
    public Player(String name, int distanceAttack, int directAttack){
        super(name, directAttack);
        this.distanceAttack = distanceAttack;
    }
    
    public int distanceAttack(){
    //       this.channel.setAnim("DistanceAttack", 0.1f);
        return this.distanceAttack;
    }
    
    public void changeState(State state){
        this.state = state;
    }
    
    public State getState(){
        return state;
    }
    
    
}
