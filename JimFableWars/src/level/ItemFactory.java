/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package level;

import Helper.Constants;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.MatParam;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.math.Vector4f;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.shader.VarType;
import com.jme3.texture.Texture;
import java.util.Collection;

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
                cloud.model = (Node) assetManager.loadModel("Models/Level/Heaven/cloud_small_1v2_low_poly.j3o");
                cloudControl = new RigidBodyControl(new BoxCollisionShape(new Vector3f(1.7f, 0.5f, 1)), 0);
                break;
            case Constants.CLOUD_MEDIUM_1v1:
                cloud.model = (Node) assetManager.loadModel("Models/Level/Heaven/cloud_medium_1v1_low_poly.j3o");
                cloudControl = new RigidBodyControl(new BoxCollisionShape(new Vector3f(3, 0.5f, 1)), 0);
                
                break;
            case Constants.CLOUD_Long_1v1:
                cloud.model = (Node) assetManager.loadModel("Models/Level/Heaven/cloud_long_1v1_low_Poly.j3o");
                cloudControl = new RigidBodyControl(new BoxCollisionShape(new Vector3f(7, 0.5f, 1)), 0);
                break;
        }
        
        cloud.model.setName("asd123");
        
        /*
         //Ã„nderungen an der Texture
         */
        
            Node tmp = (Node) cloud.model.getChild(0);
        Geometry submesh = null;
        
        while (submesh == null && tmp.getChildren().size() > 0){
            if(tmp.getChild(0) instanceof Geometry){
                submesh = (Geometry) tmp.getChild(0);
               // submesh.setShadowMode(ShadowMode.CastAndReceive);
            }
            else
                tmp = (Node) tmp.getChild(0);
        }
        
        /*
         * //Ã„nderungen am Material
        Node tmp = (Node) cloud.model.getChild(0);
        Geometry submesh = null;
        
        while (submesh == null && tmp.getChildren().size() > 0){
            if(tmp.getChild(0) instanceof Geometry){
                submesh = (Geometry) tmp.getChild(0);
               // submesh.setShadowMode(ShadowMode.CastAndReceive);
            }
            else
                tmp = (Node) tmp.getChild(0);
        }
        
         Material cloudMaterial =  submesh.getMaterial();
         //Ambient
         //Specular
         //Diffuse
         //cloudMaterial.setFloat("m_Shininess", 100f);
         cloudMaterial.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);  // activate transparency
         cloudMaterial.setVector4("Diffuse", new Vector4f(0.75756456f, 0.649994f, 0.92508994f, 0.3f));
         cloudMaterial.setTransparent(true);
         cloudMaterial.setBoolean("UseMaterialColors",false);
         
         cloudMaterial.setColor("GlowColor", ColorRGBA.Green);
        
      //  cloudMaterial.setParam("Transparency", VarType.Float, 0.7f);
     //   cloudMaterial.setParam("WardIso", VarType.Boolean, true);
//Boolean Minnaert : false
//Boolean WardIso : false
        
        
        Collection<MatParam> list = cloudMaterial.getParams();
        System.out.println("--------------------------");
        for(MatParam t : list){
            System.out.println(t);
        }
  */
        cloud.model.setLocalTranslation(location.x, location.y, 0);
        cloud.model.addControl(cloudControl);
        appState.getPhysicsSpace().add(cloud.model);
        
        return cloud;
    }
    
    public static Item createRock(AssetManager assetManager,  BulletAppState appState, Vector3f location){
        Item rock = new Item("Rock");
        return rock;
    }
    
    public static Item createDeathStar(Vector3f location, BulletAppState appState, AssetManager assetManager){
        Item deathstar = new Item("DeathStar");
        deathstar.model = (Node) assetManager.loadModel("Models/Level/Heaven/starV1.j3o");
        deathstar.model.setLocalScale(0.5f);
        deathstar.model.setLocalTranslation(location.x, location.y, 0);
        
        RigidBodyControl control = new RigidBodyControl(new BoxCollisionShape(new Vector3f(1.2f, 1.4f, 1)), 0);
        deathstar.model.addControl(control);
        appState.getPhysicsSpace().add(deathstar.model);
                
        return deathstar;
    }
    
}