/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package level;

import Helper.Constants;
import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Node;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.light.DirectionalLight;
import com.jme3.animation.AnimEventListener;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

/**
 *
 * @author Lena
 */
public class Heaven extends LevelState implements ActionListener, PhysicsCollisionListener, AnimEventListener {

    protected Castle castle;

    public Heaven() {
        super();
    }


    public Node getRootNode() {
        return this.rootNode;
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        createLevel();
    }

    @Override
    public void update(float tpf) {
       super.update(tpf);
        //gameStateManger.getChild(0).isActive();
      //  rootNode.updateLogicalState(tpf);
       // rootNode.updateGeometricState();
    }

    private void createLevel() {
        // Level 0 //
        DirectionalLight sun = new DirectionalLight();
        sun.setColor(ColorRGBA.White);
        sun.setDirection(new Vector3f(-.5f, -.5f, -.5f).normalizeLocal());
        rootNode.addLight(sun);
       // part.setParent(this);
        //Start
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(0, 0, 0), bulletAppState, assetManager).model);
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(6, 0, 0), bulletAppState, assetManager).model);

        // fallende Wolken
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(9, 0, 0), bulletAppState, assetManager).model);
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(12, 0, 0), bulletAppState, assetManager).model);
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(15, 0, 0), bulletAppState, assetManager).model);
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(18, 0, 0), bulletAppState, assetManager).model);
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(21, 0, 0), bulletAppState, assetManager).model);
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(24, 0, 0), bulletAppState, assetManager).model);

        //rootNode.attachChild(partZero);
        /*
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(0, 0, 0), bulletAppState, assetManager).model);
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(6, 0, 0), bulletAppState, assetManager).model);

        // fallende Wolken
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(9, 0, 0), bulletAppState, assetManager).model);
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(12, 0, 0), bulletAppState, assetManager).model);
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(15, 0, 0), bulletAppState, assetManager).model);
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(18, 0, 0), bulletAppState, assetManager).model);
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(21, 0, 0), bulletAppState, assetManager).model);
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(24, 0, 0), bulletAppState, assetManager).model);
*/

       // Level 1 //
       
       rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(123, 27, 0), bulletAppState, assetManager).model);
       // dritter Gegner
       rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(116, 30, 0), bulletAppState, assetManager).model);
       // bewegende Wolken oben/unten
       rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(107, 32, 0), bulletAppState, assetManager).model);
       rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(98, 32, 0), bulletAppState, assetManager).model);
       rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(89, 32, 0), bulletAppState, assetManager).model);

       // bewegende Wolken links/rechts
       rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(82, 36, 0), bulletAppState, assetManager).model);
       rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(78, 42, 0), bulletAppState, assetManager).model);
       rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(74, 48, 0), bulletAppState, assetManager).model);
       rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(70, 54, 0), bulletAppState, assetManager).model);

        //Part 1

        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(31, 3, 0), bulletAppState, assetManager).model);
        // erster Gegner
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_Long_1v1, new Vector3f(45, 1, 0), bulletAppState, assetManager).model);
        // bewegende Wolke nach oben/unten
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(56, 5, 0), bulletAppState, assetManager).model);
        // item
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(50, 18, 0), bulletAppState, assetManager).model);
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(62, 13, 0), bulletAppState, assetManager).model);
        // zweiter Gegner
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_MEDIUM_1v1, new Vector3f(73, 6, 0), bulletAppState, assetManager).model);

  
        //Part 2
      // Treppe
      rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(80, 9, 0), bulletAppState, assetManager).model);
      rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(85, 12, 0), bulletAppState, assetManager).model);
      rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(90, 15, 0), bulletAppState, assetManager).model);
      rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(95, 18, 0), bulletAppState, assetManager).model);
      rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(100, 21, 0), bulletAppState, assetManager).model);

      // geheime PLatform mit Gegner
      rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_Long_1v1, new Vector3f(112, 21, 0), bulletAppState, assetManager).model);
      rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_Long_1v1, new Vector3f(98, 2, 0), bulletAppState, assetManager).model);
      rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_Long_1v1, new Vector3f(112, 2, 0), bulletAppState, assetManager).model);
      // bewegende Wolke nach oben/unten mit Item
      rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(124, 2, 0), bulletAppState, assetManager).model);

              rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_MEDIUM_1v1, new Vector3f(40, 54, 0), bulletAppState, assetManager).model);
        // bewegende Wolke oben/unten
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_MEDIUM_1v1, new Vector3f(30, 54, 0), bulletAppState, assetManager).model);
        // vierter Gegner
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_MEDIUM_1v1, new Vector3f(15, 20, 0), bulletAppState, assetManager).model);

        // bewegende Wolke oben/unten mit Todesternhinderniss
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(5, 20, 0), bulletAppState, assetManager).model);
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(9, 34, 0), bulletAppState, assetManager).model);
        // item
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(14, 38, 0), bulletAppState, assetManager).model);
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(9, 42, 0), bulletAppState, assetManager).model);
        // bewegende Wolke links/rechts
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(5, 64, 0), bulletAppState, assetManager).model);
        // viele Todessterne
        // mit Todesstern und fünftem Gegner
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_Long_1v1, new Vector3f(80, 64, 0), bulletAppState, assetManager).model);
        // sechster Gegner
        rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(90, 68, 0), bulletAppState, assetManager).model);

    }

    
    public void onAction(String name, boolean isPressed, float tpf) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void collision(PhysicsCollisionEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
