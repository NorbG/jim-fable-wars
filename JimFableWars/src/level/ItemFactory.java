/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package level;

import java.awt.Point;

/**
 *
 * @author Lena
 */
public class ItemFactory {
    
    public static Item createPowerUp(String type, Point location){
        Item item = new Item(type, location);
        return item;
    }
    
    public static Item createCloud(Point location){
        Item cloud = new Item("Cloud", location);
        return cloud;
    }
    
    public static Item createRock(Point location){
        Item rock = new Item("Rock", location);
        return rock;
    }
    
    public static Item createDeathStar(Point location){
        Item deathstar = new Item("DeathStar", location);
        return deathstar;
    }
    
}
