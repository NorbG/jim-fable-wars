/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import com.jme3.animation.AnimControl;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.GhostControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import mygame.Game;

/**
 *
 * @author Lena
 */
public class CharacterFactory {

    public static Player createPlayer(String type, AssetManager assetManager) {
        //TODO attack
        Player player = null;
        if (type.equals("Dragon")) {
            player = new Player(type, 3, 0);
            player.adjustHealth(3);
        }
        
        player.model = (Node) assetManager.loadModel("Models/Character/dragon.j3o");
        player.model.setName("Player");
        player.model.scale(0.6f);
        player.model.setLocalTranslation(0.f, 50.f, 0);
        
        BoxCollisionShape collisionShape = new BoxCollisionShape(new Vector3f(0.5f, 0.7f, 0.5f));
       // GhostControl control = new GhostControl(collisionShape);
     //   control.addCollideWithGroup(Helper.Constants.CLOUDGROUP);
        RigidBodyControl character = new RigidBodyControl(collisionShape, 1.3f);
        character.setAngularDamping(100f);
        player.character = character;
        player.model.addControl(character);
        Material mat_tt = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_tt.setTexture("ColorMap", assetManager.loadTexture("Textures/dragon_texture.png"));
        mat_tt.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);

        player.model.setMaterial(mat_tt);
               
        player.attack = new AudioNode(assetManager, "Sounds/Dragon/attack.wav");
        player.on_death = new AudioNode(assetManager, "Sounds/Dragon/on_death.wav");
        player.on_hit = new AudioNode(assetManager, "Sounds/Dragon/on_hit.wav");
        player.walk = new AudioNode(assetManager, "Sounds/Dragon/walk.wav");



        /*player.standAnimation = "stand";
        player.flyAnimation = "dragon_move";
        //set the animations
        player.animation = player.model.getControl(AnimControl.class);
        player.channel = player.animation.createChannel();
          for(int i = 0; i < player.animation.getSkeleton().getBoneCount(); i++){
              player.channel.addBone(player.animation.getSkeleton().getBone(i));
        }*/
        return player;
    }

    public static Opponent createOpponent(String name, AssetManager assetManager) {
        //TODO: attack
        Opponent op = null;
        op = new Opponent(name, 1);
        Node model = null;
        RigidBodyControl control = null;

        if(name.equals(Helper.Constants.FAIRY)){
        model = (Node) assetManager.loadModel("Models/Character/pixyJoinedclothes.j3o");
        model.scale(0.7f);
        model.setName(name);
        
        
        BoxCollisionShape collisionShape = new BoxCollisionShape(new Vector3f(0.7f, 1f, 0.4f));
        control = new RigidBodyControl(collisionShape, 1);
        

       //
        op.attack = new AudioNode(assetManager, "Sounds/Opponents/Fairy/attack.wav");
        op.walk = new AudioNode(assetManager, "Sounds/Opponents/Fairy/walk.wav");
        }
        else if(name.equals(Helper.Constants.UNICORN)){
        model = (Node) assetManager.loadModel("Models/Character/unicorn.j3o");
        model.scale(0.6f);
        model.setName(name);
        
        
        BoxCollisionShape collisionShape = new BoxCollisionShape(new Vector3f(0.7f, 1f, 0.4f));
        control = new RigidBodyControl(collisionShape, 1);

       //
        op.attack = new AudioNode(assetManager, "Sounds/Opponents/Unicorn/attack.wav");
        op.walk = new AudioNode(assetManager, "Sounds/Opponents/Unicorn/walk.wav");
        op.on_death = new AudioNode(assetManager, "Sounds/Opponents/Unicorn/on_death.wav");
        op.on_hit = new AudioNode(assetManager, "Sounds/Opponents/Unicorn/on_hit.wav");
        }
        
        control.setAngularDamping(100f);
        control.setApplyPhysicsLocal(true);
        model.addControl(control);
        op.character = control;
        op.model = model;
        //character = control;
        //op.standAnimation = "stand";
        //op.walkAnimation = "Walk";
        //set the animations
        /*op.animation = op.model.getControl(AnimControl.class);
        op.channel = op.animation.createChannel();
        for (int i = 0; i < op.animation.getSkeleton().getBoneCount(); i++) {
            op.channel.addBone(op.animation.getSkeleton().getBone(i));
        }*/
        return op;
    }
}
