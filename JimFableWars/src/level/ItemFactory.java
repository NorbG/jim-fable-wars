/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package level;

import Helper.Constants;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
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
        item.model = new Node("Power Up");
        item.model.attachChild(geometry);
        return item;
    }
    
    public static Item createCloud(int type, Vector3f location,  BulletAppState appState, AssetManager assetManager){
        Item cloud = new Item("Cloud");
        RigidBodyControl cloudControl = null;
        
        switch(type)
        {
            case Constants.CLOUD_SMALL_1v2:
                cloud.model = (Node) assetManager.loadModel("Models/Level/Heaven/cloud_small_1v2.j3o");
                cloudControl = new RigidBodyControl(new BoxCollisionShape(new Vector3f(2, 1, 1)), 0);
                break;
            case Constants.CLOUD_MEDIUM_1v1:
                cloud.model = (Node) assetManager.loadModel("Models/Level/Heaven/cloud_medium_1v1.j3o");
                cloudControl = new RigidBodyControl(new BoxCollisionShape(new Vector3f(2, 1, 1)), 0);
                break;
            case Constants.CLOUD_Long_1v1:
                cloud.model = (Node) assetManager.loadModel("Models/Level/Heaven/cloud_long_1v1.j3o");
                cloudControl = new RigidBodyControl(new BoxCollisionShape(new Vector3f(2, 1, 1)), 0);
                break;
        }
        

        cloud.model.setLocalTranslation(location.x, location.y, 0);
        cloud.model.addControl(cloudControl);
        appState.getPhysicsSpace().add(cloud.model);
        
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
