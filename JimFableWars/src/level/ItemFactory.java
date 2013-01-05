/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package level;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 *
 * @author Lena
 */
public class ItemFactory {
    
    public static Item createPowerUp(String type, AssetManager assetManager,  BulletAppState appState, Vector3f location){

        Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.Green);
        material.setColor("GlowColor", ColorRGBA.Green);
    
        Box powerUp = new Box(0.5f, 0.5f, 0.5f);
        powerUp.scaleTextureCoordinates(new Vector2f(1f, .5f));
        Geometry geometry = new Geometry("brick", powerUp);
        geometry.setMaterial(material);
        geometry.setLocalTranslation(location);
        geometry.addControl(new RigidBodyControl(1.5f));
        geometry.setShadowMode(ShadowMode.CastAndReceive);
        appState.getPhysicsSpace().add(geometry);
        Item item = new Item(type);
        item.geometry = geometry;
        return item;
    }
    
    public static Item createCloud(AssetManager assetManager,  BulletAppState appState, Vector3f location){
        Item cloud = new Item("Cloud");
        return cloud;
    }
    
    public static Item createRock(AssetManager assetManager,  BulletAppState appState, Vector3f location){
        Item rock = new Item("Rock");
        return rock;
    }
    
    public static Item createDeathStar(AssetManager assetManager,  BulletAppState appState, Vector3f location){
        Item deathstar = new Item("DeathStar");
        return deathstar;
    }
    
}
