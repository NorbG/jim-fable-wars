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
import java.awt.Point;

/**
 *
 * @author Lena
 */
public class Heaven extends LevelState implements ActionListener, PhysicsCollisionListener, AnimEventListener {

    //parts in Node unterteilt, die attached & detached werden,
    private Point intervallZero;
    private LevelState partZero;
    
    private Point intervallOne;
    private LevelState partOne;
    
    private Point intervallTwo;
    private LevelState partTwo;
    
    private Point intervallThree;
    private LevelState partThree;
    
    private Point intervallFour;
    private LevelState partFour;

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
        
        //Intervall geht vom letzten Intervall bis zu den Koordinaten, in der Bewegung vom Player wird 
        //dann geprüft, in welchem er sich befindet & angepasst
        intervallZero = new Point(24, 0);
        
        this.game.attachState(partZero);
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

        loadPartOne();
        this.game.attachState(partOne);
         }

    private void loadPartFour() {
        //Part 4
        partFour = new LevelState();
        partFour.initialize(stateManager, game);
        partFour.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_MEDIUM_1v1, new Vector3f(40, 54, 0), bulletAppState, assetManager).model);
        // bewegende Wolke oben/unten
        partFour.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_MEDIUM_1v1, new Vector3f(30, 54, 0), bulletAppState, assetManager).model);
        // vierter Gegner
        partFour.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_MEDIUM_1v1, new Vector3f(15, 20, 0), bulletAppState, assetManager).model);

        // bewegende Wolke oben/unten mit Todesternhinderniss
        partFour.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(5, 20, 0), bulletAppState, assetManager).model);
        partFour.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(9, 34, 0), bulletAppState, assetManager).model);
        // item
        partFour.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(14, 38, 0), bulletAppState, assetManager).model);
        partFour.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(9, 42, 0), bulletAppState, assetManager).model);
        // bewegende Wolke links/rechts
        partFour.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(5, 64, 0), bulletAppState, assetManager).model);
        // viele Todessterne
        // mit Todesstern und fünftem Gegner
        partFour.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_Long_1v1, new Vector3f(80, 64, 0), bulletAppState, assetManager).model);
        // sechster Gegner
        partFour.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(90, 68, 0), bulletAppState, assetManager).model);
         
        intervallFour = new Point(90, 68);
    }

    private void loadPartThree() {
        //Part 3
       // Level 1 //
       partThree = new LevelState();
       partThree.initialize(stateManager, game);
       
       partThree.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(123, 27, 0), bulletAppState, assetManager).model);
       // dritter Gegner
       partThree.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(116, 30, 0), bulletAppState, assetManager).model);
       // bewegende Wolken oben/unten
       partThree.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(107, 32, 0), bulletAppState, assetManager).model);
       partThree.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(98, 32, 0), bulletAppState, assetManager).model);
       partThree.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(89, 32, 0), bulletAppState, assetManager).model);

       // bewegende Wolken links/rechts
       partThree.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(82, 36, 0), bulletAppState, assetManager).model);
       partThree.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(78, 42, 0), bulletAppState, assetManager).model);
       partThree.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(74, 48, 0), bulletAppState, assetManager).model);
       partThree.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(70, 54, 0), bulletAppState, assetManager).model);

       intervallThree = new Point(123, 27);
    }
    
    
    private void loadPartOne() {
        //Part 1
        partOne = new LevelState();
        partOne.initialize(stateManager, game);
        partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(31, 3, 0), bulletAppState, assetManager).model);
        // erster Gegner
        partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_Long_1v1, new Vector3f(45, 1, 0), bulletAppState, assetManager).model);
        // bewegende Wolke nach oben/unten
        partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(56, 5, 0), bulletAppState, assetManager).model);
        // item
        partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(50, 18, 0), bulletAppState, assetManager).model);
        partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(62, 13, 0), bulletAppState, assetManager).model);
        // zweiter Gegner
        partOne.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_MEDIUM_1v1, new Vector3f(73, 6, 0), bulletAppState, assetManager).model);

        intervallOne = new Point(73, 1);
    }

    
    private void loadPartTwo() {
        //Part 2
      // Treppe
      partTwo = new LevelState();
      partTwo.initialize(stateManager, game);
      
      partTwo.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(80, 9, 0), bulletAppState, assetManager).model);
      partTwo.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(85, 12, 0), bulletAppState, assetManager).model);
      partTwo.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(90, 15, 0), bulletAppState, assetManager).model);
      partTwo.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(95, 18, 0), bulletAppState, assetManager).model);
      partTwo.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(100, 21, 0), bulletAppState, assetManager).model);

      // geheime PLatform mit Gegner
      partTwo.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_Long_1v1, new Vector3f(112, 21, 0), bulletAppState, assetManager).model);
      partTwo.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_Long_1v1, new Vector3f(98, 2, 0), bulletAppState, assetManager).model);
      partTwo.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_Long_1v1, new Vector3f(112, 2, 0), bulletAppState, assetManager).model);
      // bewegende Wolke nach oben/unten mit Item
      partTwo.rootNode.attachChild(ItemFactory.createCloud(Constants.CLOUD_SMALL_1v2, new Vector3f(124, 2, 0), bulletAppState, assetManager).model);

      intervallTwo = new Point(124, 2);
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
