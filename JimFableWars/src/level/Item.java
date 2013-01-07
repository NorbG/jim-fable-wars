/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package level;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.scene.Node;


/**
 *
 * @author Lena
 */
public class Item {
    
    private String name;
    public AnimControl animation;
    public AnimChannel channel;
    public Node model;
    
    public Item(String name){
        this.name = name;
    }
    
    public void activate(){}
    
    public String getName(){
        return name;
    }
    
    
}
