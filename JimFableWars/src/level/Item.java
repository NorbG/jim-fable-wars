/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package level;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import java.awt.Point;

/**
 *
 * @author Lena
 */
public class Item {
    
    private String name;
    private AnimControl animation;
    private AnimChannel channel;
    private Point location;
    
    public Item(String name, Point location){
        this.name = name;
        this.location = location;
    }
    
    public void activate(){}
    
    public String getName(){
        return name;
    }
    
    
}
