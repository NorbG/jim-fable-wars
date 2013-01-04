/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import com.jme3.animation.AnimControl;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 *
 * @author Lena
 */
public class CharacterFactory {
    
    
    public static Player createPlayer(String type, AssetManager assetManager,  BulletAppState appState){
        //TODO attack
    Player player = new Player(type, 2, 2);
    createCharacter(assetManager, type, appState, player);
    return player;
    }
    
    
    public static Opponent createOpponent(String type, AssetManager assetManager){
        //TODO: attack
    Opponent op = new Opponent(type, 3);
    return op;
    }
    
    private static void createCharacter(AssetManager assetManager, String type, BulletAppState appState, Player player) {
        //load the model and the rigid body animation
       // Node character = (Node) assetManager.loadModel("Models/" + type + ".mesh.xml");
       Node model = (Node) assetManager.loadModel("Models/Oto/Oto.mesh.xml");
        CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(1.5f, 6f, 1);
        CharacterControl control = new CharacterControl(capsuleShape, 0.01f);
        model.addControl(control);
  
        appState.getPhysicsSpace().add(control);
        control.setPhysicsLocation(new Vector3f(-140, 15, -10));
        
       player.model = model;
       player.character = control;
        
        //set the animations
        player.animation = player.model.getControl(AnimControl.class);
        player.channel = player.animation.createChannel();
        player.channel.addBone(player.animation.getSkeleton().getBone("uparm.right"));
        player.channel.addBone(player.animation.getSkeleton().getBone("arm.right"));
        player.channel.addBone(player.animation.getSkeleton().getBone("hand.right"));
        
 
    }
}
