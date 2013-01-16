/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package level;

import Helper.Constants;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.control.GhostControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.control.VehicleControl;
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
    
    private static int movableCloudIndex = 0;
    private static Node modelCloudType1 = null;
    private static Node modelCloudType2 = null;
    private static Node modelCloudType3 = null;
    
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
    
    public static Cloud createCloud(int type, Vector3f location,  BulletAppState appState, AssetManager assetManager){
        Cloud cloud = new Cloud("Cloud", location);
        RigidBodyControl cloudControl = null;
        modelCloudType1 = (Node) assetManager.loadModel("Models/Level/Heaven/cloud_small_1v2_low_poly.j3o");
       
        switch(type)
        {
           case Constants.CLOUD_SMALL_1v2:
                   cloud.model = modelCloudType1;
                cloudControl = new RigidBodyControl(new BoxCollisionShape(new Vector3f(1.7f, 1.3f, 1.5f)));
                break;
            case Constants.CLOUD_MEDIUM_1v1:
               //     modelCloudType2 = (Node) assetManager.loadModel("Models/Level/Heaven/cloud_medium_1v1_low_poly.j3o");
                modelCloudType1.scale(1.82f,1.2f, 1);
               cloud.model = modelCloudType1;
                cloudControl = new RigidBodyControl(new BoxCollisionShape(new Vector3f(3.1f, 1.3f, 1.5f)));
                
                break;
            case Constants.CLOUD_Long_1v1:

                 //modelCloudType3 = (Node) assetManager.loadModel("Models/Level/Heaven/cloud_long_1v1_low_Poly.j3o");
                modelCloudType1.scale(4.34f, 1.2f, 1);
                cloud.model = modelCloudType1;
                cloudControl = new RigidBodyControl(new BoxCollisionShape(new Vector3f(7.2f, 1.3f, 1.5f)));
                break;
        }
        
        cloudControl.setMass(1f); 
        cloudControl.setRestitution(0f);
        cloudControl.setFriction(0f);
        cloudControl.setKinematic(true);
        cloud.model.setLocalTranslation(location.x, location.y, 0);
        cloud.model.addControl(cloudControl);
        cloud.model.setName("Cloud");
        appState.getPhysicsSpace().add(cloud.model);
        
        return cloud;
    }
    
    public static Cloud createMovableCloud(int type, Vector3f location,  BulletAppState appState, AssetManager assetManager, int direction, boolean repeat, Vector3f endPosition)
    {
        Cloud cloud = createCloud(type, location, appState, assetManager);
        cloud.direction = direction;
        cloud.repeat = repeat;
        cloud.endPosition = endPosition;
        
        
        cloud.model.setName("movable" + movableCloudIndex);
        movableCloudIndex++;
        
        return cloud;
    }
    
    public static Item createRock(AssetManager assetManager,  BulletAppState appState, Vector3f location){
        Item rock = new Item("Rock");
        return rock;
    }
    
    public static Item createDeathStar(Vector3f location, BulletAppState appState, AssetManager assetManager){
        Item deathstar = new Item("DeathStar");
        deathstar.model = (Node) assetManager.loadModel("Models/Level/Heaven/starV1.j3o");
        deathstar.model.setLocalScale(0.3f);
        deathstar.model.setLocalTranslation(location.x, location.y, 0);
        deathstar.model.setName("DeathStar");
        
        RigidBodyControl control = new RigidBodyControl(new BoxCollisionShape(new Vector3f(0.8f, 0.8f, 0.8f)), 0);
        deathstar.model.addControl(control);
        appState.getPhysicsSpace().add(deathstar.model);
                
        return deathstar;
    }
    
    public static void reset()
    {
        movableCloudIndex = 0;
    }
}
