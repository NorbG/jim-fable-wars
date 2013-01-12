/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package level;

import Helper.Constants;
import character.CharacterFactory;
import character.Player;
import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Node;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.light.DirectionalLight;
import com.jme3.animation.AnimEventListener;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import de.lessvoid.nifty.elements.render.PanelRenderer;
import de.lessvoid.nifty.tools.Color;
import java.awt.Point;

/**
 *
 * @author Lena
 */
public class Heaven extends LevelState implements ActionListener, PhysicsCollisionListener, AnimEventListener {

    //parts in Node unterteilt, die attached & detached werden,
    private Item lastCloudZero;
    private LevelState partZero;
    
    private Item lastCloudOne;
    private LevelState partOne;
	
	private final float LEVEL_BOTTOM = -10.f;    

    public Heaven() {
        super();
    }


    public Node getRootNode() {
        return this.rootNode;
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.rootNode = game.getRootNode();
        bulletAppState.getPhysicsSpace().addCollisionListener(this);
        bulletAppState.getPhysicsSpace().add(player.control);
        bulletAppState.getPhysicsSpace().enableDebug(assetManager);
        
        initKeys();
        loadPartZero();
        loadAmbient();
    }

    
    private void loadPartZero() {
        // Level 0 //
        // Part 0
        partZero = new LevelState();
        partZero.initialize(stateManager, game);
        DirectionalLight sun = new DirectionalLight();
        sun.setColor(ColorRGBA.White);
        sun.setDirection(new Vector3f(-.5f, -.5f, -.5f).normalizeLocal());
        rootNode.addLight(sun);
       // part.setParent(this);
        //Start
        partZero.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(0, 0, 0), bulletAppState, assetManager).model);
        partZero.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(6, 0, 0), bulletAppState, assetManager).model);

        // fallende Wolken
        partZero.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(9, 0, 0), bulletAppState, assetManager).model);
        partZero.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(12, 0, 0), bulletAppState, assetManager).model);
        partZero.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(15, 0, 0), bulletAppState, assetManager).model);
        partZero.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(18, 0, 0), bulletAppState, assetManager).model);
        partZero.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(21, 0, 0), bulletAppState, assetManager).model);
        partZero.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(24, 0, 0), bulletAppState, assetManager).model);
        
        //Part1
        partZero.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(31, 3, 0), bulletAppState, assetManager).model);
        // erster Gegner
        partZero.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_Long_1v1, new Vector3f(45, 1, 0), bulletAppState, assetManager).model);
        // bewegende Wolke nach oben/unten
        partZero.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(56, 5, 0), bulletAppState, assetManager).model);
        // item
        partZero.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(50, 18, 0), bulletAppState, assetManager).model);
        partZero.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(62, 13, 0), bulletAppState, assetManager).model);
        // zweiter Gegner
        partZero.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_MEDIUM_1v1, new Vector3f(73, 6, 0), bulletAppState, assetManager).model);
  
      //Part 2
      // Treppe
      
      partZero.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(80, 9, 0), bulletAppState, assetManager).model);
      partZero.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(85, 12, 0), bulletAppState, assetManager).model);
      partZero.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(90, 15, 0), bulletAppState, assetManager).model);
      partZero.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(95, 18, 0), bulletAppState, assetManager).model);
      partZero.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(100, 21, 0), bulletAppState, assetManager).model);

      // geheime PLatform mit Gegner
      partZero.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_Long_1v1, new Vector3f(112, 21, 0), bulletAppState, assetManager).model);
      partZero.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_Long_1v1, new Vector3f(98, 2, 0), bulletAppState, assetManager).model);
      partZero.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_Long_1v1, new Vector3f(112, 2, 0), bulletAppState, assetManager).model);
      // bewegende Wolke nach oben/unten mit Item
      lastCloudZero = ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(124, 2, 0), bulletAppState, assetManager);
      rootNode.attachChild(lastCloudZero.model);
        
        this.game.attachState(partZero);
        rootNode.attachChild(partZero.rootNode);
         }

    
    
    private void loadPartOne() {
        //Part 4
        partOne = new LevelState();
        partOne.initialize(stateManager, game);
        
        //Part 3
       // Level 1 //
       partOne.rootNode.attachChild(lastCloudZero.model);
       partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(123, 27, 0), bulletAppState, assetManager).model);
       // dritter Gegner
       partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(116, 30, 0), bulletAppState, assetManager).model);
       // bewegende Wolken oben/unten
       partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(107, 32, 0), bulletAppState, assetManager).model);
       partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(98, 32, 0), bulletAppState, assetManager).model);
       partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(89, 32, 0), bulletAppState, assetManager).model);

       // bewegende Wolken links/rechts
       partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(82, 36, 0), bulletAppState, assetManager).model);
       partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(78, 42, 0), bulletAppState, assetManager).model);
       partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(74, 48, 0), bulletAppState, assetManager).model);
       partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(70, 54, 0), bulletAppState, assetManager).model);
        
      
       partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_MEDIUM_1v1, new Vector3f(40, 54, 0), bulletAppState, assetManager).model);
        // bewegende Wolke oben/unten
       partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_MEDIUM_1v1, new Vector3f(30, 54, 0), bulletAppState, assetManager).model);
        // vierter Gegner
       partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_MEDIUM_1v1, new Vector3f(15, 20, 0), bulletAppState, assetManager).model);

        // bewegende Wolke oben/unten mit Todesternhinderniss
        partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(5, 20, 0), bulletAppState, assetManager).model);
        partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(9, 34, 0), bulletAppState, assetManager).model);
        // item
        partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(14, 38, 0), bulletAppState, assetManager).model);
        partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(9, 42, 0), bulletAppState, assetManager).model);
        // bewegende Wolke links/rechts
        partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(5, 64, 0), bulletAppState, assetManager).model);
        // viele Todessterne
        // mit Todesstern und fünftem Gegner
        partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_Long_1v1, new Vector3f(80, 64, 0), bulletAppState, assetManager).model);
        // sechster Gegner
        lastCloudOne = ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(90, 68, 0), bulletAppState, assetManager);
        rootNode.attachChild(lastCloudOne.model);
        
        this.game.attachState(partOne);
        rootNode.attachChild(partOne.rootNode);
    }


    
    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void collision(PhysicsCollisionEvent event) {
        if ( event.getNodeA().getName().equals("player")
                && event.getNodeB().getName().equals("Star")) {
             //player.adjustHealth(1);
        }
        else if ( event.getNodeB().getName().equals("player") 
                    && event.getNodeA().getName().equals("Star")) {
            //player.adjustHealth(1);
        }
       
    }

    @Override
    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private void initKeys() {
        game.getInputManager().addMapping("Left", new KeyTrigger(KeyInput.KEY_LEFT));
        game.getInputManager().addMapping("Right", new KeyTrigger(KeyInput.KEY_RIGHT));
        game.getInputManager().addMapping("Jump", new KeyTrigger(KeyInput.KEY_A));
        game.getInputManager().addMapping("RangeAttack", new KeyTrigger(KeyInput.KEY_S));
        game.getInputManager().addMapping("MeleeAttack", new KeyTrigger(KeyInput.KEY_D));
        game.getInputManager().addMapping("Escape", new KeyTrigger(KeyInput.KEY_ESCAPE));

        game.getInputManager().addListener(analogListener, new String[]{"Left", "Right", "Jump"});
        game.getInputManager().addListener(actionListener, new String[]{"RangeAttack", "MeleeAttack", "Escape"});
    }
    private AnalogListener analogListener = new AnalogListener() {
        public void onAnalog(String name, float value, float tpf) {
            // move left
            if (name.equals("Left")) {
                player.currentDirection = Player.Direction.LEFT;
                player.isMoving = true;
            }

            // move right
            if (name.equals("Right")) {
                player.currentDirection = Player.Direction.RIGHT;
                player.isMoving = true;
            }

            // jump
            if (name.equals("Jump")) {
                if (player.currentJumpDelay >= player.JUMP_DELAY && player.canJump) {
                    player.currentJumpDelay = 0;
                    player.jumping = true;
                }
            }
        }
    };
    
    private ActionListener actionListener = new ActionListener() {
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (name.equals("RangeAttack") && keyPressed) {
                game.attachState(player.handleRangedAttack());
            }
            
            if (name.equals("MeleeAttack") && keyPressed) {
            }
            
            if (name.equals("Escape") && keyPressed) {
            }
        }
    };
    
    @Override
    public void update(float tpf){
        player.handleMovement(tpf, game);
		
		// check for death
        if (player.getPlayerLocation().y < LEVEL_BOTTOM)
        {
            rootNode.detachChildNamed("Player");            
            game.player = player = CharacterFactory.createPlayer("Dragon", assetManager);
            getRootNode().attachChild(player.model);
        }
    }

    private void loadAmbient() {
        Node skyBox = (Node) assetManager.loadModel("Models/Ambient/Heaven/Sky.j3o");
        DirectionalLight sun = new DirectionalLight();
        sun.setColor(ColorRGBA.White);
        sun.setDirection(new Vector3f(-.5f, -.5f, -.5f).normalizeLocal());
        
        rootNode.addLight(sun);
        rootNode.attachChild(skyBox);
    }
}
